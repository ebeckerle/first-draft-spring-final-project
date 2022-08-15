package com.example.firstdraftspringfinalproject.models;

public enum MetricsCategory {

    PROJECT("Project"),
    EMPLOYEE("Employee"),
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
