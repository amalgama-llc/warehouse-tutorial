package com.company.warehouse.application.graphicaleditor.handlers;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import com.amalgamasimulation.emf.commands.AddCommand;
import com.amalgamasimulation.graphicaleditor.factories.ContentPartFactory;
import com.amalgamasimulation.graphicaleditor.handlers.CreateArcClickHandler;
import com.company.warehouse.application.utils.AppData;
import com.amalgamasimulation.desktop.ui.editor.commands.UniqNamesManager;
import com.company.warehouse.datamodel.DatamodelPackage;

public class CreateArcHandler extends CreateArcClickHandler {
	
	@Override
	public EObject executeCreateObject(ContentPartFactory contentPartFactory, EClass arcClass, EObject container,
			EObject sourceNode, EObject destNode) {
		AddCommand<? extends EObject> command = createArcCommand(contentPartFactory, arcClass, container, sourceNode, destNode);
		command.setActionBefore(() -> {
				String id = UniqNamesManager.getInstance().generateUniqueId(container, command.getObject(), AppData.messages.obj_ARC);
				command.getObject().eSet(DatamodelPackage.Literals.ARC__ID, id);
				command.getObject().eSet(DatamodelPackage.Literals.ARC__NAME, id);
		});
		command.executeInStack();
		return command.getObject();
	}
	
}

