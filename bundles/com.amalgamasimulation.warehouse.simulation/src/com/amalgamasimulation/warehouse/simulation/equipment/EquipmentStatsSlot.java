package com.amalgamasimulation.warehouse.simulation.equipment;

import com.amalgamasimulation.engine.Engine;
import com.amalgamasimulation.scheduling.Slot;

public class EquipmentStatsSlot extends Slot {

	private final Engine engine;
	private final IEquipmentState state;
	private final Forklift forklift;
	private boolean closed = false;
	
	public EquipmentStatsSlot(Engine engine, IEquipmentState state, Forklift forklift) {
		// initially assign current time as beginTime & endTime
		super(engine.time(), engine.time());
		this.engine = engine;
		this.state = state;
		this.forklift = forklift;
	}
	
	/**
	 * @return saved endTime if closed, current time otherwise
	 */
	@Override
	public double endTime() {
		return closed ? super.endTime() : engine.time();
	}

	/**
	 * Saves current time as endTime
	 */
	public void close() {
		endTime = engine.time();
		closed = true;		
	}

	public IEquipmentState getSate() {
		return state;
	}

	public Forklift getForklift() {
		return forklift;
	}

}