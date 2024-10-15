package com.company.warehouse.simulation;

import java.util.List;
import java.util.Random;

import com.amalgamasimulation.engine.Engine;
import com.amalgamasimulation.geometry.Point;
import com.amalgamasimulation.geometry.Polyline;
import com.amalgamasimulation.graphagent.GraphEnvironment;
import com.amalgamasimulation.utils.Pair;
import com.company.warehouse.simulation.graph.Arc;
import com.company.warehouse.simulation.graph.Node;
import com.company.warehouse.simulation.graph.agents.Agent;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import com.amalgamasimulation.utils.Utils;
import com.company.warehouse.datamodel.Scenario;

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
	protected GraphEnvironment<Node, Arc, Agent> graphEnvironment;
	private Random random = new Random(0);
	private double endTime;
	
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
		engine().setTemporal(scenario.getBeginDate(), ChronoUnit.HOURS);
		setEndTime(dateToTime(scenario.getEndDate()));
		
		// Create a new empty graph environment upon creation of the model
		graphEnvironment = new GraphEnvironment<>();
		// Schedule the dispatch event for the simulation time 0 for all the agents.
		// Note this this event will be executed when the engine running the model starts
		engine.scheduleRelative(0, () -> getAgents().forEach(a -> dispatchAgent(a)));
		initializeNodes();
		initializeArcs();
		initializeAgents();	
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
	public Node addNode(Point point) {
		Node node = new Node(point);
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
			Node node = addNode(new Point(scenarioNode.getX(), scenarioNode.getY()));
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

	private Polyline createPolyline(com.company.warehouse.datamodel.Arc dmArc) {
		List<Point> points = new ArrayList<>();
		points.add(new Point(dmArc.getSource().getX(), dmArc.getSource().getY()));
		dmArc.getPoints().forEach(bendpoint -> points.add(new Point(bendpoint.getX(), bendpoint.getY())));
		points.add(new Point(dmArc.getDest().getX(), dmArc.getDest().getY()));
		return new Polyline(Utils.toList(points.stream().distinct()));
	}
}

