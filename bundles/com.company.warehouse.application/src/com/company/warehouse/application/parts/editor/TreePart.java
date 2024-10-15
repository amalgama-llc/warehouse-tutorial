package com.company.warehouse.application.parts.editor;

import java.time.LocalDateTime;
import java.util.List;
import jakarta.inject.Inject;

import org.eclipse.e4.core.services.nls.Translation;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import com.company.warehouse.application.handlers.SwitchPerspectiveHandler;
import com.company.warehouse.application.parts.editor.treeelements.TreeElementScenario;
import com.company.warehouse.application.states.AppState;
import com.amalgamasimulation.desktop.ui.editor.parts.AbstractTreePart;
import com.amalgamasimulation.desktop.ui.editor.utils.TreeElement;
import com.company.warehouse.application.utils.Messages;
import com.company.warehouse.application.utils.PerspectiveUtils.Perspective;
import com.company.warehouse.application.utils.AppData;
import com.company.warehouse.datamodel.DatamodelPackage;
import com.company.warehouse.datamodel.Scenario;
import com.company.warehouse.application.utils.validation.ValidationManager;
import com.amalgamasimulation.desktop.utils.ToolbarUtils;
import com.amalgamasimulation.utils.format.Formats;
import com.amalgamasimulation.desktop.utils.IAppInfo;
import com.amalgamasimulation.engine.service.IEngineService;

public class TreePart extends AbstractTreePart<Scenario> {
	
	@Inject
	@Translation
	private Messages messages;

	@Inject
	private AppData appData;
	
	@Inject
	private AppState appState;
	
	@Inject
	private IAppInfo appInfo;
	
	@Inject
	private IEngineService engineService;
	


	private ToolItem simulationPerspectiveItem;
	
	@Override
	protected void initializeToolBar(Composite parent) {
		ToolBar toolBar = new ToolBar(parent, SWT.HORIZONTAL);
		ToolbarUtils.addCommandItem(toolBar, iconsMapping.REFRESH, messages.toolbar_update, 
				() -> new ValidationManager(messages).validate(messageManager, appData.getScenario(), partService))
		.setText(messages.toolbar_update);
		
		simulationPerspectiveItem = ToolbarUtils.addCommandItem(toolBar, iconsMapping.getImage("/icons/simulation.png"), messages.button_simulation, 
				() -> SwitchPerspectiveHandler.trySwitchToPerspective(Perspective.SIMULATION, parent.getShell(), app, partService, modelService, messageManager, messages, appData, appState , engineService));	
		simulationPerspectiveItem.setText(messages.button_simulation);
		simulationPerspectiveItem.setEnabled(false);
	}
	
		
	@Override
	protected String getMainWindowTitle(MWindow mainWindow) {
		Scenario scenario = appData.getScenario();
		String [] arr = mainWindow.getLabel().split(" - ");
		String title = arr[0] + " - " + appInfo.getVersionAsString();	
		if (scenario != null) {
			title = title + ",  " + scenario.getName() + ", " + formatDate(scenario.getBeginDate()) + " - " + formatDate(scenario.getEndDate());
		}
		return title;
	}
	
	private String formatDate(LocalDateTime date) {
		return date == null ? "" : Formats.getDefaultFormats().dayMonthYear(date);
	}

	@Override
	protected EStructuralFeature[] getUpdateFeaturesForMainWindowTitle() {
		return new EStructuralFeature [] {
				DatamodelPackage.Literals.SCENARIO__NAME,
				DatamodelPackage.Literals.SCENARIO__BEGIN_DATE,
				DatamodelPackage.Literals.SCENARIO__END_DATE};
	}

	@Override
	protected void onNewFileLoaded() {
		super.onNewFileLoaded();
		if(appData.getScenario() != null) {
			simulationPerspectiveItem.setEnabled(true);
		}
	}
	
	@Override
	protected void initializeDependentScenarioListFields(List<EStructuralFeature> list) {
		list.add(DatamodelPackage.Literals.SCENARIO__NODES);
		list.add(DatamodelPackage.Literals.SCENARIO__ARCS);
		list.add(DatamodelPackage.Literals.SCENARIO__AGENTS);
	}

	@Override
	protected Scenario getContainer() {
		return appData.getScenario();
	}

	@Override
	protected TreeElement getParentTreeElement() {
		return new TreeElementScenario(appData.getScenario());
	}
}

