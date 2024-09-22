package com.company.warehouse.simulation;

import com.company.warehouse.simulation.graph.Arc;
import com.company.warehouse.simulation.graph.Node;
import com.company.warehouse.simulation.graph.agents.Agent;
import com.amalgamasimulation.utils.container.BiMap;

/**
 * This class represents the 2-way mapping between data model entities and
 * simulation model entities.
 *
 */
public class Mapping {

		public BiMap<com.company.warehouse.datamodel.Node, Node> nodesMap = new BiMap<>();
		public BiMap<com.company.warehouse.datamodel.Arc, Arc> forwardArcsMap = new BiMap<>();
		public BiMap<com.company.warehouse.datamodel.Agent, Agent> agentsMap = new BiMap<>();

}

