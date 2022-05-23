package com.example.firstdraftspringfinalproject.models;

public class Project {

    private String projectId;
    private String projectName;

    // Other fields we may want in the future:
    // hoursQuoted, hoursBilled, contactInfo, etc.
    // number of shipments upcoming
    //shipment goal dates
    //actual shipment dates

    public Project (String projectId, String projectName){
        this.projectId = projectId;
        this.projectName = projectName;
    }

    // GETTERS & SETTERS
    public String getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Override
    public String toString() {
        return projectName;
    }
}
