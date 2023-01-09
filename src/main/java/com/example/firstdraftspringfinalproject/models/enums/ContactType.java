package com.example.firstdraftspringfinalproject.models.enums;

public enum ContactType {


    GENERAL("General"),
    CARRIER("Carrier"),
    COLLEAGUE("Colleague"),
    PROJECTCONTACT("Project Contact"),
    EMERGENCYCONTACT("Emergency Contact"),
    EMPLOYEE("Employee");

    private final String displayName;

    ContactType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
