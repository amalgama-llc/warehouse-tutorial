package com.company.warehouse.simulation.graph;

import java.util.Objects;
import java.util.Optional;

import com.amalgamasimulation.geometry.Point;
import com.amalgamasimulation.graphagent.AgentGraphNodeImpl;
import com.company.warehouse.simulation.PalletPosition;

/**
 * This class represents a node of the network. It extends the AgentGraphNodeImpl
 * class from Amalgama Platform's Graph Agent Library
 *
 */
public class Node extends AgentGraphNodeImpl {

    private final String id;

    private PalletPosition palletPosition;

    public Node(Point point, String id) {
        super(point);
        this.id = id;
    }

    public Optional<PalletPosition> getPalletPosition() {
        return Optional.ofNullable(palletPosition);
    }

    public void setPalletPosition(PalletPosition palletPosition) {
        Objects.requireNonNull(palletPosition);

        if (this.palletPosition != null) {
            throw new RuntimeException("Can't assign " + palletPosition + " to " + this + " as " + this.palletPosition + " already assigned!");
        }

        this.palletPosition = palletPosition;
    }

    @Override
    public String toString() {
        return id;
    }
}
