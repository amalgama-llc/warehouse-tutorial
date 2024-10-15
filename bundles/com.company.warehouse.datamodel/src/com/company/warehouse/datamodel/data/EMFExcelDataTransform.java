package com.company.warehouse.datamodel.data;

import com.amalgamasimulation.calendardatamodel.CalendardatamodelPackage;
import com.amalgamasimulation.ecoreutils.EcoreutilsPackage;
import com.amalgamasimulation.emf.excel.EMFExcelTransform;
import com.amalgamasimulation.randomdatamodel.RandomdatamodelPackage;
import com.amalgamasimulation.timeseriesdatamodel.TimeseriesdatamodelPackage;
import com.company.warehouse.datamodel.DatamodelPackage;
import com.company.warehouse.datamodel.Scenario;

public class EMFExcelDataTransform {
	
	
	private static EMFExcelTransform<Scenario> emfExcelTransform;
	
	private static void initialize() {
		 emfExcelTransform = new EMFExcelTransform<Scenario>().setRootClass(DatamodelPackage.eINSTANCE.getScenario())
					.addPackage(EcoreutilsPackage.eINSTANCE)
					.addPackage(CalendardatamodelPackage.eINSTANCE)
					.addPackage(RandomdatamodelPackage.eINSTANCE)
					.addPackage(TimeseriesdatamodelPackage.eINSTANCE);
					//.setCustomSheetName(DatamodelPackage.Literals.EQUIPMENT_UNAVAILABILITY_ASSIGNMENT, "UnavailabilityAssignment");
	}

	public static EMFExcelTransform<Scenario> getExcelTransform() {
		if(emfExcelTransform == null) {
			initialize();
		}
		return emfExcelTransform;
	}
}
