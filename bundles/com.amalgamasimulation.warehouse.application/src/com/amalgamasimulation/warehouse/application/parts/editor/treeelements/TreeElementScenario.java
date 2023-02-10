package com.amalgamasimulation.warehouse.application.parts.editor.treeelements;

import java.time.temporal.ChronoField;
import java.util.List;

import com.amalgamasimulation.utils.format.Formats;
import com.amalgamasimulation.warehouse.application.utils.TreeElementType;
import com.amalgamasimulation.warehouse.common.localization.Messages;
import com.amalgamasimulation.warehouse.datamodel.Scenario;


public class TreeElementScenario extends TreeElement {
	private Scenario scenario;
	private Messages messages;

	public TreeElementScenario(Scenario scenario, Messages messages) {
		super(TreeElementType.SCENARIO, scenario);
		this.scenario = scenario;
		this.messages = messages;
	}

	@Override
	public String getLabel() {
		return messages.obj_SCENARIO + 
				" " + Formats.getDefaultFormats().dayMonthLongYear(scenario.getBeginDate()) + 
				" - " + Formats.getDefaultFormats().dayMonthLongYear(scenario.getEndDate()) +
				", " + (scenario.getEndDate().getLong(ChronoField.EPOCH_DAY) - scenario.getBeginDate().getLong(ChronoField.EPOCH_DAY)) + " days";
	}

	@Override
	protected List<TreeElement> createChildElements() {
		return List.of(new TreeElementNetwork(scenario));
	}
	
}

