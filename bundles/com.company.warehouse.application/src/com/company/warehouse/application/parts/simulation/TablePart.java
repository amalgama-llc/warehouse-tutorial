package com.company.warehouse.application.parts.simulation;

import java.util.Collections;

import jakarta.annotation.PostConstruct;

import org.eclipse.swt.widgets.Composite;

import com.amalgamasimulation.desktop.ui.views.TableView;


public class TablePart {

	@PostConstruct
	public void createComposite(Composite parent) {
		TableView<Object> tableView = new TableView<>(parent, Collections.emptyList(), false, true);
		tableView.addColumn("ID", obj -> "");
		tableView.addColumn("Name", obj -> "");
		tableView.addColumn("Description", obj -> "");
	}
}

