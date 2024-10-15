package com.company.warehouse.application.scenario;

import java.time.LocalDateTime;
import java.time.LocalTime;
import com.company.warehouse.datamodel.DatamodelFactory;
import com.company.warehouse.datamodel.Scenario;

public class ScenarioCreator {
	
	private static int createdScenariosCount = 0;

	private ScenarioCreator() {
	}


	/**
	 * Creates and returns a new scenario.
	 */
	public static Scenario createScenario() {
		LocalDateTime beginModeling = LocalDateTime.now().with(LocalTime.MIDNIGHT);
		String name = "New Scenario " + (createdScenariosCount++);
		Scenario scenario = DatamodelFactory.eINSTANCE.createScenario();
		scenario.setName(name);
		scenario.setBeginDate(beginModeling);
		scenario.setEndDate(beginModeling.plusDays(7));

		return scenario;
	}
}

