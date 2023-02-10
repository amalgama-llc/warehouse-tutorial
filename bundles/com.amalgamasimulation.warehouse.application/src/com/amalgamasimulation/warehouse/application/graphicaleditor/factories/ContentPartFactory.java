package com.amalgamasimulation.warehouse.application.graphicaleditor.factories;

import org.eclipse.gef.mvc.fx.parts.IContentPart;

import com.amalgamasimulation.warehouse.application.graphicaleditor.parts.ArcPart;
import com.amalgamasimulation.warehouse.application.graphicaleditor.parts.NodePart;
import com.amalgamasimulation.warehouse.application.graphicaleditor.parts.ScenarioPart;
import com.amalgamasimulation.warehouse.datamodel.Arc;
import com.amalgamasimulation.warehouse.datamodel.Scenario;

import javafx.scene.Node;

public class ContentPartFactory extends com.amalgamasimulation.graphicaleditor.factories.ContentPartFactory{
	
	@Override
	protected Class<? extends IContentPart<? extends Node>> getPartClass(Object content) {
		if (content instanceof Scenario) {
			return ScenarioPart.class;
		} else if (content instanceof com.amalgamasimulation.warehouse.datamodel.Node) {
			return NodePart.class;
		} else if (content instanceof Arc) {
			return ArcPart.class;
		}
		return null;
	}
}

