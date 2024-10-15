package com.company.warehouse.application.pages;

import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.emf.databinding.EMFProperties;

import com.company.warehouse.application.utils.Messages;
import com.company.warehouse.datamodel.Arc;
import com.company.warehouse.datamodel.DatamodelPackage;
import com.company.warehouse.datamodel.Point;
import com.company.warehouse.datamodel.Scenario;
import com.amalgamasimulation.desktop.binding.UpdateValueStrategyFactory;
import com.amalgamasimulation.desktop.properties.sections.TableSection;
import com.amalgamasimulation.desktop.ui.views.TableView;
import com.amalgamasimulation.desktop.ui.editor.pages.AbstractPage;


public class PointPage extends AbstractPage<Arc, Scenario>{

	@SuppressWarnings("unchecked")
	private IObservableList<Point> observableBendPoints = EMFProperties.list(DatamodelPackage.Literals.ARC__POINTS).observeDetail(observable);
	
		private Messages messages;
	
	public PointPage(Messages messages) {
		super(Arc::getScenario);
		this.messages = messages;
	}

	@Override
	public boolean isVisible(Object selectedObject) {
		return selectedObject instanceof Arc;
	}
	
	@Override
	protected String getNameClassObject() {
		return messages.obj_POINT;
	}
	
	@Override
	protected String getObjectDisplayName() {
		return messages.obj_POINT;
	}
	
	
	@Override
	protected void createControlsInternal() {
		TableSection<Arc, Point> tableSection = addTableSection(observableBendPoints, DatamodelPackage.Literals.POINT__X, DatamodelPackage.Literals.POINT__Y);
		TableView<Point> tableView = tableSection.getTableView();
		
		tableView.addColumn(messages.obj_POINT_col_X, 100, p -> p.getX())
			.setTextEditingSupport(DatamodelPackage.Literals.POINT__X, UpdateValueStrategyFactory.doubleAny());
		tableView.addColumn(messages.obj_POINT_col_Y, 100, p -> p.getY())
			.setTextEditingSupport(DatamodelPackage.Literals.POINT__Y, UpdateValueStrategyFactory.doubleAny());
	}
}

