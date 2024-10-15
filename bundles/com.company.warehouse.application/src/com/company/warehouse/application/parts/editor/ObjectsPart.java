package com.company.warehouse.application.parts.editor;

import java.util.ArrayList;
import java.util.List;
import jakarta.inject.Inject;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;

import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.e4.core.services.nls.Translation;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.databinding.EMFProperties;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;

import org.eclipse.emf.common.command.Command;
import com.amalgamasimulation.emf.commands.RemoveCommand;
import com.company.warehouse.datamodel.data.EMFExcelDataTransform;
import com.company.warehouse.application.utils.TreeElementType;
import com.company.warehouse.application.utils.AppData;
import com.company.warehouse.application.utils.Messages;
import com.amalgamasimulation.desktop.ui.editor.parts.AbstractObjectsPart;
import com.amalgamasimulation.desktop.ui.editor.parts.ObjectsPage;
import com.amalgamasimulation.desktop.ui.editor.utils.Topics;
import com.amalgamasimulation.desktop.ui.views.TableView;
import com.amalgamasimulation.emf.commands.CommandsManager;
import com.amalgamasimulation.utils.format.Formats;
import com.company.warehouse.datamodel.Agent;
import com.company.warehouse.datamodel.DatamodelFactory;
import com.company.warehouse.datamodel.Arc;
import com.company.warehouse.datamodel.DatamodelPackage;
import com.company.warehouse.datamodel.Node;
import com.company.warehouse.datamodel.Scenario;
import com.amalgamasimulation.desktop.binding.UpdateValueStrategyFactory;

public class ObjectsPart extends AbstractObjectsPart{
	@Inject
	private AppData appData;
	
	@Inject
	@Translation
	private Messages messages;
	
	@SuppressWarnings("all")
	protected IObservableValue<Scenario> scenarioObservable = new WritableValue<>();
	@SuppressWarnings("all")
	private IObservableList<Node> nodeListObservable = EMFProperties.list(DatamodelPackage.Literals.SCENARIO__NODES).observeDetail(scenarioObservable);
	@SuppressWarnings("all")
	private IObservableList<Arc> arcListObservable = EMFProperties.list(DatamodelPackage.Literals.SCENARIO__ARCS).observeDetail(scenarioObservable);
	@SuppressWarnings("all")
	private IObservableList<Agent> agentListObservable = EMFProperties.list(DatamodelPackage.Literals.SCENARIO__AGENTS).observeDetail(scenarioObservable);
	
		@Override
	protected List<ObjectsPage<? extends EObject>> getObjectsPages() {
		eventBroker.subscribe(Topics.NEW_SCENARIO, event -> scenarioObservable.setValue(appData.getScenario()));		
		List<ObjectsPage<? extends EObject>> pages = new ArrayList<ObjectsPage<? extends EObject>>();
		pages.add(createNodePage());
		pages.add(createArcPage());
		pages.add(createAgentPage());
		return pages;
	}
	
	private ObjectsPage<Node> createNodePage() {
		return new ObjectsPage<Node>(nodeListObservable, TreeElementType.NODE)
				.setTableRefreshBinding(
			DatamodelPackage.Literals.NODE__X,
					DatamodelPackage.Literals.NODE__ID,
					DatamodelPackage.Literals.NODE__NAME,
			DatamodelPackage.Literals.NODE__Y
					)
				.setAfterCreateTableElementAction(tableView -> {
					tableView.addColumn(messages.obj_NODE_col_ID, 120, Node::getId)
						.setIdTextEditingSupport(nodeListObservable, DatamodelPackage.Literals.NODE__ID);
					tableView.addColumn(messages.obj_NODE_col_NAME, 100, p -> p == null ? "" : p.getName())
						.setTextEditingSupport(DatamodelPackage.Literals.NODE__NAME, UpdateValueStrategyFactory.stringIsNotEmpty());
					tableView.addColumn(messages.obj_NODE_col_X, 100, Node::getX).setTextEditingSupport(
							DatamodelPackage.Literals.NODE__X,
							UpdateValueStrategyFactory.doubleAny());
					tableView.addColumn(messages.obj_NODE_col_Y, 100, Node::getY).setTextEditingSupport(
							DatamodelPackage.Literals.NODE__Y,
							UpdateValueStrategyFactory.doubleAny());
						tableView.getEditingInExcelManager().setDataForEditing(DatamodelPackage.Literals.NODE,
								() -> scenarioObservable.getValue(), EMFExcelDataTransform.getExcelTransform(), () -> commandFactory.getCrossReferenceAdapter());
						tableView.getEditingInExcelManager().setAdditionalActionBeforeRemove(objects -> {
							Set<Arc> removeArcs = new HashSet<>();
							Map<Node, Set<Arc>> nodeByArcs = new HashMap<>();
							List<Command> commands = new ArrayList<>();
							
							arcListObservable.forEach(arc -> {
								if(arc.getSource() != null) {
									nodeByArcs.computeIfAbsent(arc.getSource(), v -> new HashSet<>()).add(arc);
								}
								if(arc.getDest() != null) {
									nodeByArcs.computeIfAbsent(arc.getDest(), v -> new HashSet<>()).add(arc);
								}
							});
							
							objects.forEach(node -> removeArcs.addAll(nodeByArcs.get(node)));
							if(!removeArcs.isEmpty()) {
								commands.add(new RemoveCommand<Arc>(new ArrayList<>(removeArcs), commandFactory.getCrossReferenceAdapter()));
							}
							return commands;
						});
				});
	}
	
	private ObjectsPage<Arc> createArcPage() {
		return new ObjectsPage<Arc>(arcListObservable, TreeElementType.ARC)
				.setTableRefreshBinding(DatamodelPackage.Literals.ARC__SOURCE, DatamodelPackage.Literals.ARC__ID,DatamodelPackage.Literals.ARC__NAME,
					DatamodelPackage.Literals.ARC__DEST)
				.setAfterCreateTableElementAction(tableView -> {
					tableView.addColumn(messages.obj_ARC_col_ID, 100, Arc::getId)
						.setIdTextEditingSupport(arcListObservable, DatamodelPackage.Literals.ARC__ID);
					tableView.addColumn(messages.obj_ARC_col_NAME, 100, p -> p == null ? "" : p.getName())
						.setTextEditingSupport(DatamodelPackage.Literals.ARC__NAME, UpdateValueStrategyFactory.stringIsNotEmpty());
					tableView.addColumn(messages.obj_ARC_col_SOURCE, 100, Arc::getSource, p -> p == null ? "" : p.getId())
							.setAutoCompleteComboEditingSupport(nodeListObservable,
									DatamodelPackage.Literals.ARC__SOURCE, DatamodelPackage.Literals.NODE__ID);
					tableView.addColumn(messages.obj_ARC_col_DEST, 100, Arc::getDest, p -> p == null ? "" : p.getId())
							.setAutoCompleteComboEditingSupport(nodeListObservable,
									DatamodelPackage.Literals.ARC__DEST, DatamodelPackage.Literals.NODE__ID);
					tableView.getEditingInExcelManager().setDataForEditing(DatamodelPackage.Literals.ARC,
							() -> scenarioObservable.getValue(), EMFExcelDataTransform.getExcelTransform(), () -> commandFactory.getCrossReferenceAdapter());
				});
	}
	
	private ObjectsPage<Agent> createAgentPage() {
		return new ObjectsPage<Agent>(agentListObservable, TreeElementType.AGENT, 
				() -> commandFactory.create(appData.getScenario(), messages.column_agent, () -> {
					Agent agent = DatamodelFactory.eINSTANCE.createAgent();
					if(!scenarioObservable.getValue().getNodes().isEmpty()) {
						agent.setBasePosition(scenarioObservable.getValue().getNodes().get(0));
					}
					return agent;
				}, true).getObject(),
				elem -> commandFactory.remove(elem, true),
				elem -> commandFactory.copy(elem, -1, true, true).getObject()) {
			@Override
			protected void updateChecked(TableView<Agent> tableView) {
				tableView.checkItems(agentListObservable.stream().filter(agent -> agent.isIncluded()).toList());
			}
		}
				.setCheckbox(true)
				.setTableRefreshBinding(
						DatamodelPackage.Literals.AGENT__ID, 
						DatamodelPackage.Literals.AGENT__NAME,
						DatamodelPackage.Literals.AGENT__BASE_POSITION,
						DatamodelPackage.Literals.AGENT__INCLUDED,
						DatamodelPackage.Literals.AGENT__VELOCITY)
				.setAfterCreateTableElementAction(tableView -> {
					tableView.addColumn(messages.column_agent_id, 100, Agent::getId)
						.setIdTextEditingSupport(agentListObservable, DatamodelPackage.Literals.AGENT__ID);
					tableView.addColumn(messages.column_agent_name, 100, p -> p == null ? "" : p.getName())
						.setTextEditingSupport(DatamodelPackage.Literals.AGENT__NAME, UpdateValueStrategyFactory.stringIsNotEmpty());
					tableView.addColumn(messages.column_agent_base_position, 100, Agent::getBasePosition, p -> p == null ? "" : p.getId())
							.setAutoCompleteComboEditingSupport(nodeListObservable,
									DatamodelPackage.Literals.AGENT__BASE_POSITION, DatamodelPackage.Literals.NODE__ID);		
					tableView.addColumn(messages.column_agent_velocity, 100, Agent::getVelocity)
						.setLabelExtractor(value -> Formats.getDefaultFormats().twoDecimals(value))
						.setTextEditingSupport(DatamodelPackage.Literals.AGENT__VELOCITY,UpdateValueStrategyFactory.doubleAny());
					
					tableView.addCheckedItemsChangedHandler(list -> {
						List<Agent> equipments = new ArrayList<>(scenarioObservable.getValue().getAgents());
						equipments.removeAll(list);
						
						CompoundCommand com = new CompoundCommand();
						equipments.forEach(ew -> com.append(SetCommand.create(CommandsManager.getEditingDomain(), ew, DatamodelPackage.Literals.AGENT__INCLUDED, false)));
						list.forEach(ew -> com.append(SetCommand.create(CommandsManager.getEditingDomain(), ew, DatamodelPackage.Literals.AGENT__INCLUDED, true)));
						
						if(!com.isEmpty()) {
							CommandsManager.getEditingDomain().getCommandStack().execute(com);
						}
					});
					tableView.getEditingInExcelManager().setDataForEditing(DatamodelPackage.Literals.AGENT,
							() -> scenarioObservable.getValue(), EMFExcelDataTransform.getExcelTransform(), () -> commandFactory.getCrossReferenceAdapter());
					
				});
	}
}

