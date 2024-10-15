package com.company.warehouse.testsapplication;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import org.opentest4j.AssertionFailedError;

import com.amalgamasimulation.engine.Engine;
import com.amalgamasimulation.engine.Model;
import com.company.warehouse.tests.utils.AbstractVisualizedTest;

public class SingleTestTreeElement extends TreeElement{
	
	private Supplier<Object> instanceSupplier;
	private Method testMethod;
	private List<Method> initializationMethods;
	private RunResult runResult = RunResult.NONE;
	private Model model;
	
	public SingleTestTreeElement(	String name,
									Supplier<Object> instanceSupplier,
									Method testMethod,
									List<Method> initializationMethods) {
		super(name);
		this.instanceSupplier = instanceSupplier;
		this.testMethod = testMethod;
		this.initializationMethods = initializationMethods;
	}
	
	@Override
	public List<TreeElement> getChildElements() {
		return Collections.emptyList();
	}
	
	public RunResult getRunResult() {
		return runResult;
	}
	
	public void setRunResult(RunResult runResult) {
		this.runResult = runResult;
	}
	
	public Model getModel() {
		return model;
	}
	public void runTest() {
		try {
			AbstractVisualizedTest visualizedTest = (AbstractVisualizedTest) instanceSupplier.get();
			for (Method initializationMethod : initializationMethods) {
				initializationMethod.invoke(visualizedTest);
			}
			testMethod.invoke(visualizedTest);
			model = visualizedTest.getModel();
			runResult = RunResult.PASSED;
		} catch (Exception e) {
			if (e.getCause() instanceof AssertionFailedError) {
				runResult = RunResult.FAILED;
			} else {
				runResult = RunResult.ERROR;
			}
		}
	}
	
	public void runWithAnimation(Engine engine) {
		AbstractVisualizedTest animatedTest = null;
		try {
			animatedTest = (AbstractVisualizedTest) instanceSupplier.get();
			animatedTest.setExternalEngine(engine);
			testMethod.invoke(animatedTest);
		} catch (Exception e) {
			// Deliberately do nothing
		} finally {
			model = animatedTest.getModel();
		}
	}
}

