package com.example.firstdraftspringfinalproject.models;

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
}
