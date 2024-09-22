package com.company.warehouse.application.scenario;

import java.io.File;
import java.time.LocalDateTime;
import java.time.LocalTime;
import com.amalgamasimulation.desktop.utils.IAppInfo;
import com.company.warehouse.application.utils.FileUtils;
import com.company.warehouse.datamodel.DatamodelFactory;
import com.company.warehouse.datamodel.Scenario;

public class ScenarioCreator {

	private ScenarioCreator() {
	}

	/**
	 * Creates a new scenario and saves it in EXCEL format
	 * 
	 * @return file path to created scenario
	 */
	public static String createNew(IAppInfo appInfo) {
		String newScenarioFolderPath = System.getProperty("user.home") + File.separator + appInfo.getProductID();
		File newScenarioFolder = new File(newScenarioFolderPath);
		newScenarioFolder.mkdirs();

		String name;
		String fullPath;
		int fileIndex = 0;
		do {
			name = "New Scenario " + (fileIndex++) + ScenarioFileFormat.EXCEL.fileDotExtension;
			fullPath = newScenarioFolderPath + File.separator + name;
		} while (FileUtils.checkIsFileAndExists(fullPath));
		FileUtils.createFile(fullPath);
		Scenario scenario = ScenarioCreator.createScenario(name);
		ScenarioSaver.save(scenario, fullPath);
		return fullPath;
	}

	private static Scenario createScenario(String name) {
		LocalDateTime beginModeling = LocalDateTime.now().with(LocalTime.MIDNIGHT);
		Scenario scenario = DatamodelFactory.eINSTANCE.createScenario();
		scenario.setName(name);
		scenario.setBeginDate(beginModeling);
		scenario.setEndDate(beginModeling.plusDays(7));

		return scenario;
	}
}

