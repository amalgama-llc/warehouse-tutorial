package com.company.warehouse.simulation;

import com.amalgamasimulation.engine.Engine;
import com.company.warehouse.datamodel.Direction;
import com.company.warehouse.simulation.equipment.Forklift;
import com.company.warehouse.simulation.equipment.Truck;
import com.company.warehouse.simulation.tasks.MovePalletTask;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class Dispatcher {

    private final Engine engine;
    private final Model model;
    private final Queue<Forklift> availableForklifts = new LinkedList<>();

    public Dispatcher(Model model) {
        engine = model.engine();
        this.model = model;
        availableForklifts.addAll(model.getForklifts());
    }

    public void truckArrived(Truck truck) {
        final var gate = model.getGatesInDirection(truck.getDirection()).stream()
                .filter(g -> g.getTruck().isEmpty())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No gate available to park " + truck));
        handleTruckOnGate(truck, gate);
    }

    private void handleTruckOnGate(Truck truck, Gate gate) {
        final var direction = gate.getDirection();
        final var oppositeDirection = (direction == Direction.IN) ? Direction.OUT : Direction.IN;

        gate.parkTruck(truck);

        model.getGatesInDirection(oppositeDirection).stream()
                .filter(g -> g.getTruck().isPresent())
                .findFirst()
                .ifPresent(oppositeGate ->
                    handleGatePair(gate, oppositeGate, Objects.requireNonNull(availableForklifts.poll(), "No available forklifts"))
                );
    }

    private void handleGatePair(Gate gate, Gate oppositeGate, Forklift forklift) {
        final var truck = gate.getTruck().get();
        final var oppositeTruck = oppositeGate.getTruck().get();
        final boolean loading = gate.getDirection() == Direction.OUT;

        if (!truck.isAvailableFor(loading)) {
            gate.unparkTruck(truck);
            oppositeGate.unparkTruck(oppositeTruck);
            availableForklifts.add(forklift);
            return;
        }

        newMovePalletTask(forklift, truck, oppositeTruck, loading).start(() ->
            handleGatePair(gate, oppositeGate, forklift)
        );
    }

    private MovePalletTask newMovePalletTask(Forklift forklift, PalletContainer a, PalletContainer b, boolean reverse) {
        final PalletContainer from = reverse ? b : a;
        final PalletContainer to = reverse ? a : b;
        return new MovePalletTask(engine, forklift, from, to);
    }

}