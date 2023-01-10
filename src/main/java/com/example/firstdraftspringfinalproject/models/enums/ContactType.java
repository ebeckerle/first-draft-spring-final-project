package com.example.firstdraftspringfinalproject.models.enums;

import java.util.ArrayList;

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

    public static ArrayList<ContactType> getList(){
        ArrayList<ContactType> list = new ArrayList<>();
        list.add(GENERAL);
        list.add(CARRIER);
        list.add(COLLEAGUE);
        list.add(PROJECTCONTACT);
        list.add(EMERGENCYCONTACT);
        list.add(EMPLOYEE);
        return list;
    }
}
