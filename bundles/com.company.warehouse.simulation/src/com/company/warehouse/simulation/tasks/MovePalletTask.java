package com.company.warehouse.simulation.tasks;

import com.amalgamasimulation.engine.Engine;
import com.amalgamasimulation.engine.StateMachine;
import com.company.warehouse.simulation.PalletContainer;
import com.company.warehouse.simulation.equipment.Forklift;
import com.company.warehouse.simulation.equipment.IEquipmentState;

/**
 * A task for one forklift to move a pallet from its current position to another.
 */
public class MovePalletTask extends ForkliftTask {

    /**
     * All the possible states for the <code>StateMachine</code>.
     */
    public enum State implements IEquipmentState {
        PENDING(false), // not utilizing
        MOVE_TO_LOADING(true),
        LOADING(true),
        MOVE_TO_UNLOADING(true),
        UNLOADING(true),
        FINISHED(false), // not utilizing
        ;

        private boolean utilized;
        @Override
        public boolean isUtilized() {
            return utilized;
        }

        private State(boolean utilized) {
            this.utilized = utilized;
        }
    }

    private final PalletContainer from;
    private final PalletContainer to;
    private final StateMachine<State> control;

    public MovePalletTask(Engine engine, Forklift forklift, PalletContainer from, PalletContainer to) {
        super(engine, forklift);
        this.from = from;
        this.to = to;
        // Create a StateMachine providing all the states and specifying a starting one.
        control = new StateMachine<>(State.values(), State.PENDING, engine)
                // Declare all allowed transitions
                .addTransition(State.PENDING, State.MOVE_TO_LOADING)
                .addTransition(State.MOVE_TO_LOADING, State.LOADING)
                .addTransition(State.LOADING, State.MOVE_TO_UNLOADING)
                .addTransition(State.MOVE_TO_UNLOADING, State.UNLOADING)
                .addTransition(State.UNLOADING, State.FINISHED)
                // Define state handlers
                .addEnterAction(State.MOVE_TO_LOADING, state -> moveToLoading())
                .addEnterAction(State.LOADING, state -> loading())
                .addEnterAction(State.MOVE_TO_UNLOADING, state -> moveToUnloading())
                .addEnterAction(State.UNLOADING, state -> unloading())
                .addEnterAction(State.FINISHED, state -> finished())
                ;
    }

    /**
     * An entry point for any task.
     */
    @Override
    public void run() {
        control.receiveMessage(State.MOVE_TO_LOADING);
    }

    private void moveToLoading() {
        forklift.moveTo(from.getNode(),
                () -> control.receiveMessage(State.LOADING));
    }

    private void loading() {
        forklift.load(from,
                () -> control.receiveMessage(State.MOVE_TO_UNLOADING));
    }

    private void moveToUnloading() {
        forklift.moveTo(to.getNode(),
                () -> control.receiveMessage(State.UNLOADING));
    }

    private void unloading() {
        forklift.unload(to,
                () -> control.receiveMessage(State.FINISHED));
    }

    private void finished() {
        finish();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + '@' + Integer.toHexString(hashCode()) + " [from=" + from + ", to=" + to + "]";
    }

}