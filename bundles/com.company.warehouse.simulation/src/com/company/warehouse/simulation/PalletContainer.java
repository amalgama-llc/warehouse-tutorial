package com.company.warehouse.simulation;

import com.company.warehouse.simulation.graph.Node;

/**
 * A container that can store pallets. Pallets could be put or taken to/from a container.
 * Is located in a graph node.
 */
public interface PalletContainer {

    /**
     * @param loading  do we check loading (<code>true</code>) or unloading (<code>false</code>) availability
     * @return  if it is possible to load (or unload) one pallet to (or from) the container.
     */
    boolean isAvailableFor(boolean loading);

    /**
     * Loads (or unloads) one pallet to (or from) the container.
     * @param loading  perform loading (<code>true</code>) or unloading
     */
    void placePallet(boolean loading);

    Node getNode();

}