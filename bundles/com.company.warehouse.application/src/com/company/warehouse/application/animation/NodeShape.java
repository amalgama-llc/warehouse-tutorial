package com.company.warehouse.application.animation;

import com.company.warehouse.simulation.graph.Node;
import com.amalgamasimulation.animation.shapes.shapes2d.CircleShape;
import com.amalgamasimulation.animation.shapes.shapes2d.GroupShape;
import com.amalgamasimulation.geometry.Point;
import com.amalgamasimulation.utils.Colors;


public class NodeShape extends GroupShape {
	public NodeShape(Node node) {
		super(() -> node.getPoint());
		withShape(new CircleShape(new Point(0, 0), 5, Colors.black));
	}	
}

