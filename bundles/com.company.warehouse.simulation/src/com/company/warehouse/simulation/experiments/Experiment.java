package com.company.warehouse.simulation.experiments;

import java.util.function.Supplier;

import com.amalgamasimulation.utils.Utils;
import com.company.warehouse.simulation.Model;

public abstract class Experiment {
	
	private Model model;
	
	public abstract Model createModel();

	public void stop() {
		if (model != null) {
			model.engine().stop();
		}
	}
	
	public double getCompletedFraction() {
		if (model == null) {
			return 0;
		}
		return Utils.xidz(model.engine().time(), model.getEndTime(), 1.0);
	}
	
	public Model getModel() {
		return model;
	}
	
	public void run() {
		run(() -> false);
	}
	
	public void run(Supplier<Boolean> stopHandler) {
		this.model = createModel();
		model.engine().scheduleRelative(model.engine().day(), () -> stopEngineIfRequested(stopHandler));
		model.engine().setFastMode(true);
		model.engine().run(true);
	}
	
	private void stopEngineIfRequested(Supplier<Boolean> stopHandler) {
		if (stopHandler.get()) {
			model.engine().stop();
		} else {
			model.engine().scheduleRelative(model.engine().day(), () -> stopEngineIfRequested(stopHandler));
		}
	}
}

