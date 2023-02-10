package com.amalgamasimulation.warehouse.application.handlers;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.widgets.Shell;

import com.amalgamasimulation.warehouse.application.states.AppState;
import com.amalgamasimulation.warehouse.common.states.AppData;

public class SaveHandler {

	@Inject
	private AppData appData;

	@Inject
	private AppState appState;

	@CanExecute
	private boolean canExecute() {
		return appState.isEditor() && appState.isScenarioExist();
	}

	@Execute
	public void execute(Shell shell) {
		appState.saveScenario(appData.getFilePath(), appData);
	}
}

