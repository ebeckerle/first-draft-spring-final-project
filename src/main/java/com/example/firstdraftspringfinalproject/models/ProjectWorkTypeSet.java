package com.example.firstdraftspringfinalproject.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class ProjectWorkTypeSet {

    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    @OneToOne
    private Project project;

    @OneToOne
    @NotNull
    private WorkType workType;

    public ProjectWorkTypeSet() {}

    public ProjectWorkTypeSet(Project project, WorkType workType){
        this.project = project;
        this.workType = workType;
    }

    public Integer getId() {
        return id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public WorkType getWorkType() {
        return workType;
    }

    public void setWorkType(WorkType workType) {
        this.workType = workType;
    }
}
