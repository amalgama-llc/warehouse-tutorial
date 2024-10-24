package com.company.warehouse.application.parts.simulation;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import com.amalgamasimulation.charts.TimeGanttChart;
import com.amalgamasimulation.charts.axes.ticks.AxisTimeStyle;
import com.amalgamasimulation.charts.swt.ChartEnvironmentSwt;
import com.amalgamasimulation.charts.utils.LabelSide;
import com.amalgamasimulation.charts.visualsets.GanttVisualSet;
import com.amalgamasimulation.desktop.ui.editor.utils.Topics;
import com.amalgamasimulation.desktop.utils.MessageManager;
import com.amalgamasimulation.engine.service.IEngineService;
import com.amalgamasimulation.utils.Colors;
import com.amalgamasimulation.utils.format.Formats;
import com.amalgamasimulation.viewupdater.service.IViewUpdaterService;
import com.company.warehouse.application.EquipmentStateUI;
import com.company.warehouse.simulation.Model;
import com.company.warehouse.simulation.equipment.EquipmentStatsSlot;
import com.company.warehouse.simulation.graph.agents.Agent.AgentState;
import com.company.warehouse.simulation.graph.agents.stats.slots.AgentStatsSlot;

public class GanttChartPart {


	@Inject
	private IEngineService engineservice;
	
	@Inject
	private IViewUpdaterService viewUpdaterService;
	
	@Inject
	private MessageManager messageManager;
	
	private TimeGanttChart ganttChart;
	
	@PostConstruct
	public void createComposite(Composite parent, Shell shell) {
		initializeGanttChart(parent);
	}
	
	private void initializeGanttChart( Composite parent ) {
		ganttChart =  new TimeGanttChart (new ChartEnvironmentSwt( parent ), "Gantt chart", ChronoUnit.MINUTES );
		viewUpdaterService.getDefaultUpdater().addView( () -> updateView(), () -> false );
		messageManager.subscribe(Topics.SHOW_MODEL, this::updateContent, true);
	}
	
	private void updateContent(Model model) {
		ganttChart.getVisualSetContainer().clear();
		if (model != null) {
			ganttChart.getXAxis().setTimeStyle(AxisTimeStyle.getDefault(model.timeToDate(0), model.timeUnit()))
				.setDisplayedRange(0,  model.minute() * 30);
	        for (var forklift : model.getForklifts()) {
	            var visualSet = new GanttVisualSet<>(forklift.getName(), () -> forklift.getStatsSlots(), t -> t.beginTime(), t -> t.endTime())
	                    .setBackgroundColor(s -> EquipmentStateUI.colorOf(s.getState()))
	                    .setLabelText(LabelSide.MIDDLE_CENTER, s -> EquipmentStateUI.nameOf(s.getState()), s -> 12.0, s -> Colors.white)
	                    .setLabelText(LabelSide.TOP_LEFT, this::getBeginTimeText, s -> 9.0, s -> Colors.white);
	            ganttChart.getVisualSetContainer().addVisualSet(visualSet);
	        }
		}
		ganttChart.redraw();
	}
	
	private String getBeginTimeText(EquipmentStatsSlot slot) {
		return Formats.getDefaultFormats().dayMonthLongYearHoursMinutes(timeToDate(slot.beginTime()));
	}
	
	private void updateView() {
		ganttChart.redraw();
	}
	
	private LocalDateTime timeToDate(double time) {
		return engineservice.getEngine().timeToDate(time);
	}
	
	private String getTopLeftText(AgentStatsSlot slot) {
		return Formats.getDefaultFormats().dayMonthLongYearHoursMinutes(timeToDate(slot.beginTime()));
	}
	
	private String getTopRightText(AgentStatsSlot slot) {
		return Formats.getDefaultFormats().dayMonthLongYearHoursMinutes(timeToDate(slot.endTime()));
	}
	
	private String getTopCenterText(AgentStatsSlot slot) {
		return Formats.getDefaultFormats().duration(Duration.between(timeToDate(slot.beginTime()), timeToDate(slot.endTime())));
	}
	
	private String getMiddleCenterText(AgentStatsSlot slot) {
		return slot.getState().toString();
	}
}

