package com.company.warehouse.simulation;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

import com.company.warehouse.simulation.graph.Node;

public class PalletPosition implements PalletContainer {

    private final Node node;

    /**
     * Two callbacks to notify about position busy state change.
     * Former, when pallet is placed in this position.
     * Latter, when place is freed.
     */
    private final Map<Boolean, Optional<Consumer<PalletPosition>>> onBusyCallbacks = new HashMap<>(Map.of(
            true, Optional.empty(),
            false, Optional.empty()));

    /**
     * Whether a pallet is present here.
     */
    private boolean busy;

    private boolean reserved;
    public boolean isReserved() {
        return reserved;
    }

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

    public void reserve() {
        if (reserved) {
            throw new RuntimeException("Can't reserve already reserved position");
        }
        reserved = true;
    }

    @Override
    public void placePallet(boolean loading) {
        if (busy == loading) {
            throw new RuntimeException("Can't " + (busy ? "put to a busy" : "take from a free") + " position");
        }
        busy = loading;
        reserved = false;
        node.getGraphEnvironment().rerouteAgents();
        onBusyCallbacks.get(busy).ifPresent(callback -> callback.accept(this));
    }

    public void onBusySubscribe(boolean busy, Consumer<PalletPosition> callback) {
        onBusyCallbacks.get(busy)
            .ifPresent(c -> {throw new RuntimeException("onBusy callback already present");});
        onBusyCallbacks.put(busy, Optional.of(callback));
        if (this.busy == busy) {
            callback.accept(this);
        }
    }

    @Override
    public String toString() {
        return "PalletPosition [" + node + ", busy=" + busy + ", reserved=" + reserved + "]";
    }
}