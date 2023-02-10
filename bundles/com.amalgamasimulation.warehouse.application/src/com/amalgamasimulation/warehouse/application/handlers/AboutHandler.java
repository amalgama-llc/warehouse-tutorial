package com.amalgamasimulation.warehouse.application.handlers;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.services.nls.Translation;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

import com.amalgamasimulation.warehouse.application.AppInfo;
import com.amalgamasimulation.warehouse.application.utils.MessageBoxFactory;
import com.amalgamasimulation.warehouse.common.localization.Messages;

public class AboutHandler {

	@Inject
	@Translation
	private Messages messages;

	@CanExecute
	private boolean canExecute() {
		return true;
	}

	@Execute
	public void execute(Shell shell) {
		MessageBoxFactory.createMessageBox(shell, SWT.ICON_INFORMATION | SWT.OK | SWT.APPLICATION_MODAL, "Info",
				String.format(messages.message_about, AppInfo.getProductID(), AppInfo.getVersionAsString()));
	}
}

