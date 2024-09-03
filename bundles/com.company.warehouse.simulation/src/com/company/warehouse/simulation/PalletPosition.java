package com.company.warehouse.simulation;

import com.company.warehouse.simulation.graph.Node;

public class PalletPosition implements PalletContainer {

    private final Node node;

    /**
     * Whether a pallet is present here.
     */
    private boolean busy;

    public PalletPosition(Node node) {
        this.node = node;
        node.setPalletPosition(this);
    }

    @Override
    public Node getNode() {
        return node;
    }

    public boolean isBusy() {
        return busy;
    }

    @Override
    public boolean isAvailableFor(boolean loading) {
        return busy != loading;
    }

    @Override
    public void placePallet(boolean loading) {
        if (busy == loading) {
            throw new RuntimeException("Can't " + (busy ? "put to a busy" : "take from a free") + " position");
        }
        busy = loading;
    }

    @Override
    public String toString() {
        return "PalletPosition [" + node + ", busy=" + busy + "]";
    }
}