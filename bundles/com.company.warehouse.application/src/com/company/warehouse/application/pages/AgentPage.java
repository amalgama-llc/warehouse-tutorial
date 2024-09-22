package com.company.warehouse.application.pages;

import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.emf.databinding.EMFProperties;
import org.eclipse.emf.databinding.FeaturePath;

import com.amalgamasimulation.desktop.binding.UpdateValueStrategyFactory;
import com.amalgamasimulation.desktop.ui.editor.pages.AbstractPage;
import com.company.warehouse.application.utils.Messages;
import com.company.warehouse.datamodel.Agent;
import com.company.warehouse.datamodel.DatamodelPackage;
import com.company.warehouse.datamodel.Node;
import com.company.warehouse.datamodel.Scenario;

public class AgentPage extends AbstractPage<Agent, Scenario> {
	
	@SuppressWarnings("unchecked")
	private IObservableList<Node> nodeListObservable = EMFProperties.list(DatamodelPackage.Literals.SCENARIO__NODES).observeDetail(containerObservable);
	private Messages messages;
	
	public AgentPage(Messages messages) {
		super(Agent::getScenario);
		this.messages = messages;
	}
	
	@Override
	public boolean isVisible(Object selectedObject) {
		return selectedObject instanceof Agent;
	}
	
	@Override
	protected String getNameClassObject() {
		return messages.column_agent;
	}
	
	@Override
	protected String getObjectDisplayName() {
		return observable.getValue().getId() + " - " + observable.getValue().getName();
	}
	
	@Override
	protected final FeaturePath[] getUpdateListeners() {
		return new FeaturePath [] {
				FeaturePath.fromList(DatamodelPackage.Literals.AGENT__ID),
				FeaturePath.fromList(DatamodelPackage.Literals.AGENT__NAME)};
	}
	
	@Override
	protected void createControlsInternal() {
		addStringSection(messages.column_agent_id, DatamodelPackage.Literals.AGENT__ID)
			.addIdTextbox(containerObservable, DatamodelPackage.Literals.SCENARIO__AGENTS);
		addStringSection(messages.column_agent_name, DatamodelPackage.Literals.AGENT__NAME)
			.addTextbox(UpdateValueStrategyFactory.stringIsNotEmpty());
		addReferenceSection(messages.column_agent_base_position, DatamodelPackage.Literals.AGENT__BASE_POSITION)
			.addAutoCompleteTextbox(DatamodelPackage.Literals.NODE__ID, nodeListObservable)
			.addSelectionDialogButton(messages.object_for_select_dialog_base_position, nodeListObservable, tableView -> {
				tableView.addColumn(messages.obj_NODE_col_ID, 150, node -> node.getId());
				tableView.addColumn(messages.obj_NODE_col_NAME, 150, node -> node.getName());
			})
			.addFilteredSelectionButton(obj -> obj instanceof Node);
		addNumericSection(messages.column_agent_velocity, DatamodelPackage.Literals.AGENT__VELOCITY)
			.addTextbox(UpdateValueStrategyFactory.doublePositiveWithZero());
		addBooleanSection(messages.column_agent_included, DatamodelPackage.Literals.AGENT__INCLUDED)
			.addCheckbox();
		
	}
}

