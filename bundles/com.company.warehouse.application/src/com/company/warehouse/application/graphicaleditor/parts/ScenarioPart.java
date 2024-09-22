package com.company.warehouse.application.graphicaleditor.parts;

import java.util.Comparator;

import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.emf.databinding.EMFProperties;
import com.amalgamasimulation.graphicaleditor.parts.AbstractContainerPart;
import com.company.warehouse.datamodel.Arc;
import com.company.warehouse.datamodel.DatamodelPackage;
import com.company.warehouse.datamodel.Scenario;

public class ScenarioPart extends AbstractContainerPart<Scenario> {

	@Override
	protected Comparator<Object> getChildsContentComparator() {
		return (o1, o2) -> {
			int firstObjectPriority = o1 instanceof Arc ? 0 : 1;
			int secondObjectPriority = o2 instanceof Arc ? 0 : 1;
			return Integer.compare(firstObjectPriority, secondObjectPriority);
		};
	}

	@SuppressWarnings("unchecked")
	@Override
	protected IObservableList<Object> getChildContentsObservable(Scenario container) {
		return EMFProperties.multiList(	DatamodelPackage.Literals.SCENARIO__ARCS,
										DatamodelPackage.Literals.SCENARIO__NODES).observeDetail(containerObservable);
	}

}

