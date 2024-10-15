package com.company.warehouse.tests.utils;

import static org.junit.jupiter.api.Assertions.fail;
import java.io.File;
import java.nio.file.Paths;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import com.amalgamasimulation.emf.excel.EMFExcelLoader;
import com.amalgamasimulation.engine.Engine;
import com.company.warehouse.datamodel.data.EMFExcelDataTransform;
import com.company.warehouse.datamodel.Scenario;
import com.company.warehouse.simulation.Model;

/**
 * This is the base class for all tests that can be visualized with the test
 * application
 *
 */
public abstract class AbstractVisualizedTest {
	
	protected AbstractVisualizedTest() {
 		initTestsFolder();
 	}

	private static String testsFolder;
	
 	// initialize testsFolder once
 	private static void initTestsFolder() {
 		if (testsFolder == null) {
 			Bundle bundle = FrameworkUtil.getBundle(AbstractVisualizedTest.class);
 			String basePath;
 			if (bundle == null) {
 				// 'Run as JUnit' from Eclipse. Test project's folder is the current folder.
 				basePath = ".";
 			} else {
 				String bundleLocation = bundle.getLocation();
 				// NOTE: include these 4 bundles into your GUI test app's dependencies list: 
 				// org.eclipse.equinox.p2.core, org.eclipse.equinox.p2.engine, org.eclipse.equinox.p2.metadata, org.eclipse.equinox.p2.operations
 				if (bundleLocation.startsWith("initial@")) {
 					// Running tests from console. Test project's folder is the current folder.
 					basePath = ".";
 				} else {
 					// Running tests from Eclipse using a UI application. Current folder is the Eclipse IDE's folder,
 					// so we need to extract the bundle's path.
 					basePath = bundleLocation.replace("reference:file:/", "");
 				}
 			}
 			testsFolder = Paths.get(basePath, "scenarios").toFile().getAbsolutePath();
 		}
 	}	
	
	private Model model;
	private Engine engine = new Engine();
	private boolean isEngineExternal = false;
	
	public void setExternalEngine(Engine externalEngine) {
		this.engine = externalEngine;
		this.isEngineExternal = true;
	}
	
	public void setModel(Model model) {
		this.model = model;
	}
	
	public void runEngine() {
		if(!isEngineExternal) {
			engine.scheduleStop(Double.POSITIVE_INFINITY, "Stop at infinite time");
			engine.setFastMode(true);
			engine.run(true);
		}
	}
	
	public void loadScenario(String relativePathToScenarioFile) {
		String filePath = testsFolder + File.separatorChar + relativePathToScenarioFile;
		EMFExcelLoader<Scenario> loader = EMFExcelDataTransform.getExcelTransform().createLoader(filePath);
		if (!loader.load()) {
			fail("Unable to load scenario file: " + relativePathToScenarioFile);
		}
		this.model = new Model(engine, loader.getRootObject());
	}
	
	public Model getModel() {
		return model;
	}
	
	public Engine getEngine() {
		return engine;
	}


 	
}

