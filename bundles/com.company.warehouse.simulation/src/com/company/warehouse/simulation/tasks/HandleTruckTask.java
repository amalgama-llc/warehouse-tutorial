package com.company.warehouse.simulation.tasks;

import com.amalgamasimulation.engine.Engine;
import com.company.warehouse.datamodel.Direction;
import com.company.warehouse.simulation.Gate;
import com.company.warehouse.simulation.equipment.Forklift;
import com.company.warehouse.simulation.equipment.Truck;

public class HandleTruckTask extends Task {

    private final Truck truck;
    private final Gate gate;
    private final Forklift forklift;
    private final boolean loading;

    public HandleTruckTask(Engine engine, Truck truck, Gate gate, Forklift forklift) {
        super(engine);
        this.truck = truck;
        this.gate = gate;
        this.forklift = forklift;
        loading = truck.getDirection() == Direction.OUT;
    }

    @Override
    public void run() {
        if (truck.isAvailableFor(loading)) {
            gate.getStorageArea().reservePlace(loading, place ->
                new MovePalletTask(engine, forklift, truck, place, loading).start(
                    () -> run()
                )
            );
        } else {
            finish();
        }
    }

}