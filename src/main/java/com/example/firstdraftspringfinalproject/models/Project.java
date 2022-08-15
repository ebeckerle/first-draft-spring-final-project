package com.example.firstdraftspringfinalproject.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
public class Project {

    @Id
    @GeneratedValue
    private Integer projectId;

    @NotBlank(message = "Please provide 3 to 5 characters that best abbreviates the project's name.")
    @Size(min=3, max=5, message = "Please provide 3 to 5 characters that best abbreviates the project's name.")
    private String projectCode;

    @NotBlank(message = "Please provide a name for the project")
    @Size(max=100, message = "Please provide a name for the project in less than 100 characters")
    private String projectName;

    // Other fields we may want in the future:
    // hoursQuoted, hoursBilled, contactInfo, etc.
    // number of shipments upcoming
    //shipment goal dates
    //actual shipment dates

    public Project (String projectCode, String projectName){
        this.projectCode = projectCode;
        this.projectName = projectName;
    }

    public Project () {}

    // GETTERS & SETTERS
    public Integer getProjectId() {
        return projectId;
    }

    public String getProjectCode() {return projectCode;}

    public String getProjectName() {
        return projectName;
    }

    public void setProjectCode(String projectCode) { this.projectCode = projectCode; }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }


    @Override
    public String toString() {
        return projectName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return projectId.equals(project.projectId) && projectName.equals(project.projectName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, projectName);
    }
}
