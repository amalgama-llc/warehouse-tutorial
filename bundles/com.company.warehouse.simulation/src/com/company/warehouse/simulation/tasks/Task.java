package com.company.warehouse.simulation.tasks;

import com.amalgamasimulation.engine.Engine;

public abstract class Task {

    protected final Engine engine;
    public Engine getEngine() {
        return engine;
    }
    /**
     * User provided callback to notify upon completion of a current task.
     */
    private Runnable onComplete;

    public Task(Engine engine) {
        this.engine = engine;
    }

    /**
     * Starts the task.
     * @param onComplete  callback to invoke on completion
     */
    public void start(Runnable onComplete) {
        this.onComplete = onComplete != null ? onComplete : () -> {};
        run();
    }

    /**
     * Override this method to implement a specific task.
     */
    protected abstract void run();

    /**
     * Call this method when task finishes.
     */
    protected void finish() {
        onComplete.run();
    }

}