package com.company.warehouse.simulation.graph.agents;

import static com.company.warehouse.simulation.graph.agents.Agent.AgentState.BYPASSING;
import static com.company.warehouse.simulation.graph.agents.Agent.AgentState.MOVING;

import java.util.ArrayList;
import java.util.List;

import com.amalgamasimulation.engine.Engine;
import com.amalgamasimulation.engine.StateMachine;
import com.amalgamasimulation.graphagent.GeometricGraphPosition;
import com.amalgamasimulation.graphagent.GraphAgent;
import com.amalgamasimulation.utils.TimeStatistics;
import com.company.warehouse.simulation.graph.Arc;
import com.company.warehouse.simulation.graph.Node;
import com.company.warehouse.simulation.graph.agents.stats.slots.AgentStatsSlot;
import com.company.warehouse.simulation.graph.agents.stats.slots.BypassingSlot;
import com.company.warehouse.simulation.graph.agents.stats.slots.MovingSlot;

/**
 * This class represents an agent that lives in our simulation model. It extends
 * the GraphAgent class from Amalgama Platform's Graph Agent Library
 *
 */
public class Agent extends GraphAgent<Node, Arc> {
	
	// Our agent can be in the 2 states - either it is moving with its base velocity,
	// or it is bypassing another agent and thus moving with the reduced velocity
	public enum AgentState {
		MOVING,
		BYPASSING;
	}
	
	// This is the factor by which the agent's velocity will be multiplied
	// when it is passing by another agent
	protected final double BYPASSING_VELOCITY_MODIFIER = 0.1;
	
	// This is the duration of bypassing after meeting another agent
	protected final double BYPASSING_DURATION = 30;
	
	// Agent's name
	protected String name;
	
	// Base velocity of the agent
	protected double baseVelocity;
	
	// Current velocity of the agent, can be either its base velocity or
	// velocity reduced due to bypassing
	protected double currentVelocity;
	
	// The node to which the agent gets placed at the beginning of the simulation
	protected Node initialNode;
	
	// State machine responsible for management of switching between agent's states
	protected StateMachine<AgentState> control;

	// This TimeStatistics object is used to collect statistics on the
	// agent's bypasses count with 1 hour granularity
	protected TimeStatistics bypassesCountStatistics;
	
	// This list accumulates the statistics used to show the history
	// of agent's state changes on the gantt chart
	protected List<AgentStatsSlot> statsSlots = new ArrayList<>();
	
	// This is the callback called every time agent reaches its current
	// destination
	protected Runnable destinationArrivalHandler;
	
	public Agent(Engine engine, String name, Node initialNode, double baseVelocity) {
		super(engine);
		this.name = name;
		this.initialNode = initialNode;
		this.baseVelocity = baseVelocity;
		this.currentVelocity = baseVelocity;		
		this.bypassesCountStatistics = new TimeStatistics(engine.hour());
		
		// We define the state machine by specifying its
		// states, transitions, and state change actions
		control = new StateMachine<AgentState>(AgentState.values(), MOVING, engine)
				.addTransition(MOVING, BYPASSING)
				.addTransition(BYPASSING, MOVING)
				.addEnterAction(BYPASSING, this::onBypassingEntered)
				.addEnterAction(MOVING, this::onMovingEntered)
				.addEnterAction((state, message) -> onEnteredState(state, engine));
		
		onEnteredState(MOVING, engine);
	}
	
	public void setDestinationArrivalHandler(Runnable destinationArrivalHandler) {
		this.destinationArrivalHandler = destinationArrivalHandler;
	}
	
	public TimeStatistics getBypassesCountStatistics() {
		return bypassesCountStatistics;
	}
	
	public void moveTo(Node position) {
		moveTo(position, currentVelocity);
	}
	
	public double getCurrentVelocity() {
		return currentVelocity;
	}
	
	public double getBaseVelocity() {
		return baseVelocity;
	}
	
	public int getBypassesCount() {
		return (int) bypassesCountStatistics.totalStatistics().getSum();
	}
	
	public List<AgentStatsSlot> getStatsSlots() {
		return statsSlots;
	}
	
	@Override
	public void onDestinationReached(GeometricGraphPosition<Node, Arc> destPosition) {
		super.onDestinationReached(destPosition);
		destinationArrivalHandler.run();
	}
	
	/**
	 * This callback method of GraphAgent base class from Graph Agent Library is
	 * called when this agent reaches another agent. In this case, we initiate the
	 * bypassing process by changing the agent's state.
	 */
	@Override
	public void onOtherAgentReached(GraphAgent<Node, Arc> otherAgent, boolean isMovingInSameDirection) {
		super.onOtherAgentReached(otherAgent, isMovingInSameDirection);
		if (hasPath()) {
			control.receiveMessage(BYPASSING);
			engine.scheduleRelative(BYPASSING_DURATION * engine.minute(), () -> control.receiveMessage(MOVING));
		}
	}
	
	/**
	 * Method that is executed every time the agent enters into BYPASSING state
	 */
	private void onBypassingEntered(Object msg) {
		// Calculate the reduced velocity and assign it to the currentVelocity
		// variable
		currentVelocity = baseVelocity * BYPASSING_VELOCITY_MODIFIER;
		// Change agent's velocity
		setVelocity(currentVelocity);
		// Increase the number of bypasses in the statistics object
		bypassesCountStatistics.add(engine.time(), 1);
	}
	
	/**
	 * Method that is executed every time the agent enters into MOVING state
	 */
	private void onMovingEntered(Object msg) {
		// Return the agent to its base velocity
		currentVelocity = baseVelocity;
		setVelocity(currentVelocity);
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	private AgentStatsSlot onEnteredState(AgentState state, Engine engine) {
		AgentStatsSlot newSlot = state == MOVING ? new MovingSlot(engine.time(), this, state) : new BypassingSlot(engine.time(), this, state);
		if (!statsSlots.isEmpty()) {
			AgentStatsSlot lastSlot = statsSlots.get(statsSlots.size() - 1);
			if (lastSlot.duration() > 0) {
				lastSlot.close();
				statsSlots.add(newSlot);
			} else {
				statsSlots.set(statsSlots.size() - 1, newSlot);
			}
		} else {
			statsSlots.add(newSlot);
		}
		return newSlot;
	}
	
	public AgentStatsSlot getCurrentStatsSlot() {
		return statsSlots.isEmpty() ? null : statsSlots.get(statsSlots.size() - 1);
	}
}

