package com.company.warehouse.application;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import com.amalgamasimulation.desktop.ui.editor.commands.CommandFactory;
import com.amalgamasimulation.desktop.ui.editor.utils.ICommandFactory;
import com.amalgamasimulation.desktop.utils.IAppInfo;
import com.amalgamasimulation.desktop.ui.editor.utils.IconsMapping;
import com.company.warehouse.application.utils.TestIconMapping;

public class Activator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		ICommandFactory commandFactory = new CommandFactory();
		context.registerService(ICommandFactory.class.getName(), commandFactory, null);
		IconsMapping iconsMapping = new TestIconMapping();
		context.registerService(IconsMapping.class.getName(), iconsMapping, null);
		context.registerService(IAppInfo.class.getName(), new AppInfo(iconsMapping), null);
	
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}

