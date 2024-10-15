package com.company.warehouse.application.utils;

import com.amalgamasimulation.desktop.ui.editor.utils.AbstractTreeElementType;

public class TreeElementType  extends AbstractTreeElementType {
	public static TreeElementType NETWORK = new TreeElementType("/icons/transportation_net.png", AppData.messages.tree_element_network, false);
	public static TreeElementType ARC = new TreeElementType("/icons/link.png", AppData.messages.tree_element_arcs, false);
	public static TreeElementType NODE = new TreeElementType("/icons/location.png", AppData.messages.tree_element_nodes, false);
	public static TreeElementType AGENT = new TreeElementType("/icons/agent.png", AppData.messages.tree_element_agents, false);
	public static TreeElementType SCENARIO = new TreeElementType("/icons/producte_structure.png", AppData.messages.tree_element_scenario, true);
	
	
	public TreeElementType(String iconPath, String label, boolean existProperty) {
		super(iconPath, label, existProperty);
	}
}
	

