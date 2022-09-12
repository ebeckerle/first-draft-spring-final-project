package com.example.firstdraftspringfinalproject.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class ProjectWorkTypeSet {

    @Id
    @GeneratedValue
    private Integer id;

    @OneToOne
    private Project project;

    @OneToOne
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectWorkTypeSet that = (ProjectWorkTypeSet) o;
        return project.equals(that.project) && workType.equals(that.workType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(project, workType);
    }
}
