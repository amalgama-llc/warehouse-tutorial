package com.amalgamasimulation.warehouse.application.handlers;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.services.nls.Translation;
import org.eclipse.e4.ui.workbench.IWorkbench;

import com.amalgamasimulation.warehouse.application.states.AppState;
import com.amalgamasimulation.warehouse.application.utils.ChangeLanguageManager;
import com.amalgamasimulation.warehouse.common.localization.Messages;

public class SetLanguageHandler {

	@Inject
	@Translation
	protected Messages messages;

	@Inject
	private AppState appState;

	@CanExecute
	private boolean canExecute() {
		return true;
	}

	@Execute
	public void execute(@Named("com.amalgamasimulation.warehouse.application.commandparameter.parameter1") String language, IWorkbench application) {
		appState.setCurrentLanguage(language);
		new ChangeLanguageManager(messages).setLanguage(application, language);
	}

}

