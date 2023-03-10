package com.amalgamasimulation.warehouse.application.scenario;

import java.io.File;
import java.time.LocalDateTime;
import com.amalgamasimulation.warehouse.application.AppInfo;
import com.amalgamasimulation.warehouse.application.utils.FileUtils;
import com.amalgamasimulation.warehouse.common.localization.Messages;
import com.amalgamasimulation.warehouse.datamodel.DatamodelFactory;
import com.amalgamasimulation.warehouse.datamodel.Scenario;

public class ScenarioCreator {

	private ScenarioCreator() {
	}

	/**
	 * Creates a new scenario and saves it in EXCEL format
	 * 
	 * @return file path to created scenario
	 */
	public static String createNew(Messages messages) {
		String newScenarioFolderPath = System.getProperty("user.home") + File.separator + AppInfo.getProductID();
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
		Scenario scenario = ScenarioCreator.createScenario(name, messages);
		ScenarioSaver.save(scenario, fullPath);
		return fullPath;
	}

	private static Scenario createScenario(String name, Messages messages) {
		LocalDateTime beginModeling = LocalDateTime.now();
		Scenario scenario = DatamodelFactory.eINSTANCE.createScenario();
		scenario.setName(name);
		scenario.setBeginDate(beginModeling);
		scenario.setEndDate(beginModeling.plusDays(7));

		return scenario;
	}
}

