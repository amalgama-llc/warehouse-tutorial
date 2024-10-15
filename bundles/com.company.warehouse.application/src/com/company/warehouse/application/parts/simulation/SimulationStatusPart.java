package com.company.warehouse.application.parts.simulation;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;

import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.core.services.nls.Translation;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ToolBar;

import com.company.warehouse.application.animation.SimulationStatusShape;
import com.company.warehouse.application.handlers.SwitchPerspectiveHandler;
import com.company.warehouse.application.states.AppState;
import com.company.warehouse.application.utils.AppData;
import com.amalgamasimulation.desktop.ui.editor.utils.IconsMapping;
import com.company.warehouse.application.utils.Messages;
import com.amalgamasimulation.desktop.ui.editor.utils.Topics;
import com.company.warehouse.application.utils.PerspectiveUtils.Perspective;
import com.company.warehouse.simulation.ExperimentRun;
import com.company.warehouse.simulation.Model;
import com.amalgamasimulation.desktop.ui.views.ToolBarComposite;
import com.amalgamasimulation.desktop.utils.MessageManager;
import com.amalgamasimulation.desktop.utils.ToolbarUtils;
import com.amalgamasimulation.engine.service.IEngineService;
import com.amalgamasimulation.geometry.Point;
import com.amalgamasimulation.platform.animation.swt.SWT2DSimulationView;
import com.amalgamasimulation.platform.animation.swt.SWT2DSimulationViewImpl;
import com.amalgamasimulation.viewupdater.service.IViewUpdaterService;

public class SimulationStatusPart {

	@Inject
	private MessageManager messageManager;
	
	@Inject
	private IViewUpdaterService viewUpdaterService;
	
	private SWT2DSimulationView animationView;
	
	@Inject
	private IconsMapping iconsMapping;
	
	@Inject
	private EPartService partService;
	
	@Inject
	@Translation
	public Messages messages;

	@Inject
	private AppData appData;
	
	@Inject
	private IEventBroker eventBroker;
	
	@Inject
	private AppState appState;
	
	@Inject
	private EModelService modelService;
	
	@Inject
	private MApplication app;
	
	@Inject
	private IEngineService engineService;

	@PostConstruct
	public void createComposite(Composite parent) {
		new ToolBarComposite(parent, this::initializeToolBar, this::initContents);	
		
	}
	
	private void onShowModel(Model model) {
		animationView.removeAllShapes();
		if (model != null) {
			animationView.addShape(new SimulationStatusShape(	model,
																partClass -> messageManager.activatePart((Class<?>)partClass),
																() -> animationView.updateView()
					).withPoint(new Point(5,5)).withFixedScale(1.25));
		}
		animationView.updateView();
	}
	
	private void initContents(Composite parent) {
		animationView = new SWT2DSimulationViewImpl(parent);
		animationView.setAllowPan(false);
		animationView.setAllowScale(false);
		messageManager.subscribe(Topics.SHOW_MODEL, this::onShowModel, true);
		viewUpdaterService.getDefaultUpdater().addView(animationView);
	}
	
	private void initializeToolBar(Composite parent) {
		ToolBar toolBar = new ToolBar(parent, SWT.HORIZONTAL);
		
		ToolbarUtils.addCommandItem(toolBar, iconsMapping.REFRESH, messages.button_reset, 
				this::resetSimulation)
			.setText(messages.button_reset);
		
		ToolbarUtils.addCommandItem(toolBar, iconsMapping.getImage("/icons/editing.png"), messages.button_editor, 
				() -> SwitchPerspectiveHandler.trySwitchToPerspective(Perspective.EDITOR, parent.getShell(), app, partService, modelService, messageManager, messages, appData, appState, engineService))	
			.setText(messages.button_editor);
	}
	
	public void resetSimulation() {
		engineService.getEngine().reset();
		ExperimentRun experimentRun = new ExperimentRun(appData.getScenario(), engineService.getEngine());
		appState.setCurrentExperiment(experimentRun);
		messageManager.post(Topics.SHOW_MODEL, experimentRun.getModel());
		appState.setRunEnabled(true);
	}
	
}

