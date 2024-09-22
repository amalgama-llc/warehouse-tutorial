package com.company.warehouse.simulation.graph;

import com.amalgamasimulation.geometry.Point;
import com.amalgamasimulation.graphagent.AgentGraphNodeImpl;

/**
 * This class represents a node of the network. It extends the AgentGraphNodeImpl
 * class from Amalgama Platform's Graph Agent Library
 *
 */
public class Node extends AgentGraphNodeImpl {
	public Node(Point point) {
		super(point);
	}
}

