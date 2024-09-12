package com.company.warehouse.application.parts.simulation;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ToolBar;

import com.company.warehouse.application.animation.ArcShape;
import com.company.warehouse.application.animation.ForkliftShape;
import com.company.warehouse.application.animation.AgentShape;
import com.company.warehouse.application.animation.NodeShape;
import com.amalgamasimulation.desktop.ui.editor.utils.IconsMapping;
import com.amalgamasimulation.desktop.ui.editor.utils.Topics;
import com.company.warehouse.simulation.Model;
import com.amalgamasimulation.desktop.ui.views.ToolBarComposite;
import com.amalgamasimulation.desktop.utils.MessageManager;
import com.amalgamasimulation.desktop.utils.ToolbarUtils;
import com.amalgamasimulation.platform.animation.swt.SWT2DSimulationView;
import com.amalgamasimulation.platform.animation.swt.SWT2DSimulationViewImpl;
import com.amalgamasimulation.viewupdater.service.IViewUpdaterService;


public class SimulationPart {

	@Inject
	private MessageManager messageManager;
	
	@Inject
	private IViewUpdaterService viewUpdaterService;
	
	private SWT2DSimulationView animationView;
	private Model model;
	
	@Inject
	private IconsMapping iconsMapping;
	
	@PostConstruct
	public void postConstruct(Composite parent) {
		new ToolBarComposite(parent, this::createToolBar, this::createContent);
	}
	
	private void createContent(Composite parent) {
		animationView = new SWT2DSimulationViewImpl(parent);
		messageManager.subscribe(Topics.SHOW_MODEL, this::onShowModel, true);
		viewUpdaterService.getDefaultUpdater().addView(animationView);
	}
	private ToolBar createToolBar(Composite parent) {
 		ToolBar toolBar = new ToolBar(parent, SWT.HORIZONTAL);
		ToolbarUtils.addCommandItem(toolBar, iconsMapping.CENTERING, "Center", () -> animationView.adjustWindow()).setText("Center");
 		return toolBar;
	}
	
	private void onShowModel(Model model) {
		this.model = model;
		animationView.removeAllShapes();
		if(model != null) {
			model.getNodes().stream().forEach(n -> animationView.addShape(new NodeShape(n)));
			model.getArcs().forEach(r -> animationView.addShape(new ArcShape(r)));		
	        model.getForklifts().forEach(f -> animationView.addShape(new ForkliftShape(f)));
		}
		animationView.adjustWindow();
	}
}

