package com.company.warehouse.simulation;

import com.amalgamasimulation.engine.Engine;
import com.amalgamasimulation.service.ServiceWithResources;
import com.amalgamasimulation.service.ServiceWithResources.ResourceSeizingRule;
import com.company.warehouse.datamodel.Direction;
import com.company.warehouse.simulation.equipment.Forklift;
import com.company.warehouse.simulation.equipment.Truck;
import com.company.warehouse.simulation.tasks.MovePalletTask;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.function.BiConsumer;

public class Dispatcher {

    private final Engine engine;
    private final Model model;
    private final Map<Direction, ServiceWithResources<Truck, Gate>> gates = new HashMap<>();

    private final Map<Direction, Queue<Gate>> pendingGatesWithTrucks = Map.of(
            Direction.IN, new LinkedList<>(),
            Direction.OUT, new LinkedList<>()
            );

    private final Queue<Forklift> availableForklifts = new LinkedList<>();
    private final Queue<BiConsumer<Forklift, Runnable>> forkliftRequests = new LinkedList<>();

    public Dispatcher(Model model) {
        engine = model.engine();
        this.model = model;

        model.getForklifts().forEach(f -> addAvailableForklift(f));

        for (var d : Direction.values()) {
            gates.put(d, new ServiceWithResources<>(engine, model.getGatesInDirection(d), ResourceSeizingRule.LEAST_USED));
        }
    }

    private void addAvailableForklift(Forklift forklift) {
        final var request = forkliftRequests.poll();

        if (request != null) {
            request.accept(forklift, () -> addAvailableForklift(forklift));
        } else {
            availableForklifts.add(forklift);
        }
    }

    private void requestForklift(BiConsumer<Forklift, Runnable> request) {
        final var forklift = availableForklifts.poll();

        if (forklift != null) {
            request.accept(forklift, () -> addAvailableForklift(forklift));
        } else {
            forkliftRequests.add(request);
        }
    }

    public void truckArrived(Truck truck) {
        final var suitableGates = gates.get(truck.getDirection());
        suitableGates.placeRequest(truck, (s, gate) ->
            handleTruckOnGate(truck, gate, () ->
                suitableGates.release(truck)
            )
        );
    }

    private void handleTruckOnGate(Truck truck, Gate gate, Runnable onComplete) {
        final var direction = gate.getDirection();
        final var oppositeDirection = (direction == Direction.IN) ? Direction.OUT : Direction.IN;

        gate.parkTruck(truck);

        final var oppositeGate = pendingGatesWithTrucks.get(oppositeDirection).poll();

        if (oppositeGate == null) {
            pendingGatesWithTrucks.get(direction).add(gate);
            return;
        }

        handleGatePair(gate, oppositeGate, () -> {
            gate.unparkTruck(truck);
            onComplete.run();
        });
    }

    private void handleGatePair(Gate gate, Gate oppositeGate, Runnable onComplete) {
        final var truck = gate.getTruck().get();
        final var oppositeTruck = oppositeGate.getTruck().get();

        requestForklift((forklift, forkliftReleaser) ->
            handleGatePairWithForklift(truck, oppositeTruck, forklift, () -> {
                forkliftReleaser.run();
                oppositeGate.unparkTruck(oppositeTruck);
                gates.get(oppositeTruck.getDirection()).release(oppositeTruck);
                onComplete.run();
            })
        );
    }

    private void handleGatePairWithForklift(Truck truck, Truck oppositeTruck, Forklift forklift, Runnable onComplete) {
        final boolean loading = truck.getDirection() == Direction.OUT;

        if (!truck.isAvailableFor(loading)) {
            onComplete.run();
            return;
        }

        newMovePalletTask(forklift, truck, oppositeTruck, loading).start(() ->
            handleGatePairWithForklift(truck, oppositeTruck, forklift, onComplete)
        );
    }

    private MovePalletTask newMovePalletTask(Forklift forklift, PalletContainer a, PalletContainer b, boolean reverse) {
        final PalletContainer from = reverse ? b : a;
        final PalletContainer to = reverse ? a : b;
        return new MovePalletTask(engine, forklift, from, to);
    }

}