package com.company.warehouse.application.parts.simulation;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;

import org.eclipse.e4.core.services.nls.Translation;
import org.eclipse.swt.widgets.Composite;

import com.company.warehouse.application.utils.AppData;
import com.amalgamasimulation.desktop.ui.editor.utils.Topics;
import com.company.warehouse.application.utils.Messages;
import com.company.warehouse.simulation.Model;
import com.amalgamasimulation.desktop.ui.views.TableView;
import com.amalgamasimulation.desktop.utils.MessageManager;
import com.amalgamasimulation.viewupdater.service.IViewUpdaterService;
import com.amalgamasimulation.utils.format.Formats;

public class SimulationStatisticsPart {

	@Inject
	@Translation
	private Messages messages;
	
	@Inject
	private AppData appData;
	
	@Inject
	private IViewUpdaterService viewUpdaterService;
	
	@Inject
	private MessageManager messageManager;	
	private TableView<Indicator> tableView;

	@PostConstruct
	public void createComposite(Composite parent) {
		tableView = new TableView<>(parent, Collections.emptyList(), false, true);
		tableView.addColumn(messages.SIMULATION_STATS_obj_INDICATOR_col_NAME, 	200, indicator -> indicator.label);
		tableView.addColumn(messages.SIMULATION_STATS_obj_INDICATOR_col_VALUE, 	70, indicator ->  indicator.formatter.apply(indicator.value.get()));
		
		viewUpdaterService.getStatsUpdater().addView(tableView);
		messageManager.subscribe(Topics.SHOW_MODEL, this::onShowModel, true);
	}
	
	protected void onShowModel(Model model) {
		if(model != null) {
			List<Indicator> data = List.of(
					new Indicator(messages.INDICATOR_ARCS, () -> (double)appData.getScenario().getArcs().size(), Formats.getDefaultFormats()::noDecimals, false),
					new Indicator(messages.INDICATOR_NODES, () -> (double)appData.getScenario().getNodes().size(), Formats.getDefaultFormats()::noDecimals, false)					
					);
			tableView.setData(data);	
		} else {
			tableView.setData(Collections.emptyList());
		}
	}
	
	public class Indicator {		
		String label;
		Supplier<Double> value;
		Function<Double, String> formatter;
		boolean bold;
		public Indicator(String label, Supplier<Double> value, Function<Double, String> formatter, boolean bold) {
			this.value = value;
			this.formatter = formatter;
			this.label = label;
			this.bold = bold;
		}	
	}
	
}

