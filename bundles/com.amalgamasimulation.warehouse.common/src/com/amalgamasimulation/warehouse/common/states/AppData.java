package com.amalgamasimulation.warehouse.common.states;


import javax.inject.Inject;
import javax.inject.Singleton;

import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.e4.core.services.nls.Translation;

import com.amalgamasimulation.warehouse.common.localization.Messages;
import com.amalgamasimulation.warehouse.datamodel.Scenario;

@Singleton
@Creatable
public class AppData {
	private Scenario scenario;
	private String filePath;

	@Inject
	@Translation
	public static Messages messages;
	
	public Scenario getScenario() {
		return scenario;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public void setScenario(Scenario scenario) {
		this.scenario = scenario;
	}

}

