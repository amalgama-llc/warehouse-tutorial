package com.company.warehouse.testsapplication;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import com.amalgamasimulation.desktop.ui.editor.utils.IconsMapping;
public class Activator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		context.registerService(IconsMapping.class.getName(), new IconsMapping(), null);
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}

