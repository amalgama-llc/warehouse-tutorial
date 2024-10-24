package com.company.warehouse.simulation;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import com.amalgamasimulation.engine.Engine;
import com.amalgamasimulation.geometry.Point;
import com.amalgamasimulation.geometry.Polyline;
import com.amalgamasimulation.graphagent.GraphEnvironment;
import com.amalgamasimulation.utils.Pair;
import com.amalgamasimulation.utils.Utils;
import com.company.warehouse.datamodel.Direction;
import com.company.warehouse.datamodel.Scenario;
import com.company.warehouse.simulation.equipment.Forklift;
import com.company.warehouse.simulation.equipment.Truck;
import com.company.warehouse.simulation.graph.Arc;
import com.company.warehouse.simulation.graph.EnvironmentWithPallets;
import com.company.warehouse.simulation.graph.Node;
import com.company.warehouse.simulation.graph.agents.Agent;
import com.company.warehouse.simulation.tasks.IdlingTask;
import com.company.warehouse.simulation.tasks.MovePalletTask;

/**
 * This is the class that represents the simulation model itself. In our case,
 * it contains the graph environment and the list of agents.
 */
@SuppressWarnings("serial")
public class Model extends com.amalgamasimulation.engine.Model {
	private Scenario scenario;
	private Mapping mapping = new Mapping();
	
	// Graph environment from Amalgama Platform's Graph Agent Library.
	// The container class for the graph and agents living in this graph.
//	protected GraphEnvironment<Node, Arc, Agent> graphEnvironment;
    private EnvironmentWithPallets graphEnvironment;
	public EnvironmentWithPallets getGraphEnvironment() {
		return graphEnvironment;
	}
	
	private Random random = new Random(0);
	private double endTime;

    private List<Forklift> forklifts = new ArrayList<>();
	public List<Forklift> getForklifts() {
		return forklifts;
	}
	
    private List<PalletPosition> allPositions = new ArrayList<>();
    public Iterable<PalletPosition> getAllPositions() {
        return allPositions;
    }

    private StorageArea mainStorage;
    public StorageArea getMainStorage() {
        return mainStorage;
    }

    private final Map<Direction, List<Gate>> gatesByDirection = Map.of(
            Direction.IN, new ArrayList<>(),
            Direction.OUT, new ArrayList<>()
            );

    private final Dispatcher dispatcher;

    private final Map<Direction, DoubleSummaryStatistics> truckLoadingDuration = Map.of(
            Direction.IN, new DoubleSummaryStatistics(),
            Direction.OUT, new DoubleSummaryStatistics()
            );

    /**
	 * This constructor takes a data model's scenario as its second argument and
	 * performs the initialization of the simulation model. During initialization,
	 * it creates a mapping between data model entities and simulation entities.
	 * This mapping is stored inside this class and can be retrieved with
	 * getMapping() method.
	 */
	public Model(Engine engine, Scenario scenario) {
		super(engine);
		this.scenario = scenario;
//		engine().setTemporal(scenario.getBeginDate(), ChronoUnit.HOURS);
		engine().setTemporal(scenario.getBeginDate(), ChronoUnit.MINUTES);
		setEndTime(dateToTime(scenario.getEndDate()));
		
		// Create a new empty graph environment upon creation of the model
//		graphEnvironment = new GraphEnvironment<>();
		graphEnvironment = new EnvironmentWithPallets();
		initializeNodes();
		initializeArcs();
        initializeMainStorage();
        initializeGates();
		initializeForklifts();
		
//		makeAssignments();
        dispatcher = new Dispatcher(this);
        engine().scheduleRelative(0, this::spawnTrucks);
    }

    /**
     * @param probability  requested probability of the <code>true</code> value.
     * @return  pseudo-random boolean
     */
    public boolean randomTrue(double probability){
        return random.nextDouble() < probability;
    }

    private PalletPosition newPalletPosition(com.company.warehouse.datamodel.Node scenarioNode, boolean busy) {
        var node = mapping.nodesMap.get(scenarioNode);
        var p = new PalletPosition(node);
        if (busy) {
            p.placePallet(true);
        }
        allPositions.add(p);
        return p;
    }

    private void initializeMainStorage() {
        final var places = scenario.getStoragePlaces().stream()
                .map(scenarioNode -> newPalletPosition(scenarioNode, randomTrue(0.5)))
                .collect(Collectors.toCollection(LinkedList::new));
        
        mainStorage = new StorageArea(places);
    }

    private void initializeGates() {
        for (var scenarioGate : scenario.getGates()) {
            final var direction = scenarioGate.getDirection();
            final var entrance = mapping.nodesMap.get(scenarioGate.getEntrance());
            final var places = scenarioGate.getPlaces().stream()
//                    .map(scenarioNode -> newPalletPosition(scenarioNode, direction == Direction.OUT))
                    .map(scenarioNode -> newPalletPosition(scenarioNode, false))
                    .collect(Collectors.toCollection(LinkedList::new));
            final var gate = new Gate(direction, entrance, new StorageArea(places));
            getGatesInDirection(direction).add(gate);
        }
    }

    private void initializeForklifts() {
        for (var scenarioForklift : scenario.getForklifts()) {
            forklifts.add(new Forklift(
                    engine(),
                    graphEnvironment,
                    scenarioForklift.getName(),
                    mapping.nodesMap.get(scenarioForklift.getBase()),
                    scenarioForklift.getVelocity(),
                    scenarioForklift.getLoadingTimeSec() * second(),
                    scenarioForklift.getUnloadingTimeSec() * second()
                    ));
        }
	}

    private void makeAssignments() {
        // To iterate busy positions
        var busyPositions = graphEnvironment.getNodeValues().stream()
                .flatMap(node -> node.getPalletPosition().stream())
                .filter(position -> position.isBusy())
                .spliterator();
        
        // To iterate free positions
        var freePositions = graphEnvironment.getNodeValues().stream()
                .flatMap(node -> node.getPalletPosition().stream())
                .filter(position -> !position.isBusy())
                .spliterator();

        int i = 0;
        for (var forklift : forklifts) {
            final var startTime = i++ * 2 * minute();
            busyPositions.tryAdvance(from ->
                freePositions.tryAdvance(to -> {
                    final var movingTask = new MovePalletTask(engine(), forklift, from, to);
                    final var idlingTask = new IdlingTask(engine(), forklift);
                    engine().scheduleRelative(startTime,
                            () -> movingTask.start(
                                    () -> idlingTask.start(null)
                            )
                    );
                })
            );
        }
    }
	
    public List<Gate> getGatesInDirection(Direction direction) {
        return gatesByDirection.get(direction);
    }

    private void spawnTrucks() {
        for (var d : Direction.VALUES) {
            dispatcher.truckArrived(new Truck(d, scenario.getTruckCapacity()));
        }
        engine().scheduleRelative(scenario.getTruckArrivalIntervalMin() * minute(), this::spawnTrucks);
    }

	/**
	 * This is the method that is called every time an agent reaches its
	 * destination. It selects the random node from all the nodes excluding the one
	 * it is currently in, and sends the agent to this node.
	 * 
	 */
	private void dispatchAgent(Agent agent) {
		List<Node> possibleDestinationNodes = graphEnvironment	.getNodeValues()
																.stream()
																.filter(n -> n != agent.getCurrentNode().getValue())
																.toList();
		if (possibleDestinationNodes.isEmpty()) {
			throw new RuntimeException("There are no nodes to send an agent to");
		}
		agent.moveTo(possibleDestinationNodes.get(random.nextInt(possibleDestinationNodes.size())));
	}
	
	/**
	 * This method sets the simulation end time of the model and schedules the stop
	 * of the engine at this time.
	 */
	public void setEndTime(double endTime) {
		this.endTime = endTime;
		engine().scheduleStop(endTime, "Simulation end");
	}
	
	/**
	 * Returns the mapping between data model entities and simulation entities.
	 */
	public Mapping getMapping() {
		return mapping;
	}
	
	/**
	 * Returns the date model scenario from which this model had been created.
	 */
	public Scenario getScenario() {
		return scenario;
	}
	
	/**
	 * Adds an new agent to the model and performs all the necessary settings. This
	 * method must be used during initialization of the model.
	 */
	public Agent addAgent(String name, Node baseNode, double velocity) {
		Agent agent = new Agent(engine(), name, baseNode, velocity);
		agent.setDestinationArrivalHandler(() -> dispatchAgent(agent));
		agent.setGraphEnvironment(graphEnvironment);
		agent.jumpTo(baseNode);
		return agent;
	}
	
	/**
	 * Adds an new network node to the model and performs all the necessary
	 * settings. This method must be used during initialization of the model.
	 */
	public Node addNode(Point point, String id) {
		Node node = new Node(point, graphEnvironment, id);
		graphEnvironment.addNode(node);
		return node;		
	}
	
	/**
	 * Adds an new network pair of arcs (i.e., a single bidirectional arc) to the
	 * model and performs all the necessary settings. This method must be used
	 * during initialization of the model.
	 */
	public Pair<Arc, Arc> addArcs(Node source, Node dest, Polyline forwardPolyline) {
		var result = graphEnvironment.addArc(	source,
												dest,
												new Arc(forwardPolyline),
												new Arc(forwardPolyline.getReversed()));		
		return new Pair<>(result.first.getValue(), result.second.getValue());
	}

	public Random getRandom() {
		return random;
	}
	
	
	public List<Node> getNodes() {
		return graphEnvironment.getNodeValues();
	}

	public List<Arc> getArcs() {
		return graphEnvironment.getArcValues();
	}
	
	public List<Agent> getAgents() {
		return graphEnvironment	.getAgents()
								.stream()
								.map(Agent.class::cast)
								.toList();
	}
	
	public double getEndTime() {
		return endTime;
	}
	
	private void initializeNodes() {
		for (var scenarioNode : scenario.getNodes()) {
			Node node = addNode(new Point(scenarioNode.getX(), scenarioNode.getY()), scenarioNode.getId());
			mapping.nodesMap.put(scenarioNode, node);
		}
	}
	
	private void initializeAgents() {
		for (var scenarioAgent : scenario.getAgents()) {
			if (scenarioAgent.isIncluded()) {
				Node basePosition = mapping.nodesMap.get(scenarioAgent.getBasePosition());
				Agent agent = addAgent("Agent #" + getAgents().size(), basePosition, scenarioAgent.getVelocity());
				mapping.agentsMap.put(scenarioAgent, agent);
			}
		}
	}


	private void initializeArcs() {
		for (var scenarioArc : scenario.getArcs()) {
			Polyline polyline = createPolyline(scenarioArc);
			if (polyline.getLength() != 0) {
				Node sourceNode = mapping.nodesMap.get(scenarioArc.getSource());
				Node destNode = mapping.nodesMap.get(scenarioArc.getDest());
				Pair<Arc, Arc> arcs = addArcs(sourceNode, destNode, polyline);
				mapping.forwardArcsMap.put(scenarioArc, arcs.first);
			}
		}
	}

    public double getForkliftUtilization() {
        final double avgUtilizationTime = forklifts.stream()
                .mapToDouble(f -> f.getUtilizedTime())
                .average().orElse(0);
        
        return Utils.zidz(avgUtilizationTime, time());
    }

    public DoubleSummaryStatistics getTruckLoadingDuration(Direction direction) {
        return truckLoadingDuration.get(direction);
    }

	private Polyline createPolyline(com.company.warehouse.datamodel.Arc dmArc) {
		List<Point> points = new ArrayList<>();
		points.add(new Point(dmArc.getSource().getX(), dmArc.getSource().getY()));
		dmArc.getPoints().forEach(bendpoint -> points.add(new Point(bendpoint.getX(), bendpoint.getY())));
		points.add(new Point(dmArc.getDest().getX(), dmArc.getDest().getY()));
		return new Polyline(Utils.toList(points.stream().distinct()));
	}
}

