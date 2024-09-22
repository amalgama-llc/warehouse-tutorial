package com.company.warehouse.simulation.experiments;

import com.amalgamasimulation.engine.Engine;
import com.company.warehouse.datamodel.Scenario;
import com.company.warehouse.simulation.Model;

public class SensitivityAnalysisExperiment extends Experiment {
	
	private String name;
	private Scenario scenario;

	public SensitivityAnalysisExperiment(String name, Scenario scenario) {
		this.name = name;
		this.scenario = scenario;
	}
	
	public Scenario getScenario() {
		return scenario;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public Model createModel() {
		return new Model(new Engine(), scenario);
	}
	
}

