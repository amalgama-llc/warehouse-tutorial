package com.amalgamasimulation.warehouse.application.parts.simulation;

import java.time.temporal.ChronoUnit;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import com.amalgamasimulation.charts.TimeGanttChart;
import com.amalgamasimulation.charts.axes.ticks.AxisTimeStyle;
import com.amalgamasimulation.charts.swt.ChartEnvironmentSwt;
import com.amalgamasimulation.charts.utils.LabelSide;
import com.amalgamasimulation.desktop.utils.MessageManager;
import com.amalgamasimulation.viewupdater.service.IViewUpdaterService;
import com.amalgamasimulation.visualsets.VisualSets;
import com.amalgamasimulation.warehouse.application.EquipmentStateUI;
import com.amalgamasimulation.warehouse.application.utils.Topics;
import com.amalgamasimulation.warehouse.simulation.Model;

public class GanttChartPart {

	@Inject
	private IViewUpdaterService viewUpdaterService;
	
	private TimeGanttChart ganttChart;
	
	@Inject
	private MessageManager messageManager;
	
	@PostConstruct
	public void createComposite(Composite parent, Shell shell) {
		initializeGanttChart(parent);
	}
	
	private void initializeGanttChart(Composite parent) {
		ganttChart = new TimeGanttChart(new ChartEnvironmentSwt( parent ), "Gantt chart", ChronoUnit.HOURS);
		viewUpdaterService.getDefaultUpdater().addView(() -> updateView(), () -> false);
		messageManager.subscribe(Topics.SHOW_MODEL, this::updateContent, true);
	}
	
	private void updateView() {
		ganttChart.redraw();
	}
	
	private void updateContent(Model model) {
		ganttChart	.getXAxis()
					.setTimeStyle(AxisTimeStyle.getDefault(model.timeToDate(0), model.timeUnit()))
					.setDisplayedRange(0, model.dateToTime(model.timeToDate(model.getEndTime())));
		ganttChart.getVisualSetContainer().clear();
		for (var forklift : model.getForklifts()) {
			var visualSet = VisualSets
					.gantt(forklift.getName(), () -> forklift.getStatsSlots())
					.setBackgroundColor(s -> EquipmentStateUI.colorOf(s.getSate()))
					.addLabel(LabelSide.MIDDLE_CENTER, s -> EquipmentStateUI.nameOf(s.getSate()))
					.addLabel(LabelSide.TOP_LEFT, s -> "%.2f".formatted(s.beginTime()));
			ganttChart.getVisualSetContainer().addVisualSet(visualSet);
		}
		ganttChart.redraw();
	}
}


