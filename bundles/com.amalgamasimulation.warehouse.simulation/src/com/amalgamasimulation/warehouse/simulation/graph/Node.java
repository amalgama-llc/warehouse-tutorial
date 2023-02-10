package com.amalgamasimulation.warehouse.simulation.graph;

import java.util.Objects;
import java.util.Optional;

import com.amalgamasimulation.geometry.Point;
import com.amalgamasimulation.graphagent.AgentGraphNodeImpl;
import com.amalgamasimulation.warehouse.simulation.PalletPosition;

public class Node extends AgentGraphNodeImpl {

    private final String id;
    
    private PalletPosition palletPosition;
    
    private final EnvironmentWithPallets graphEnvironment;
    
    public Node(Point point, EnvironmentWithPallets graphEnvironment, String id) {
        super(point);
        this.graphEnvironment = graphEnvironment;
        this.id = id;
    }

    public EnvironmentWithPallets getGraphEnvironment() {
        return graphEnvironment;
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
