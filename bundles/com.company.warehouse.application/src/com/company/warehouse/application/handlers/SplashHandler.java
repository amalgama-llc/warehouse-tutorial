package com.company.warehouse.application.handlers;

import jakarta.inject.Inject;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.core.services.nls.Translation;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.workbench.UIEvents;
import org.eclipse.e4.ui.workbench.lifecycle.PostContextCreate;
import org.eclipse.e4.ui.workbench.lifecycle.ProcessAdditions;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.IWindowCloseHandler;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

import com.amalgamasimulation.desktop.utils.IAppInfo;
import com.company.warehouse.application.states.AppState;
import com.company.warehouse.application.utils.CurrentLocale;
import com.company.warehouse.application.utils.MessageBoxFactory;
import com.company.warehouse.application.utils.PerspectiveUtils;
import com.company.warehouse.application.utils.Messages;
import com.amalgamasimulation.localization.Util;

public class SplashHandler {
	
	@Inject
	@Translation
	private Messages messages;

	@Inject
	private IAppInfo appInfo;

	@Inject
	private AppState appState;

	@Inject
	private IEventBroker eventBroker;

	@PostContextCreate
	void postContextCreate(IEclipseContext context) {
		final String locale = CurrentLocale.getCurrentLocale(context);	
		appState.setCurrentLanguage(locale);
	}

	@ProcessAdditions
	public void processAdditions(MApplication app, EModelService modelService, EPartService partService) {
		PerspectiveUtils.copyAllPerspectivesFromSnippets(modelService, app);
		
		MWindow mainWindow = PerspectiveUtils.getMainWindow(modelService, app);
		PerspectiveUtils.Perspective perspective = PerspectiveUtils.getVisiblePerspective(mainWindow);
		PerspectiveUtils.setVisibleForModelingToolBar(perspective.engineToolBarIsVisible, modelService, mainWindow);
		appState.setCurrentPerspective(perspective);		
		mainWindow.setLabel(appInfo.getNameAndVersion());
		Util.APPLICATION_NAME = "Warehouse";
			

		eventBroker.subscribe(UIEvents.UILifeCycle.APP_STARTUP_COMPLETE, event -> {

			partService.activate(partService.findPart("com.company.warehouse.application.part.properties"));
			partService.activate(partService.findPart("com.company.warehouse.application.part.errors"));
			partService.activate(partService.findPart("com.company.warehouse.application.part.objects"));

			mainWindow.getContext().set(IWindowCloseHandler.class, w -> {
				// ask if user wants to close the app
				if (SWT.OK != MessageBoxFactory.createMessageBox(new Shell(), SWT.ICON_QUESTION | SWT.OK | SWT.CANCEL | SWT.APPLICATION_MODAL, "",
						messages.message_create_new_scenario)) {
					return false;
				}
				// ask if user wants the (changed) scenario to be saved
				return appState.ensureCurrentScenarioIsSaved(new Shell());
			});
			
			appState.createAndSetNewScenario();
		});
	}

}

