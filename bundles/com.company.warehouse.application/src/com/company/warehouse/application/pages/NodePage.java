package com.company.warehouse.application.pages;

import org.eclipse.emf.databinding.FeaturePath;

import com.company.warehouse.application.utils.Messages;
import com.company.warehouse.datamodel.DatamodelPackage;
import com.company.warehouse.datamodel.Node;
import com.company.warehouse.datamodel.Scenario;
import com.amalgamasimulation.desktop.binding.UpdateValueStrategyFactory;
import com.amalgamasimulation.desktop.ui.editor.pages.AbstractPage;


public class NodePage extends AbstractPage<Node, Scenario> {

	private Messages messages;
	
	public NodePage(Messages messages) {
		super(Node::getScenario);
		this.messages = messages;
	}

	@Override
	public boolean isVisible(Object selectedObject) {
		return selectedObject instanceof Node;
	}
	
	@Override
	protected String getNameClassObject() {
		return messages.obj_NODE;
	}
	
	@Override
	protected String getObjectDisplayName() {
		return observable.getValue().getId() + " - " + observable.getValue().getName();
	}
	
	@Override
	protected final FeaturePath[] getUpdateListeners() {
		return new FeaturePath [] {
				FeaturePath.fromList(DatamodelPackage.Literals.NODE__ID),
				FeaturePath.fromList(DatamodelPackage.Literals.NODE__NAME)};
	}
	
	@Override
	protected void createControlsInternal() {
		addStringSection(messages.obj_NODE_col_ID, DatamodelPackage.Literals.NODE__ID)
			.addIdTextbox(containerObservable, DatamodelPackage.Literals.SCENARIO__NODES);
		addStringSection(messages.obj_NODE_col_NAME, DatamodelPackage.Literals.NODE__NAME)
			.addTextbox(UpdateValueStrategyFactory.stringIsNotEmpty());
			addNumericSection(messages.obj_NODE_col_X, DatamodelPackage.Literals.NODE__X)
				.addTextbox(UpdateValueStrategyFactory.doubleAny());
			addNumericSection(messages.obj_NODE_col_Y, DatamodelPackage.Literals.NODE__Y)
				.addTextbox(UpdateValueStrategyFactory.doubleAny());

	}
}

