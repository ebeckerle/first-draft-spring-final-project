package com.example.firstdraftspringfinalproject.models.enums;

public enum MetricsCategory {


    EMPLOYEE("Employee"),
    PROJECT("Project"),
    WORKTYPE("WorkType"),
    PAYRATE("PayRate");


    private final String displayName;

    MetricsCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public MetricsCategory getMetricsCategoryEnumFromString(String metricsCategory){
        if(metricsCategory.equals("Employee")){
            return EMPLOYEE;
        } else if (metricsCategory.equals("Project")) {
            return PROJECT;
        }
    }
}
