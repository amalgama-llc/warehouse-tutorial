package com.company.warehouse.simulation.equipment;

import com.company.warehouse.datamodel.Direction;
import com.company.warehouse.simulation.PalletContainer;
import com.company.warehouse.simulation.graph.Node;

public class Truck implements PalletContainer {

    /**
     * If this truck is incoming our outgoing.
     */
    private final Direction direction;

    /**
     * How many pallets can fit in the truck.
     */
    private final int capacity;

    /**
     * How many pallets are currently in the truck.
     */
    private int palletCount;

    /**
     * Location where truck is parked.
     */
    private Node node;

    public Truck(Direction direction, int capacity) {
        this.direction = direction;
        this.capacity = capacity;
        // Incoming trucks are initially full, outgoing are empty
        palletCount = direction == Direction.IN ? capacity : 0;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getPalletCount() {
        return palletCount;
    }

    @Override
    public Node getNode() {
        if (node == null) {
            throw new RuntimeException("Truck is not parked");
        }

        return node;
    }

    /**
     * Establish parking.
     * @param node  a parking location node
     */
    public void park(Node node) {
        if (node == null) {
            throw new RuntimeException("Can't park to null");
        } else if (this.node != null) {
            throw new RuntimeException("Truck is already parked");
        }

        this.node = node;
    }

    public void unpark(Node node) {
        if (node == null) {
            throw new RuntimeException("Can't unpark from null");
        } else if (this.node != node) {
            throw new RuntimeException("Truck is not parked to this place");
        }

        this.node = null;
    }

    @Override
    public boolean isAvailableFor(boolean loading) {
        return loading ? palletCount < capacity : palletCount > 0;
    }

    @Override
    public void placePallet(boolean loading) {
        if (!isAvailableFor(loading)) {
            throw new RuntimeException("Truck not available for loading (" + loading + "): " + this);
        }

        palletCount += loading ? 1 : -1;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + '@' + Integer.toHexString(hashCode());
    }

}