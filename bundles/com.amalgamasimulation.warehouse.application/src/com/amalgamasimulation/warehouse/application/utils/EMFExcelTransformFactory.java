package com.amalgamasimulation.warehouse.application.utils;

import com.amalgamasimulation.emf.excel.ColumnHeaderProvider;
import com.amalgamasimulation.emf.excel.EMFExcelTransform;
import com.amalgamasimulation.warehouse.application.utils.validation.ObjectType;
import com.amalgamasimulation.warehouse.common.localization.Messages;
import com.amalgamasimulation.warehouse.common.states.AppData;
import com.amalgamasimulation.warehouse.datamodel.DatamodelPackage;
import com.amalgamasimulation.warehouse.datamodel.Scenario;
import static com.amalgamasimulation.warehouse.datamodel.DatamodelPackage.eINSTANCE;
import com.amalgamasimulation.ecoreutils.EcoreutilsPackage;
import com.amalgamasimulation.calendardatamodel.CalendardatamodelPackage;
import com.amalgamasimulation.randomdatamodel.RandomdatamodelPackage;
import com.amalgamasimulation.timeseriesdatamodel.TimeseriesdatamodelPackage;

public class EMFExcelTransformFactory {
	
	private EMFExcelTransformFactory() {}

	public static EMFExcelTransform<com.amalgamasimulation.warehouse.datamodel.Scenario> createExcelTransform() {
		EMFExcelTransform<Scenario> emfExcelTransform = new EMFExcelTransform<Scenario>().setRootClass(DatamodelPackage.eINSTANCE.getScenario())
				.addPackage(EcoreutilsPackage.eINSTANCE)
				.addPackage(CalendardatamodelPackage.eINSTANCE)
				.addPackage(RandomdatamodelPackage.eINSTANCE)
				.addPackage(TimeseriesdatamodelPackage.eINSTANCE)
				;
		emfExcelTransform.setColumnHeaderProvider(EMFExcelTransformFactory.createColumnHeaderProvider(AppData.messages));

		return emfExcelTransform;
	}

	private static ColumnHeaderProvider createColumnHeaderProvider(Messages messages) {
		var columnHeaderProvider = new ColumnHeaderProvider();
		for (ObjectType objectType : ObjectType.values()) {
			objectType.getFeatures().forEach(f -> columnHeaderProvider.addMapping(objectType.getEClass(), f, objectType.getColumnNameExcel(f)));
		}
		columnHeaderProvider
				.addMapping(eINSTANCE.getNode(), null, messages.obj_NODE_col_ID_excel)
				.addMapping(eINSTANCE.getArc(), null, messages.obj_ARC_col_ID_excel)
				;
		
		return columnHeaderProvider;
	}

}

