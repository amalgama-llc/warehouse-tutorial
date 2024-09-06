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
import java.util.concurrent.atomic.AtomicInteger;
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
        if (gate.getDirection() == Direction.IN) {
        	// unload the truck and then unload the gate storage
            handleTruck(truck, gate, () -> handleGateArea(gate, onComplete));
        } else {
        	// load the gate storage and then load the truck
            handleGateArea(gate, () -> handleTruck(truck, gate, onComplete));
        }
    }

    private void handleTruck(Truck truck, Gate gate, Runnable onComplete) {
        gate.parkTruck(truck);
        final double parkedTime = engine.time();
        requestForklift((forklift, forkliftReleaser) ->
            new HandleTruckTask(engine, truck, gate, forklift).start(() -> {
                forkliftReleaser.run();
                gate.unparkTruck(truck);
                model.getTruckLoadingDuration(truck.getDirection()).accept(engine.time() - parkedTime);
                onComplete.run();
            })
        );
    }

    /**
     * (Un)loads gate storage.
     */
    private void handleGateArea(Gate gate, Runnable onComplete) {
        final var loading = gate.getDirection() == Direction.OUT;
        final var places = gate.getStorageArea()
            .getPlacesAvailableFor(loading)
            .toList();
        
        final var placeCount = new AtomicInteger(places.size());
        
        if (placeCount.get() == 0) {
        	onComplete.run();
        	return;
        }
        
        for (var p : places) {
            handleGatePlace(loading, p, () -> {
                if (placeCount.decrementAndGet() == 0) {
                    onComplete.run();
                }
            });
        }
    }
    
    /**
     * Gets corresponding place in the main storage.
     * Gets a forklift.
     * Moves a pallet.
     * Releases the forklift.
     */
    private void handleGatePlace(boolean loading, PalletPosition gatePlace, Runnable onComplete) {
        model.getMainStorage().reservePlace(loading, storagePlace ->
            requestForklift((f, releaser) ->
                new MovePalletTask(engine, f, gatePlace, storagePlace, loading).start(() -> {
                    releaser.run();
                    onComplete.run();
                })
            )
        );
    }

}