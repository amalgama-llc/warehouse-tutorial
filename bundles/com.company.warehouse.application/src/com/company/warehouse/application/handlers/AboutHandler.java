package com.company.warehouse.application.handlers;

import jakarta.inject.Inject;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.widgets.Shell;

import com.amalgamasimulation.desktop.ui.dialogs.AboutDialog;
import com.amalgamasimulation.desktop.utils.IAppInfo;

public class AboutHandler {

	@Inject
	private IAppInfo appInfo;
	
	@CanExecute
	private boolean canExecute() {
		return true;
	}

	@Execute
	public void execute(Shell shell) {
		new AboutDialog(shell, appInfo).open();
	}
}

