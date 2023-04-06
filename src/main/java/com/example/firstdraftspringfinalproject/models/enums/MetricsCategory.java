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

    public static MetricsCategory getMetricsCategoryEnumFromString(String metricsCategory){
        return switch (metricsCategory) {
            case "Employee" -> EMPLOYEE;
            case "Project" -> PROJECT;
            case "WorkType" -> WORKTYPE;
            case "PayRate" -> PAYRATE;
            default -> EMPLOYEE;
        };
    }
}
