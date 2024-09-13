package com.company.warehouse.simulation.tasks;

import com.amalgamasimulation.engine.Engine;
import com.company.warehouse.simulation.equipment.Forklift;

public abstract class ForkliftTask extends Task {

    protected final Forklift forklift;

    public ForkliftTask(Engine engine, Forklift forklift) {
        super(engine);
        this.forklift = forklift;
    }

}