package com.company.warehouse.application.animation;

import java.awt.Color;
import java.util.Objects;

import com.amalgamasimulation.animation.shapes.shapes2d.GroupShape;
import com.amalgamasimulation.animation.shapes.shapes2d.RectangleShape;
import com.amalgamasimulation.geometry.Point;
import com.amalgamasimulation.utils.Colors;
import com.company.warehouse.simulation.Gate;
import com.company.warehouse.simulation.Model;
import com.company.warehouse.simulation.graph.Node;

public class TruckAtGateShape extends GroupShape {

    public TruckAtGateShape(Gate gate, Model model) {
        final var entrance = gate.getEntrance();

        withVisibility(() -> gate.getTruck().isPresent());
        withPoint(entrance.getPoint());
        withRotationAngle(arcDirectionFor(entrance, model));
        withShape(new RectangleShape(() -> new Point(-6, 0), () -> 12.0, () -> 32.0)
                .withFillColor(Colors.lightGrey)
                );
        withShape(
            new RectangleShape(
                () -> new Point(-6, 32.0 * (1 - cargoLevel(gate))),
                () -> 12.0,
                () -> 32.0 * cargoLevel(gate)
            ).withFillColor(Color.green)
        );
        withShape(new RectangleShape(() -> new Point(-5, 32), () -> 10.0, () -> 8.0)
                .withFillColor(Colors.dodgerBlue)
                );
        withFixedScale(1);
    }

    /**
     * @param gate
     * @return  a fraction of capacity in use
     */
    private double cargoLevel(Gate gate) {
        return gate.getTruck()
                .map(t -> t.getPalletCount() / (double) t.getCapacity())
                .orElse(0.0);
    }

    /**
     * Calculates the orientation for the truck image.
     * @param node  a parking location
     * @param model  warehouse simulation model instance
     * @return  a direction opposite to the arc connected to the node
     */
    private static double arcDirectionFor(Node node, Model model) {
        return - Objects.requireNonNull(
            model.getGraphEnvironment().getGraphNode(node).getFirstArcIn(),
            "No arc comes to the gate entrance node"
        ).getValue().getPolyline().getLastSegment().getHeading();
    }
}