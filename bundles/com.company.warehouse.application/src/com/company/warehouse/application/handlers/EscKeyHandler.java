package com.company.warehouse.application.handlers;

import jakarta.inject.Inject;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

import com.amalgamasimulation.desktop.ui.editor.utils.Topics;


public class EscKeyHandler {

	@Inject
	private IEventBroker eventBroker;

	@Execute
	public void execute(MApplication app, EPartService partService, EModelService modelService) {
		eventBroker.send(Topics.ESC_KEY_TYPE, null);
	}
	
}

