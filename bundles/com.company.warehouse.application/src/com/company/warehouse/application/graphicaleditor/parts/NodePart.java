package com.company.warehouse.application.graphicaleditor.parts;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.amalgamasimulation.graphicaleditor.parts.AbstractNodePart;
import com.company.warehouse.application.utils.AppData;
import com.company.warehouse.datamodel.DatamodelPackage;
import com.company.warehouse.datamodel.Node;
import com.company.warehouse.datamodel.Scenario;

public class NodePart extends AbstractNodePart<Node, Scenario> {
	
	@Override
	public EStructuralFeature getNameFeature() {
		return DatamodelPackage.Literals.NODE__NAME;
	}

	@Override
	public EStructuralFeature getXStructuralFeature() {
			return DatamodelPackage.Literals.NODE__X;
	}

	@Override
	public EStructuralFeature getYStructuralFeature() {
			return DatamodelPackage.Literals.NODE__Y;
	}

	@Override
	public EStructuralFeature getZStructuralFeature() {
		return null;
	}

	@Override
	public EStructuralFeature getContainerStructuralFeature() {
		return DatamodelPackage.Literals.NODE__SCENARIO;
	}
	
	@Override
	public String getNewObjectName(EObject container, EObject eObject) {
		return AppData.messages.obj_NODE;
	}
	
}

