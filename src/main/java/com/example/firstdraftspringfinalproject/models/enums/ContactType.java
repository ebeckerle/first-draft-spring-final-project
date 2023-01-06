package com.example.firstdraftspringfinalproject.models.enums;

public enum ContactType {


    GENERAL("General"),
    CARRIER("Carrier");
    EMPLOYEE("Employee");
    EMERGENCYCONTACT("Emergency Contact");

    private final String displayName;

    ContactType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
