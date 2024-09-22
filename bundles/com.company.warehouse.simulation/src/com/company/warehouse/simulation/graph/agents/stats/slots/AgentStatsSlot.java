package com.company.warehouse.simulation.graph.agents.stats.slots;

import com.amalgamasimulation.core.scheduling.Slot;
import com.company.warehouse.simulation.graph.agents.Agent;
import com.company.warehouse.simulation.graph.agents.Agent.AgentState;

/**
 * Base class for all slots that represent that some agent was in some state
 * during the certain time. Such slots are used, for example, to show Gantt
 * charts for the agents
 *
 */
public abstract class AgentStatsSlot extends Slot {

	private boolean closed;
	private Agent agent;
	private AgentState state;
	
	public AgentStatsSlot(double beginTime, Agent agent, AgentState state) {
		super(beginTime, beginTime);
		this.agent = agent;
		this.state = state;
	}
	
	@Override
	public double endTime() {
		return Math.max(beginTime(), closed ? super.endTime() : agent.time());
	}

	public void close() {
		max = agent.time();
		closed = true;		
	}
	
	public AgentState getState() {
		return state;
	}
}

