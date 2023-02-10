package com.amalgamasimulation.warehouse.simulation;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.amalgamasimulation.engine.Engine;
import com.amalgamasimulation.geometry.Point;
import com.amalgamasimulation.geometry.Polyline;
import com.amalgamasimulation.utils.SingleStateStatistics;
import com.amalgamasimulation.utils.Utils;
import com.amalgamasimulation.utils.random.DefaultRandomGenerator;
import com.amalgamasimulation.warehouse.datamodel.Direction;
import com.amalgamasimulation.warehouse.datamodel.Scenario;
import com.amalgamasimulation.warehouse.simulation.equipment.Forklift;
import com.amalgamasimulation.warehouse.simulation.equipment.IEquipmentState;
import com.amalgamasimulation.warehouse.simulation.equipment.Truck;
import com.amalgamasimulation.warehouse.simulation.graph.Arc;
import com.amalgamasimulation.warehouse.simulation.graph.EnvironmentWithPallets;
import com.amalgamasimulation.warehouse.simulation.graph.Node;

@SuppressWarnings("serial")
public class Model extends com.amalgamasimulation.engine.Model {
	private EnvironmentWithPallets graphEnvironment = new EnvironmentWithPallets();
	private final Scenario scenario;
	private final Mapping mapping = new Mapping();
	
	private List<Arc> arcs = new ArrayList<>();
	
	private List<Forklift> forklifts = new ArrayList<>();
	
	private DefaultRandomGenerator random = new DefaultRandomGenerator(0);

    private List<PalletPosition> allPositions = new ArrayList<>();
    
    private final Map<Direction, List<Gate>> gates = Map.of(
            Direction.IN, new ArrayList<>(),
            Direction.OUT, new ArrayList<>()
            );
    
    private final Dispatcher dispatcher;
    
    private StorageArea mainStorage;
    
    private final Map<Direction, DoubleSummaryStatistics> truckLoadingDuration = Map.of(
            Direction.IN, new DoubleSummaryStatistics(),
            Direction.OUT, new DoubleSummaryStatistics()
            );
    
	public Model(Engine engine, Scenario scenario) {
		super(engine);
		engine().setTemporal(scenario.getBeginDate(), ChronoUnit.HOURS);
		engine().scheduleStop(dateToTime(scenario.getEndDate()), 0, "Experiment finished");
		this.scenario = scenario;
		
		initializeNodes();
		initializeModelObjects();
		
        dispatcher = new Dispatcher(this);
        engine().scheduleRelative(0, this::spawnTrucks);
	}
	
	public DoubleSummaryStatistics getTruckLoadingDuration(Direction direction) {
		return truckLoadingDuration.get(direction);
	}
	
	private void spawnTrucks() {
        for (var d : Direction.VALUES) {
            dispatcher.truckArrived(new Truck(d, scenario.getTruckCapacity()));
        }
        engine().scheduleRelative(scenario.getTruckArrivalIntervalMin() * minute(), this::spawnTrucks);
    }
	
	/**
     * @param probability  requested probability of the <code>true</code> value.
     * @return  pseudo-random boolean
     */
    public boolean randomTrue(double probability){
        return random.nextDouble() < probability;
    }
    
    public Iterable<PalletPosition> getAllPositions() {
        return allPositions;
    }
    
    private void initializeModelObjects() {
        initializeGraph();
        initializeMainStorage();
        initializeGates();
        initializeForklifts();
    }
    
    private PalletPosition newPalletPosition(com.amalgamasimulation.warehouse.datamodel.Node scenarioNode, boolean busy) {
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

    public StorageArea getMainStorage() {
        return mainStorage;
    }
    
    private void initializeGates() {
        for (var scenarioGate : scenario.getGates()) {
            final var direction = scenarioGate.getDirection();
            final var entrance = mapping.nodesMap.get(scenarioGate.getEntrance());
            final var places = scenarioGate.getPlaces().stream()
            		.map(scenarioNode -> newPalletPosition(scenarioNode, false))
                    .collect(Collectors.toCollection(LinkedList::new));
            final var gate = new Gate(direction, entrance, new StorageArea(places));
            gates.get(direction).add(gate);
        }
    }
	
    public List<Gate> getGates(Direction direction) {
        return gates.get(direction);
    }
	
	private void initializeForklifts() {
		for (var scenarioForklift : scenario.getForklifts()) {
			forklifts.add(new Forklift(
					engine(),
					getGraphEnvironment(),
					scenarioForklift.getName(),
					mapping.nodesMap.get(scenarioForklift.getBase()),
					scenarioForklift.getVelocity(),
					scenarioForklift.getLoadingTimeSec() * second(),
					scenarioForklift.getUnloadingTimeSec() * second()
					));
		}
	}
	
	public List<Forklift> getForklifts() {
		return forklifts;
	}
	
	public double getForkliftUtilization() {
		final double avgUtilizationTime = forklifts.stream()
				.mapToDouble(f -> f.getUtilizedTime())
				.average().orElse(0);
		
		return Utils.zidz(avgUtilizationTime, time());
	}
	
	private void initializeNodes() {
        for (var scenarioNode : scenario.getNodes()) {
            Node node = new Node(
                    new Point(scenarioNode.getX(), scenarioNode.getY()),
                    graphEnvironment,
                    scenarioNode.getId());
            graphEnvironment.addNode(node);
            mapping.nodesMap.put(scenarioNode, node);
        }
    }	

	private void initializeGraph() {
		for (var scenarioArc : scenario.getArcs()) {
			Polyline polyline = createPolyline(scenarioArc);
			if (polyline.getLength() != 0) {
				Node sourceNode = mapping.nodesMap.get(scenarioArc.getSource());
				Node destNode = mapping.nodesMap.get(scenarioArc.getDest());
				
				Arc forwardArc = new Arc(polyline);
				Arc backwardArc = new Arc(polyline.getReversed());

				forwardArc.setReverseArc(backwardArc);
				backwardArc.setReverseArc(forwardArc);
				graphEnvironment.addArc(sourceNode, destNode, forwardArc, backwardArc);
				mapping.forwardArcsMap.put(scenarioArc, forwardArc);
				this.arcs.add(forwardArc);
				this.arcs.add(backwardArc);
			}
		}
	}


	private Polyline createPolyline(com.amalgamasimulation.warehouse.datamodel.Arc dmArc) {
		List<Point> points = new ArrayList<>();
		points.add(new Point(dmArc.getSource().getX(), dmArc.getSource().getY()));
		dmArc.getPoints().forEach(
				bendpoint -> points.add(new Point(bendpoint.getX(), bendpoint.getY())));
		points.add(new Point(dmArc.getDest().getX(), dmArc.getDest().getY()));
		return new Polyline(Utils.toList(points.stream().distinct()));
	}
	

	public List<Arc> getArcs() {
		return arcs;
	}
	
	public EnvironmentWithPallets getGraphEnvironment() {
        return graphEnvironment;
    }
	
	public double getEndTime() {
		return dateToTime(scenario.getEndDate());
	}

}

