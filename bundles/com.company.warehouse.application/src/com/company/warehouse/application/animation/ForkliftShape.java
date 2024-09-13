package com.company.warehouse.application.animation;

import java.awt.Color;

import com.amalgamasimulation.animation.shapes.shapes2d.GroupShape;
import com.amalgamasimulation.animation.shapes.shapes2d.RectangleShape;
import com.amalgamasimulation.geometry.Point;
import com.amalgamasimulation.utils.Colors;
import com.company.warehouse.simulation.equipment.Forklift;

public class ForkliftShape extends GroupShape {

    private Forklift forklift;

    public ForkliftShape(Forklift forklift) {
        super(() -> forklift.getCurrentAnimationPoint());
        this.forklift = forklift;
        withShape(new RectangleShape(() -> new Point(-5, -5), () -> 10.0, () -> 10.0)
                .withFillColor(Colors.orange)
                );
        withShape(new RectangleShape(() -> new Point(-cargoSize(), 10 - cargoSize()), () -> cargoSize() * 2, () -> cargoSize() * 2)
                .withFillColor(() -> cargoColor())
                );
        withRotationAngle(() -> - forklift.getCurrentAnimationHeading());
        withFixedScale(1);
    }

    private double cargoSize() {
        return forklift.isLoaded() ? 6 : 5;
    }

    private Color cargoColor() {
        return forklift.isLoaded() ? Color.green : Color.black;
    }
}