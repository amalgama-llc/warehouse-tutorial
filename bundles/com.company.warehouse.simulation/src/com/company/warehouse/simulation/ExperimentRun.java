package com.company.warehouse.simulation;

import com.company.warehouse.datamodel.Scenario;
import com.amalgamasimulation.engine.Engine;

/**
 * Class representing a single run of the simulation model created from a data
 * model scenario performed by an external engine. Used in single simulation run
 * mode.
 *
 */
public class ExperimentRun {
	private final Model model;
	private final Scenario scenario;
	private final Engine engine;
	
	public ExperimentRun(Scenario scenario, Engine engine) {
		this.scenario = scenario;
		this.engine = engine;
		this.model = new Model(engine, scenario);
	}

	public Model getModel() {
		return model;
	}

	public Scenario getScenario() {
		return scenario;
	}

	public Engine getEngine() {
		return engine;
	}
}

