package com.amalgamasimulation.warehouse.simulation;

import com.amalgamasimulation.warehouse.simulation.graph.Arc;
import com.amalgamasimulation.warehouse.simulation.graph.Node;
import com.amalgamasimulation.utils.container.BiMap;

public class Mapping {

		public BiMap<com.amalgamasimulation.warehouse.datamodel.Node, Node> nodesMap = new BiMap<>();
		public BiMap<com.amalgamasimulation.warehouse.datamodel.Arc, Arc> forwardArcsMap = new BiMap<>();

}

