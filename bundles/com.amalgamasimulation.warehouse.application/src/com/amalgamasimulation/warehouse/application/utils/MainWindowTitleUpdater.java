package com.amalgamasimulation.warehouse.application.utils;

import java.time.LocalDateTime;
import java.util.List;

import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.workbench.IWorkbench;

import com.amalgamasimulation.warehouse.application.AppInfo;
import com.amalgamasimulation.warehouse.common.states.AppData;
import com.amalgamasimulation.warehouse.datamodel.Scenario;
import com.amalgamasimulation.utils.format.Formats;


public class MainWindowTitleUpdater {
	
	private MainWindowTitleUpdater() {}

	public static void updateMainWindowTitle(IWorkbench workbench, AppData appData) {
		MApplication application = workbench.getApplication();
		List<MWindow> windows = application.getChildren();
		MWindow mainWindow = windows.get(0);
	
		Scenario scenario = appData.getScenario();
		String [] arr = mainWindow.getLabel().split(" - ");
		String title = arr[0] + " - " + AppInfo.getVersionAsString();	
		if (scenario != null) {
			title = title + ",  " + scenario.getName() + ", " + formatDate(scenario.getBeginDate()) + " - " + formatDate(scenario.getEndDate());
		}
		mainWindow.setLabel(title);
	}
	
	private static String formatDate(LocalDateTime date) {
		return date == null ? "" : Formats.getDefaultFormats().dayMonthYear(date);
	}

}

