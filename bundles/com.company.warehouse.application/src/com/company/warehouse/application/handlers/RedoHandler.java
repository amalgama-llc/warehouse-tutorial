package com.company.warehouse.application.handlers;

import jakarta.inject.Inject;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;

import com.amalgamasimulation.emf.commands.CommandsManager;

import com.company.warehouse.application.states.AppState;

public class RedoHandler {
	
	@Inject
	private AppState appState;
	
	@CanExecute
	private boolean canExecute() {
		return appState.isEditor() && appState.isScenarioExist() && CommandsManager.getEditingDomain().getCommandStack().canRedo();
	}
	
	@Execute
	public void execute() {
		CommandsManager.getEditingDomain().getCommandStack().redo();
	}
}

