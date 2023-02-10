package com.amalgamasimulation.warehouse.application.parts.editor.treeelements;

import java.util.List;

import com.amalgamasimulation.warehouse.application.utils.TreeElementType;
import com.amalgamasimulation.warehouse.datamodel.Scenario;


public class TreeElementNetwork extends TreeElement {
	private Scenario scenario;
	
	public TreeElementNetwork(Scenario scenario) {
		super(TreeElementType.NETWORK, scenario);
		this.scenario = scenario;
	}
	
	@Override
	protected List<TreeElement> createChildElements() {
		return List.of(
				createLeaf(TreeElementType.NODE, () -> scenario.getNodes().size()),
				createLeaf(TreeElementType.ARC, () -> scenario.getArcs().size())
				);
	}
}

