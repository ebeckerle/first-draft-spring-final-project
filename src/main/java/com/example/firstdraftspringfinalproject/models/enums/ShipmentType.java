package com.example.firstdraftspringfinalproject.models.enums;

public enum ShipmentType {


    INCOMING("INCOMING"),
    OUTGOING("OUTGOING");

    private final String displayName;

    ShipmentType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
