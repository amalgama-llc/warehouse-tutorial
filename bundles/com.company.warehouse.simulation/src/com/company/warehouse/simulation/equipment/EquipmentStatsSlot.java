package com.company.warehouse.simulation.equipment;

import com.amalgamasimulation.core.scheduling.Slot;

public class EquipmentStatsSlot extends Slot {

    private final Forklift forklift;
    private final IEquipmentState state;
    private boolean closed = false;

    public EquipmentStatsSlot(Forklift forklift, IEquipmentState state) {
        // initially assign current time as beginTime & endTime
        super(forklift.time(), forklift.time());
        this.forklift = forklift;
        this.state = state;
    }

    /**
     * @return saved endTime if closed, current time otherwise
     */
    @Override
    public double endTime() {
        return closed ? super.endTime() : forklift.time();
    }

    /**
     * Saves current time as endTime
     */
    public void close() {
        max = forklift.time();
        closed = true;
    }

    public IEquipmentState getState() {
        return state;
    }

    public Forklift getForklift() {
        return forklift;
    }

}