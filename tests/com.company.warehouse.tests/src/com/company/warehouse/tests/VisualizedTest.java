package com.company.warehouse.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.company.warehouse.tests.utils.AbstractVisualizedTest;

@DisplayName("First set of visualized tests")
public class VisualizedTest extends AbstractVisualizedTest {
	@Test
	@DisplayName("The model with 4 agents can be initialized from a scenario file and run")
	public void test_model_loaded_from_scenario() {
		loadScenario("DemoScenario.xlsx");
		
		runEngine();
		assertEquals(4, getModel().getAgents().size(), "The model has been initialized from scenario file and has 4 agents in it");		
	}

}

