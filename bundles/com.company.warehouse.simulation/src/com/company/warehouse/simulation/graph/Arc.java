package com.company.warehouse.simulation.graph;

import com.amalgamasimulation.geometry.Polyline;
import com.amalgamasimulation.graphagent.AgentGraphArcImpl;

/**
 * This class represents an arc of the network. It extends the AgentGraphArcImpl
 * class from Amalgama Platform's Graph Agent Library
 *
 */
@SuppressWarnings("serial")
public class Arc extends AgentGraphArcImpl {
	public Arc(Polyline polyline) {
		super(polyline);
	}
}

