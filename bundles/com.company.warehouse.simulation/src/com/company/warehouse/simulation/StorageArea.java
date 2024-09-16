package com.company.warehouse.simulation;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class StorageArea {

    /**
     * All the pallet positions of this area.
     */
    private LinkedList<PalletPosition> places;

    /**
     * Two queues for requests of different kinds.
     * Former for empty postions.
     * Latter for busy ones.
     */
    private Map<Boolean, Queue<Consumer<PalletPosition>>> requests = Map.of(
            false, new LinkedList<Consumer<PalletPosition>>(),
            true, new LinkedList<Consumer<PalletPosition>>());

    public StorageArea(LinkedList<PalletPosition> places) {
        this.places = places;
        for (var p : places) {
            p.onBusySubscribe(false, this::placeBusyChanged);
            p.onBusySubscribe(true, this::placeBusyChanged);
        }
    }

    /**
     * Returns all non reserved positions that are available for loading (empty) or unloading (busy)
     * @param loading  are empty positions requested?
     * @return  a stream of pallet positions
     */
    public Stream<PalletPosition> getPlacesAvailableFor(boolean loading) {
        return places.stream()
            .filter(p -> p.isAvailableFor(loading) && !p.isReserved())
            .peek(p -> p.reserve())
            ;
    }

    /**
     * Requests a position of a specified busy state.
     * @param busy  state of requested position
     * @param request  callback that will receive requested position as soon as one become available
     */
    public void reservePlace(boolean busy, Consumer<PalletPosition> request) {
        final var iterator = busy ? places.iterator() : places.descendingIterator();

        while (iterator.hasNext()) {
            final var p = iterator.next();
            if (p.isAvailableFor(!busy) && !p.isReserved()) {
                reserveAndComplete(p, request);
                return;
            }
        }

        requests.get(busy).add(request);
    }

    /**
     * Is called upon any of position busy state change
     */
    private void placeBusyChanged(PalletPosition p) {
        final var request = requests.get(p.isAvailableFor(false)).poll();
        if (request != null) {
            reserveAndComplete(p, request);
        }
    }

    /**
     * Reserves the position and invokes the callback
     */
    private void reserveAndComplete(PalletPosition p, Consumer<PalletPosition> request) {
        p.reserve();
        request.accept(p);
    }

}