package com.amalgamasimulation.warehouse.simulation;

import java.util.Objects;
import java.util.Optional;

import com.amalgamasimulation.warehouse.datamodel.Direction;
import com.amalgamasimulation.warehouse.simulation.equipment.Truck;
import com.amalgamasimulation.warehouse.simulation.graph.Node;

public class Gate {

	/**
	 * Whether this gate is incoming or outgoing.
	 */
	private final Direction direction;
	
	/**
	 * A node to park trucks at.
	 */
	private final Node entrance;

	/**
	 * A parked truck.
	 */
	private Truck truck;
	
	private final StorageArea storage;

	public Gate(Direction direction, Node entrance, StorageArea storage) {
        this.direction = direction;
        this.entrance = entrance;
        this.storage = storage;
    }

    public StorageArea getStorage() {
        return storage;
    }
	
	public Direction getDirection() {
		return direction;
	}
	
	public Node getEntrance() {
		return entrance;
	}

	public Optional<Truck> getTruck() {
		return Optional.ofNullable(truck);
	}
	
	/**
	 * Parks a truck at this gate.
	 * @param truck
	 */
	public void parkTruck(Truck truck) {
		Objects.requireNonNull(truck);
		
		if (this.truck != null) {
			throw new RuntimeException("Can't park " + truck + " to " + this + " as " + this.truck + " already parked!");
		}
		
		this.truck = truck;
		truck.park(getEntrance());
	}
	
	public void unparkTruck(Truck truck) {
		Objects.requireNonNull(truck);
		
		if (this.truck != truck) {
			throw new RuntimeException("Can't unpark " + truck + " from " + this + " as another " + this.truck + " parked!");
		}
		
		truck.unpark(getEntrance());
		this.truck = null;
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + '@' + Integer.toHexString(hashCode());
	}

}
