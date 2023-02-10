package com.amalgamasimulation.warehouse.application.animation;

import com.amalgamasimulation.warehouse.simulation.graph.Arc;
import com.amalgamasimulation.animation.shapes.shapes2d.GroupShape;
import com.amalgamasimulation.animation.shapes.shapes2d.PolylineShape;
import com.amalgamasimulation.geometry.Point;


public class ArcShape extends GroupShape {
	
	public ArcShape(Arc road) {
		super(() -> new Point(0, 0));
		withShape(new PolylineShape(() -> road.getPolyline()));
		asStatic(() -> true);
	}
}

