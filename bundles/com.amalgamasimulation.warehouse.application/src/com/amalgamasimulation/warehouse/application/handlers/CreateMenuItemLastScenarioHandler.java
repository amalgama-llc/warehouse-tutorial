package com.amalgamasimulation.warehouse.application.handlers;

import java.util.List;

import javax.inject.Inject;

import org.eclipse.e4.ui.di.AboutToShow;
import org.eclipse.e4.ui.model.application.ui.menu.MDirectMenuItem;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuElement;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuFactory;

import com.amalgamasimulation.warehouse.application.states.AppState;

public class CreateMenuItemLastScenarioHandler {

	@Inject
	private AppState appState;

	@AboutToShow
	public void aboutToShow(List<MMenuElement> items) {
		String[] paths = appState.recentlyOpenedFilesManager.getLastFilePaths();
		for (String str : paths) {
			if (!str.isEmpty()) {
				items.add(createMenuItem(str));
			}
		}
	}

	private MDirectMenuItem createMenuItem(String label) {
		MDirectMenuItem dynamicItem = MMenuFactory.INSTANCE.createDirectMenuItem();
		dynamicItem.setLabel(label);
		dynamicItem.setContributionURI("bundleclass://com.amalgamasimulation.warehouse.application/com.amalgamasimulation.warehouse.application.handlers.LoadLastScenarioHandler");
		return dynamicItem;
	}

}

