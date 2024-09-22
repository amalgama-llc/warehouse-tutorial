package com.company.warehouse.testsapplication;

import java.awt.Color;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.ToolBar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.wiring.BundleWiring;

import com.amalgamasimulation.desktop.ui.dialogs.ProgressDialog;
import com.amalgamasimulation.desktop.ui.dialogs.ProgressDialog.Style;
import com.amalgamasimulation.desktop.ui.views.ToolBarComposite;
import com.amalgamasimulation.desktop.ui.views.TreeView;
import com.amalgamasimulation.desktop.utils.MenuUtils;
import com.amalgamasimulation.desktop.utils.MessageManager;
import com.amalgamasimulation.desktop.utils.ToolbarUtils;
import com.amalgamasimulation.engine.service.IEngineService;
import com.amalgamasimulation.viewupdater.service.IViewUpdaterService;
import com.amalgamasimulation.desktop.ui.editor.utils.IconsMapping;
import com.amalgamasimulation.desktop.ui.editor.utils.Topics;
import com.company.warehouse.tests.utils.AbstractVisualizedTest;

public class TreePart {
	
	@Inject
	private MessageManager messageManager;
	
	@Inject
	private IEngineService engineService;
	
	@Inject
	private IViewUpdaterService updaterService;
	
	@Inject
	private IconsMapping iconsMapping;
	
	private List<TreeElement> groupElements = initializeTests();
	private TreeView<TreeElement> treeView;
	
	@PostConstruct
	protected void createComposite(Composite parent) {
		new ToolBarComposite(parent, this::createToolBar, this::createTreeView);
	}
	
	private void createToolBar(Composite parent) {
		ToolBar toolBar = new ToolBar(parent, SWT.HORIZONTAL);
		ToolbarUtils.addCommandItem(toolBar, iconsMapping.EXPAND_TREE, "Expand all", () -> treeView.expandAll());
		ToolbarUtils.addCommandItem(toolBar, iconsMapping.COLLAPSE_TREE, "Collapse all", () -> treeView.collapseAll());
		ToolbarUtils.addSeparator(toolBar);
		ToolbarUtils.addCommandItem(toolBar, iconsMapping.CHECK, "Run all tests", () -> runAllTests());
		ToolbarUtils.addSeparator(toolBar);
	}
	
	protected void createTreeView(Composite parent) {
		treeView = new TreeView<>(parent, groupElement -> groupElement.getChildElements(), groupElements);
		treeView.addColumn("Name", 450, e -> e.getName());
		treeView.addColumn("Status", 100,  e -> e.getRunResult())
			.setSimpleBackground(e -> 	e.getRunResult() == RunResult.NONE ? Color.white 
										: e.getRunResult() == RunResult.PASSED ? Color.green 
										: Color.red);
		treeView.addDoubleClickHandler((treeElement, e) -> {
			if (treeElement instanceof SingleTestTreeElement) {
				SingleTestTreeElement test = (SingleTestTreeElement)treeElement;
				runWithAnimation(test);
			}
		});
		treeView.addContextMenuHandler((Menu menu, TreeElement treeElement, int columnIndex) -> {
			if (treeElement instanceof SingleTestTreeElement) {
				SingleTestTreeElement test = (SingleTestTreeElement)treeElement;
				MenuUtils.addCommandMenuItem(menu, iconsMapping.CHECK, "Run this test", () -> ProgressDialog.execute("Running '" + test.getName() + "'", () -> runTest(test)));
				MenuUtils.addCommandMenuItem(menu, iconsMapping.CHECK, "Run with animation", () -> runWithAnimation(test));
			} else if (treeElement instanceof TestsGroupTreeElement){
				TestsGroupTreeElement test = (TestsGroupTreeElement)treeElement;
				MenuUtils.addCommandMenuItem(menu, iconsMapping.CHECK, "Run tests in group", () -> runAllTests(test));
			}
			MenuUtils.addCommandMenuItem(menu, iconsMapping.CHECK, "Run all tests", () -> runAllTests());
		});
		treeView.setData(groupElements);
		updaterService.getDefaultUpdater().addView(treeView);
	}
	
	private List<TreeElement> initializeTests() {
		Bundle bundle = FrameworkUtil.getBundle(AbstractVisualizedTest.class);
		BundleWiring bw = bundle.adapt(BundleWiring.class);
		var x = bw.listResources("/", "*.java", BundleWiring.FINDENTRIES_RECURSE + BundleWiring.LISTRESOURCES_LOCAL);
		List<Class<?>> classes = new ArrayList<>();
		try {
			for(String className : x) {
				className = className.replace("src/", "").replace(".java", "").replace("/", ".");
				classes.add( bw.getClassLoader().loadClass(className));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		List<TreeElement> treeElements = new ArrayList<>();
		classes.stream().filter(clazz -> AbstractVisualizedTest.class.isAssignableFrom(clazz)).forEach(clazz -> {
			DisplayName classNameAnnotation = (DisplayName)clazz.getAnnotation(DisplayName.class);
			TestsGroupTreeElement testsGroupTreeElement = new TestsGroupTreeElement(classNameAnnotation == null ? clazz.getSimpleName() : classNameAnnotation.value());
			
			List<Method> beforeEachMethods = new ArrayList<Method>();
			Arrays.asList(clazz.getDeclaredMethods()).forEach(method -> {
				if(method.getAnnotation(BeforeEach.class) != null) {
					beforeEachMethods.add(method);
				}
			});
			
			Arrays.asList(clazz.getDeclaredMethods()).forEach(method -> {
				if(		method.getAnnotation(Test.class) != null
						&& method.getAnnotation(Disabled.class) == null) {
					try {
						DisplayName nameAnnotation = method.getAnnotation(DisplayName.class);
						String name = nameAnnotation == null ? method.getName() : nameAnnotation.value();
						testsGroupTreeElement.getChildElements().add(new SingleTestTreeElement(name, () -> {
							try {
								return clazz.getDeclaredConstructors()[0].newInstance();
							} catch (Exception e) {
								e.printStackTrace();
								return null;
							}
						}, method, beforeEachMethods));
					} catch(Exception e) {
						e.printStackTrace();						
					}
				}
			});
			
			if (!testsGroupTreeElement.getChildElements().isEmpty()) {
				treeElements.add(testsGroupTreeElement);
			}
		});
		treeElements.forEach(e -> e.getChildElements().sort(Comparator.comparing(TreeElement::getName)));
		return treeElements.stream().sorted(Comparator.comparing(TreeElement::getName)).collect(Collectors.toList());
	}
	
	private void runAllTests() {
		runAllTests(null);
	}
	
	private void runAllTests(TestsGroupTreeElement element) {
		treeView.updateView();
		List<SingleTestTreeElement> testsToRun = groupElements.stream().filter(e -> element == null || element == e).flatMap(groupElement -> groupElement.getChildElements().stream()).map(te -> (SingleTestTreeElement)te).collect(Collectors.toList());
		if (!testsToRun.isEmpty()) {
			ProgressDialog.execute("Running...", "Cancel", Style.DEFINITE, () -> {
				testsToRun.forEach(test -> {
					test.setRunResult(RunResult.NONE);
				});
				for (int i = 0; i < testsToRun.size(); i++) {
					if (ProgressDialog.cancelPressed()) {
						break;
					}
					SingleTestTreeElement test = testsToRun.get(i);
					ProgressDialog.setMessage("Running " + (i + 1) + " of " + testsToRun.size() + " (Failed: " + testsToRun.stream().filter(t -> t.getRunResult() == RunResult.FAILED || t.getRunResult() == RunResult.ERROR).count() + ")");
					ProgressDialog.setProgress((double)i / (double)testsToRun.size());
					test.runTest();
				}
			}, () -> {});
			treeView.updateView();
		}
	}
	
	private void runTest(SingleTestTreeElement test) {
		engineService.getEngine().reset();
		messageManager.send(Topics.SHOW_MODEL, null);
		test.runTest();
		treeView.updateView();
	}
	
	private void runWithAnimation(SingleTestTreeElement test) {
		engineService.getEngine().reset();
		test.runWithAnimation(engineService.getEngine());
		messageManager.send(Topics.SHOW_MODEL, test.getModel());
		engineService.getEngine().setTimeScale(1.0);
		engineService.getEngine().run();
		treeView.updateView();
	}
}

