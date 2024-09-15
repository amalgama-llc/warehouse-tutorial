package com.company.warehouse.simulation.tasks;

import com.amalgamasimulation.engine.Engine;
import com.amalgamasimulation.engine.StateMachine;
import com.company.warehouse.simulation.equipment.Forklift;
import com.company.warehouse.simulation.equipment.IEquipmentState;

public class IdlingTask extends ForkliftTask {

    public enum State  implements IEquipmentState {
        PENDING,
        MOVE_TO_BASE,
        IDLING,
        CANCELLED,
        ;

        /**
         * Idling task is non utilizing by definition.
         */
        @Override
        public boolean isUtilized() {
            return false;
        }
    }

    private StateMachine<State> control;

    public IdlingTask(Engine engine, Forklift forklift) {
        super(engine, forklift);
        control = new StateMachine<>(State.values(), State.PENDING, engine)
                .addTransition(State.PENDING, State.MOVE_TO_BASE)
                .addTransition(State.MOVE_TO_BASE, State.IDLING)
                .addTransition(State.PENDING, State.CANCELLED)
                .addTransition(State.MOVE_TO_BASE, State.CANCELLED)
                .addTransition(State.IDLING, State.CANCELLED)
                .addEnterAction(State.MOVE_TO_BASE, state -> moveToBase())
                .addEnterAction(State.CANCELLED, state -> cancelling())
                .addEnterAction((state, message) -> forklift.putStatsSlot(state))
                ;
    }

    @Override
    public void run() {
        control.receiveMessage(State.MOVE_TO_BASE);
    }

    /**
     * Cancels task execution and also aborts current action of the forklift.
     */
    public void cancel() {
        control.receiveMessage(State.CANCELLED);
    }

    private void moveToBase() {
        forklift.moveToBase(
                () -> control.receiveMessage(State.IDLING));
    }

    private void cancelling() {
        forklift.cancelCurrentAction();
    }

    public Forklift getForklift() {
        return forklift;
    }

}