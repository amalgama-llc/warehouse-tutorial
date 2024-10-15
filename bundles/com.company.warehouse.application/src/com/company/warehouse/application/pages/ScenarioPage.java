package com.company.warehouse.application.pages;

import org.eclipse.emf.databinding.FeaturePath;

import com.company.warehouse.application.utils.Messages;
import com.company.warehouse.datamodel.DatamodelPackage;
import com.company.warehouse.datamodel.Scenario;
import com.amalgamasimulation.desktop.binding.UpdateValueStrategyFactory;
import com.amalgamasimulation.desktop.ui.editor.pages.AbstractPage;

public class ScenarioPage extends AbstractPage<Scenario, Scenario>{
	
	private Messages messages;
	
	public ScenarioPage(Messages messages) {
		super(null);
		this.messages = messages;
	}

	@Override
	public boolean isVisible(Object selectedObject) {
		return selectedObject instanceof Scenario;
	}
	
	@Override
	protected String getNameClassObject() {
		return messages.obj_SCENARIO;
	}
	
	@Override
	protected String getObjectDisplayName() {
		return observable.getValue().getName();
	}
	
	@Override
	protected FeaturePath[] getUpdateListeners() {
		return new FeaturePath [] {FeaturePath.fromList(DatamodelPackage.Literals.SCENARIO__NAME)};
	}

	@Override
	protected void createControlsInternal() {
		addStringSection(messages.obj_SCENARIO_col_NAME, DatamodelPackage.Literals.SCENARIO__NAME)
			.addTextbox(UpdateValueStrategyFactory.stringIsNotEmpty());
		addDateTimeSection(messages.obj_SCENARIO_col_BEGIN_DATE, DatamodelPackage.Literals.SCENARIO__BEGIN_DATE)
			.addTextbox(UpdateValueStrategyFactory.localDateTime())
			.addLocalDateTimeEditorButton()
			.setEnabled(true);
		addDateTimeSection(messages.obj_SCENARIO_col_END_DATE, DatamodelPackage.Literals.SCENARIO__END_DATE)
			.addTextbox(UpdateValueStrategyFactory.localDateTime())
			.addLocalDateTimeEditorButton()
			.setEnabled(true);		
	}
}

