package com.company.warehouse.application.parts.editor.treeelements;

import java.time.temporal.ChronoField;
import java.util.List;

import com.amalgamasimulation.utils.format.Formats;
import com.company.warehouse.application.utils.TreeElementType;
import com.amalgamasimulation.desktop.ui.editor.utils.TreeElement;
import com.company.warehouse.datamodel.Scenario;


public class TreeElementScenario extends TreeElement {
	private Scenario scenario;

	public TreeElementScenario(Scenario scenario) {
		super(TreeElementType.SCENARIO, scenario);
		this.scenario = scenario;
	}

	@Override
	public String getLabel() {
		return treeElementType.getLabel() + 
				" " + Formats.getDefaultFormats().dayMonthLongYear(scenario.getBeginDate()) + 
				" - " + Formats.getDefaultFormats().dayMonthLongYear(scenario.getEndDate()) +
				", " + (scenario.getEndDate().getLong(ChronoField.EPOCH_DAY) - scenario.getBeginDate().getLong(ChronoField.EPOCH_DAY)) + " days";
	}

	@Override
	protected List<TreeElement> createChildElements() {
		return List.of(new TreeElementNetwork(scenario),
createLeaf(TreeElementType.AGENT, () -> scenario.getAgents().size()));
	}
	
}

