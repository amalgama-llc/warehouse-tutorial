package com.company.warehouse.simulation.experiments;

import java.io.File;
import java.util.function.Supplier;

import com.amalgamasimulation.engine.Engine;
import com.company.warehouse.datamodel.Scenario;
import com.company.warehouse.simulation.Model;

public class ScenarioComparisonExperiment extends Experiment {

	private String filePath;
	private Supplier<Scenario> scenarioExtractor;
	
	public ScenarioComparisonExperiment(String filePath, Supplier<Scenario> scenarioExtractor) {
		this.filePath = filePath;
		this.scenarioExtractor = scenarioExtractor;
	}
	
	public String getFilePath() {
		return filePath;
	}
	
	public String getFileName() {
		return new File(filePath).getName();
	}
	
	@Override
	public Model createModel() {
		return new Model(new Engine(), scenarioExtractor.get());
	}
}

