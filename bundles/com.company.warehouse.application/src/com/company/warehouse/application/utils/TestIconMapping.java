package com.company.warehouse.application.utils;

import java.io.InputStream;

import com.amalgamasimulation.desktop.ui.editor.utils.IconsMapping;

public class TestIconMapping extends IconsMapping{

	@Override
	public InputStream getInputStream(String filePath) {
		return getInternalInputStream(TestIconMapping.class, filePath);
	}

}

