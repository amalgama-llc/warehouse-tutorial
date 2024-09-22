package com.company.warehouse.simulation.graph.agents.stats.slots;

import com.company.warehouse.simulation.graph.agents.Agent;
import com.company.warehouse.simulation.graph.agents.Agent.AgentState;

/**
 * Slot representing a time interval when an agent was moving with its base
 * velocity
 *
 */
public class MovingSlot extends AgentStatsSlot {

	public MovingSlot(double beginTime, Agent agent, AgentState state) {
		super(beginTime, agent, state);
	}
}

