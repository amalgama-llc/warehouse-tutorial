package com.company.warehouse.simulation.equipment;

import com.amalgamasimulation.engine.Engine;
import com.amalgamasimulation.graphagent.GraphAgent;
import com.amalgamasimulation.graphagent.GeometricGraphPosition;
import com.amalgamasimulation.graphagent.GraphEnvironment;
import com.company.warehouse.simulation.PalletContainer;
import com.company.warehouse.simulation.graph.Arc;
import com.company.warehouse.simulation.graph.Node;

/**
 * A forklift simulation.
 *
 * Extends <code>GraphAgent</code> as this thing is about to move around the warehouse graph.
 */
public class Forklift extends GraphAgent<Node, Arc> {

    /**
     * Having object name is handy for monitoring the system state and for debugging.
     */
    private final String name;

    /**
     * A garage location.
     */
    private final Node base;

    /**
     * A movement speed characteristic of this model of equipment.
     */
    private final double velocity;

    /**
     * Amount of time required for loading a pallet.
     */
    private final double loadingTime;

    /**
     * Amount of time required for unloading a pallet.
     */
    private final double unloadingTime;

    private boolean loaded;
    public boolean isLoaded() {
        return loaded;
    }
    
    /**
     * User provided callback to notify completion of a current action.
     */
    private Runnable onComplete;

    public Forklift(Engine engine, GraphEnvironment<Node, Arc, ?> graphEnvironment, String name, Node base,
            double velocity, double loadingTime, double unloadingTime) {
        super(engine);
        this.name = name;
        this.base = base;
        this.velocity = velocity;
        this.loadingTime = loadingTime;
        this.unloadingTime = unloadingTime;

        // Immerse the forklift to the graph environment
        setGraphEnvironment(graphEnvironment);
        // and place it to the starting node..
        jumpTo(base);
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     * Starts the movement from current to specified location.
     *
     * @param node  target location
     * @param onComplete  callback to notify about arrival
     */
    public void moveTo(Node node, Runnable onComplete) {
        resetAction(onComplete);
        moveTo(node, velocity);
    }

    /**
     * Starts the movement from current location to garage.
     *
     * @param onComplete  callback to notify about arrival
     */
    public void moveToBase(Runnable onComplete) {
        moveTo(base, onComplete);
    }

    /**
     * Aborts the action in progress. E.g. stops moving.
     */
    public void cancelCurrentAction() {
        resetAction(null);
    }

    private void resetAction(Runnable onComplete) {
        cancelMoving();
        this.onComplete = onComplete;
    }

    public void load(PalletContainer container, Runnable onComplete) {
        resetAction(onComplete);
        engine.scheduleRelative(loadingTime, () -> {
            loaded = true;
            container.placePallet(false);
            finishAction();
        });
    }

    public void unload(PalletContainer container, Runnable onComplete) {
        resetAction(onComplete);
        engine.scheduleRelative(unloadingTime, () -> {
            loaded = false;
            container.placePallet(true);
            finishAction();
        });
    }

    /**
     * This overriden method is called by GraphAgent class when the movement finishes at the destination point.
     */
    @Override
    public void onDestinationReached(GeometricGraphPosition<Node, Arc> node) {
        super.onDestinationReached(node);
        finishAction();
    }

    /**
     * Invoke callback to notify the outer world that the current action is complete.
     * Reset the callback reference.
     */
    private void finishAction() {
        if (onComplete != null) {
            var callback = onComplete;
            onComplete = null;
            callback.run();
        }
    }

    /**
     * It's easier to debug with a meaningful <code>toString()</code> method.
     */
    @Override
    public String toString() {
        return name;
    }

}