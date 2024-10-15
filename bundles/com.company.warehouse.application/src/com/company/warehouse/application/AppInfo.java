package com.company.warehouse.application;

import org.eclipse.swt.graphics.Image;
import com.amalgamasimulation.desktop.utils.IAppInfo;
import com.amalgamasimulation.desktop.ui.editor.utils.IconsMapping;

public class AppInfo extends IAppInfo {
	
	private IconsMapping iconsMapping;
	
	public AppInfo(IconsMapping iconsMapping) {
		this.iconsMapping = iconsMapping;
	}

	@Override
	public String getProductID() {
		return "Warehouse";
	}
	
	@Override
	public String getFullProductName() {
		return "Warehouse";
	}
	
	@Override
	public String getAppVendorSiteUrl() {
		return "www.amalgamasimulation.com";
	}
	
	@Override
	public String getAppVendorEmail() {
		return "info@amalgamasimulation.com";
	}
	
	@Override
	public String getAppVendorName() {
		return "Amalgama LLC";
	}
	
	@Override
	public Image getApplicationIcon() {
		return iconsMapping.getImage("/icons/logo_64.png");
	}
}

