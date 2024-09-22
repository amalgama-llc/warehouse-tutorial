package com.company.warehouse.application.animation;

import com.amalgamasimulation.animation.shapes.shapes2d.GroupShape;
import com.amalgamasimulation.animation.shapes.shapes2d.RectangleShape;
import com.amalgamasimulation.utils.Colors;
import com.company.warehouse.simulation.graph.agents.Agent;
import com.amalgamasimulation.geometry.Point;

public class AgentShape extends GroupShape {
	public AgentShape(Agent agent) {
		super(() -> agent.getCurrentAnimationPoint());
		withShape(new RectangleShape(() -> new Point(-5, -5), () -> 10.0, () -> 30.0).withLineColor(Colors.darkBlue)
		        .withFillColor(Colors.blue));
		withShape(new RectangleShape(() -> new Point(-4, -9), () -> 8.0, () -> 4.0).withLineColor(Colors.green)
		        .withFillColor(Colors.green));
		withRotationAngle(() -> -agent.getHeading() + Math.PI);
		withFixedScale(1.3);
	}
}

