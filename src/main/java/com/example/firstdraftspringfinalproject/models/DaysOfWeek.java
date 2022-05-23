package com.example.firstdraftspringfinalproject.models;

public enum DaysOfWeek {

    SUNDAY("Sunday"),
    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday");

    private final String displayName;

    DaysOfWeek (String displayName){
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public static DaysOfWeek getByName(String name){
        return DaysOfWeek.valueOf(name);
    }




}
