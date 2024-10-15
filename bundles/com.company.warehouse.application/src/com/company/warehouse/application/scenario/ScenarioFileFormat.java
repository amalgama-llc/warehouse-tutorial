package com.company.warehouse.application.scenario;

public enum ScenarioFileFormat {
    EXCEL("xlsx");

    public final String fileDotExtension;
    public final String filePattern;
    
    private ScenarioFileFormat(String fileExtension) {
        this.fileDotExtension = "." + fileExtension;
        this.filePattern = "*." + fileExtension;
    }
}

