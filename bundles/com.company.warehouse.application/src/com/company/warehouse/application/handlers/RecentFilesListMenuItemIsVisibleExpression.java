package com.company.warehouse.application.handlers;

import jakarta.inject.Inject;

import org.eclipse.e4.core.di.annotations.Evaluate;

import com.company.warehouse.application.states.AppState;


public class RecentFilesListMenuItemIsVisibleExpression {
	
	@Inject
	private AppState appState;
	
	@Evaluate
	public boolean evaluate() {
		return appState.isEditor() && appState.getRecentlyOpenedFilesManager().getLastFilePaths().length > 0;
	}
}

