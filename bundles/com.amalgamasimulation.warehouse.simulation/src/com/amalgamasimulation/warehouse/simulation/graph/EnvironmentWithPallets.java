package com.amalgamasimulation.warehouse.simulation.graph;

import com.amalgamasimulation.graphagent.GraphEnvironment;

@SuppressWarnings("serial")
public class EnvironmentWithPallets extends GraphEnvironment<Node, Arc, Void> {

    private static final double BUSY_NODE_WEIGHT = 10_000;

    @Override
    public double getNodeWeight(Node node, Void k) {
        var busy = node.getPalletPosition()
            .map(p -> p.isAvailableFor(false))
            .orElse(false);

        return busy ? BUSY_NODE_WEIGHT : 0;
    }
    
    public void rerouteAgents() {
        getAgents().stream()
            .filter(a -> a.hasPath())
            .forEach(a -> a.moveTo(a.getGraphPath().getLastNode(), a.getVelocity()));
    }

}