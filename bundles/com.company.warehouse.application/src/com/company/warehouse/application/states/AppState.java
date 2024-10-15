package com.company.warehouse.application.states;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.core.services.nls.Translation;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspective;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.amalgamasimulation.desktop.utils.IAppInfo;
import com.amalgamasimulation.desktop.ui.dialogs.DialogUtils;
import com.amalgamasimulation.desktop.ui.dialogs.ProgressDialog;
import com.amalgamasimulation.desktop.ui.dialogs.ResourceErrorsViewDialog;
import com.amalgamasimulation.desktop.utils.RecentlyOpenedFilesManager;
import com.amalgamasimulation.emf.commands.CommandsManager;
import com.amalgamasimulation.emf.excel.EMFExcelLoader;
import com.amalgamasimulation.emf.excel.EMFExcelTransform;
import com.amalgamasimulation.emf.excel.ColumnHeaderProvider;
import com.company.warehouse.application.scenario.ScenarioCreator;
import com.company.warehouse.application.scenario.ScenarioFileFormat;
import com.company.warehouse.application.scenario.ScenarioLoader;
import com.company.warehouse.application.scenario.ScenarioSaver;
import com.company.warehouse.application.scenario.ScenarioSaver.ScenarioSavingException;
import com.company.warehouse.application.utils.FileUtils;
import com.company.warehouse.datamodel.data.EMFExcelDataTransform;
import com.company.warehouse.application.utils.MessageBoxFactory;
import com.company.warehouse.application.utils.PerspectiveUtils;
import com.company.warehouse.application.utils.validation.ObjectType;
import com.company.warehouse.datamodel.DatamodelPackage;
import com.amalgamasimulation.desktop.ui.editor.utils.Topics;
import com.amalgamasimulation.desktop.ui.editor.utils.ICommandFactory;
import org.eclipse.e4.ui.workbench.UIEvents;
import com.company.warehouse.application.utils.Messages;
import com.company.warehouse.application.utils.AppData;
import com.company.warehouse.datamodel.Scenario;
import com.company.warehouse.simulation.ExperimentRun;

@Singleton
@Creatable
public class AppState {

	private String currentLanguage;
	private boolean runEnabled = true;
	private PerspectiveUtils.Perspective currentPerspective;
	private final EMFExcelTransform<Scenario> emfExcelTransform = EMFExcelDataTransform.getExcelTransform();
	private ExperimentRun currentExperiment;
	
	private boolean actualPlanned = false;
	
	@Inject
	private IEventBroker eventBroker;
	
	@Inject
	@Translation
	private Messages messages;
	
	@Inject
	private AppData appData;
	
	@Inject
	private IAppInfo appInfo;
	
	@Inject
	private ICommandFactory commandFactory;
		
	private RecentlyOpenedFilesManager recentlyOpenedFilesManager;
	
	@PostConstruct
	void postConstruct() {
		emfExcelTransform.setColumnHeaderProvider(createColumnHeaderProvider(messages));
	}
	
	private ColumnHeaderProvider createColumnHeaderProvider(Messages messages) {
		var columnHeaderProvider = new ColumnHeaderProvider();
		for (ObjectType objectType : ObjectType.values()) {
			objectType.getFeatures().forEach(f -> columnHeaderProvider.addMapping(objectType.getEClass(), f, objectType.getColumnNameExcel(f)));
		}
		columnHeaderProvider
				.addMapping(DatamodelPackage.eINSTANCE.getNode(), null, messages.obj_NODE_col_ID_excel)
				.addMapping(DatamodelPackage.eINSTANCE.getArc(), null, messages.obj_ARC_col_ID_excel)
				;
		
		return columnHeaderProvider;
	}
	
	public RecentlyOpenedFilesManager getRecentlyOpenedFilesManager() {
		if (recentlyOpenedFilesManager == null) {
			recentlyOpenedFilesManager = new RecentlyOpenedFilesManager(appInfo.getProductID(), 5);
		}
		return recentlyOpenedFilesManager;
	}
	
	public PerspectiveUtils.Perspective getCurrentPerspective() {
		return currentPerspective;
	}	
	
	public void setCurrentPerspective(PerspectiveUtils.Perspective currentPerspective) {
		this.currentPerspective = currentPerspective;
	}

	public boolean isRunEnabled() {
		return runEnabled;
	}

	public void setRunEnabled(boolean runEnabled) {
		this.runEnabled = runEnabled;
	}

	public void switchPerspective(PerspectiveUtils.Perspective newPerspective, MApplication app, EPartService partService, EModelService modelService, MWindow mainWindow) {
		PerspectiveUtils.setVisibleForModelingToolBar(newPerspective.engineToolBarIsVisible, modelService, mainWindow);
		MPerspective element = (MPerspective) modelService.find(newPerspective.id, app);
		partService.switchPerspective(element);
		currentPerspective = newPerspective;
	}

	public boolean isEditor() {
		return currentPerspective  == PerspectiveUtils.Perspective.EDITOR;
	}

	public EMFExcelTransform<Scenario> getEmfExcelTransform() {
		return emfExcelTransform;
	}

	public boolean isActualPlanned() {
		return actualPlanned;
	}

	public void setActualPlanned(boolean actualPlanned) {
		this.actualPlanned = actualPlanned;
	}
	
	public String getCurrentLanguage() {
		return currentLanguage;
	}

	public void setCurrentLanguage(String currentLanguage) {
		this.currentLanguage = currentLanguage;
	}
	
	public boolean isRussian() {
		return currentLanguage.equals("ru");
	}
	
	public boolean isScenarioExist() {
		return appData.getScenario() != null;
	}
	
	public ExperimentRun getCurrentExperiment() {
		return currentExperiment;
	}

	public void setCurrentExperiment(ExperimentRun currentExperiment) {
		this.currentExperiment = currentExperiment;
	}
	
	public void saveIsDone( ) {
		BasicCommandStack b = (BasicCommandStack)CommandsManager.getEditingDomain().getCommandStack();	
		b.saveIsDone();
		setActualPlanned(false);
	}
	
	/**
	 * Checks whether the current scenario is changed. If yes - asks the user to
	 * save the scenario and saves it (after user approval).
	 * 
	 * @param shell
	 * @param appData
	 * @return true if it is safe to proceed (scenario does not need saving, or user
	 *         answered 'YES' or 'NO'); false if the scenario is changed, but user
	 *         answered 'CANCEL' and wants to do something with the scenario
	 */
	public boolean ensureCurrentScenarioIsSaved(Shell shell) {
		if (ScenarioSaver.isSaveNeeded()) {
			// scenario requires saving
			switch (askUserToSaveScenario(shell, appData, AppData.messages)) {
			case SWT.YES:
				// user has agreed to save the scenario
				return saveScenario(shell, appData.getFilePath());
			case SWT.NO:
				// user does not want the scenario to be saved, but it is ok to proceed with other tasks
				return true;
			case SWT.CANCEL:
				// the user does not want to proceed with other tasks, scenario is not saved yet
				return false;
			default:
				// unknown case
				return false;
			}
		}
		// scenario is saved before, user can go on with other tasks
		return true;
	}
    
    private int askUserToSaveScenario(Shell shell, AppData appData, Messages messages) {
        return MessageBoxFactory.createMessageBox(shell,
                                      SWT.ICON_QUESTION | SWT.YES | SWT.NO | SWT.CANCEL | SWT.APPLICATION_MODAL,
                                      messages.title_exit,
                                      String.format(messages.message_save_old_scenario,
                                                    appData.getScenario().getName()));
    }
    
	/**
	 * Load scenario from a file. A progress dialog is shown during file loading.
	 */
 	public Scenario loadScenario(String filePath) {
 		AtomicReference<Scenario> scenarioRef = new AtomicReference<>();
 		AtomicReference<EMFExcelLoader<Scenario>> loaderRef = new AtomicReference<>();
 		AtomicBoolean isExistErrorsFromEMF = new AtomicBoolean(false);
 		
 		if (!FileUtils.checkIsFileAndExists(filePath)) {
			MessageBoxFactory.createMessageBox(new Shell(), SWT.ICON_ERROR | SWT.OK | SWT.APPLICATION_MODAL,
					AppData.messages.title_error, AppData.messages.message_error_check_filepath);
 			return null;
 		}
		
		ProgressDialog.execute(AppData.messages.title_open_scenario, () -> {
			loaderRef.set(ScenarioLoader.loadExcel(emfExcelTransform, filePath));
			if (!loaderRef.get().getWarnings().isEmpty() || !loaderRef.get().getErrors().isEmpty()) {
				isExistErrorsFromEMF.set(true);
			} else {
				scenarioRef.set(loaderRef.get().getRootObject());
			}
			
			if (scenarioRef.get() != null) {				
				setApplicationScenario(filePath, scenarioRef.get());
			}
		});
		
		if (isExistErrorsFromEMF.get()) {
			new ResourceErrorsViewDialog(
					Display.getDefault().getActiveShell(), 
					AppData.messages.title_resource_errors_view_dialog, 
					AppData.messages.message_resource_errors_view_dialog, 
					loaderRef.get().getErrors(), 
					loaderRef.get().getWarnings())
				.open();
		}
		
		return scenarioRef.get();
	}
 	
	/**
	 * Creates a new scenario, sets is as the current application scenario, and also
	 * resets the scenario file path (makes it null).
	 */
 	public void createAndSetNewScenario() {
 		Scenario scenario = ScenarioCreator.createScenario();
 		setApplicationScenario(null, scenario);
 	}
	
	private void setApplicationScenario(String filePath, Scenario scenario) {
		CommandsManager.getEditingDomain().getCommandStack().flush();
		appData.setFilePath(filePath);	// may be null - this means when a new scenario is created
		appData.setScenario(scenario);
		//appData.getScenario().setName(new File(filePath).getName());
		setActualPlanned(false);
		if (filePath != null) {
			recentlyOpenedFilesManager.saveString(filePath, RecentlyOpenedFilesManager.PathType.FILE);
		}
		commandFactory.getCrossReferenceAdapter().setTarget(scenario);
		CommandsManager.getEditingDomain().getCommandStack().addCommandStackListener(l -> {
			eventBroker.send(UIEvents.REQUEST_ENABLEMENT_UPDATE_TOPIC, UIEvents.ALL_ELEMENT_ID );
		});
		eventBroker.send(Topics.NEW_SCENARIO, scenario);
	}
	
	/**
	 * Save scenario to a file. Shows a file save dialog if needed.
	 * @param filePath full path to the scenario file; if null, then a file save dialog is displayed
	 */
	public boolean saveScenario(Shell shell, String filePath) {
		final Scenario scenario = appData.getScenario();
		if (scenario == null) {
			System.err.println("AppState.saveScenario() : current scenario has not yet been set, so it will not be saved to a file");
			return false;
		}
		if (filePath == null) {
			filePath = DialogUtils.showSaveFileDialog(shell, null, scenario.getName(), p -> {}, ScenarioFileFormat.EXCEL.filePattern);
		}
		if (filePath == null) {
			System.out.println("AppState.saveScenario() : filePath is null, scenario has not been saved");
			return false;
		}
		
		// final String scenarioNameBeforeSave = scenario.getName();
		//appData.getScenario().setName(new File(filePath).getName());
		try {
			ScenarioSaver.save(scenario, filePath);
		} catch (ScenarioSavingException e) {
			//scenario.setName(scenarioNameBeforeSave);
			MessageBoxFactory.createMessageBox(new Shell(),
	                                      SWT.ICON_WARNING | SWT.OK | SWT.APPLICATION_MODAL,
	                                      AppData.messages.title_save_scenario,
	                                      String.format(AppData.messages.message_scenario_saving_error, e.getMessage()));
			return false;
		}

		recentlyOpenedFilesManager.saveString(filePath, RecentlyOpenedFilesManager.PathType.FILE);
		appData.setFilePath(filePath);
		saveIsDone();
		return true;
	}

	public boolean saveScenarioCopy(String filePath) {
		try {
			ScenarioSaver.save(appData.getScenario(), filePath);
		} catch (ScenarioSavingException e) {
			return false;
		}
		return true;
	}
	
}

