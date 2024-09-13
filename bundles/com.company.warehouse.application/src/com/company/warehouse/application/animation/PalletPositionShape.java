package com.company.warehouse.application.animation;

import java.awt.Color;

import com.amalgamasimulation.animation.shapes.shapes2d.GroupShape;
import com.amalgamasimulation.animation.shapes.shapes2d.RectangleShape;
import com.amalgamasimulation.geometry.Point;
import com.company.warehouse.simulation.PalletPosition;

public class PalletPositionShape extends GroupShape {

    public PalletPositionShape(PalletPosition p) {
        super(p.getNode().getPoint());
        withShape(new RectangleShape(() -> new Point(-5, -5), () -> 10.0, () -> 10.0)
                .withLineColor(Color.gray)
                .withFillColor(() -> p.isAvailableFor(false) ? Color.green : Color.gray)
                );
    }

}