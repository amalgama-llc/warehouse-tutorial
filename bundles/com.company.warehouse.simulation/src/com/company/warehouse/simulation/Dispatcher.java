package com.company.warehouse.simulation;

import com.amalgamasimulation.engine.Engine;
import com.amalgamasimulation.service.ServiceWithResources;
import com.amalgamasimulation.service.ServiceWithResources.ResourceSeizingRule;
import com.company.warehouse.datamodel.Direction;
import com.company.warehouse.simulation.equipment.Forklift;
import com.company.warehouse.simulation.equipment.Truck;
import com.company.warehouse.simulation.tasks.HandleTruckTask;
import com.company.warehouse.simulation.tasks.IdlingTask;
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

    private final Queue<IdlingTask> idlingTasks = new LinkedList<>();
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
            var idlingTask = new IdlingTask(engine, forklift);
            idlingTask.start(() -> {});
            idlingTasks.add(idlingTask);
        }
    }

    private void requestForklift(BiConsumer<Forklift, Runnable> request) {
        final var idlingTask = idlingTasks.poll();

        if (idlingTask != null) {
            idlingTask.cancel();
            final var forklift = idlingTask.getForklift();
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
        handleTruck(truck, gate, onComplete);
    }

    private void handleTruck(Truck truck, Gate gate, Runnable onComplete) {
        gate.parkTruck(truck);
        requestForklift((forklift, forkliftReleaser) ->
            new HandleTruckTask(engine, truck, gate, forklift).start(() -> {
                forkliftReleaser.run();
                gate.unparkTruck(truck);
                onComplete.run();
            })
        );
    }

}