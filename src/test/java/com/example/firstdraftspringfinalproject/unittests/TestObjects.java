package com.example.firstdraftspringfinalproject.unittests;

import com.example.firstdraftspringfinalproject.models.domainentityclasses.Project;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.WorkType;

import java.util.List;

public class TestObjects {


    List<Project> testProjects;
    List<WorkType> testWorkTypes;

    TestObjects (List<Project> testProjects, List<WorkType> testWorkTypes){
        this.testProjects = testProjects;
        this.testWorkTypes = testWorkTypes;
    }

    public List<Project> getTestProjects() {
        return testProjects;
    }

    public List<WorkType> getTestWorkTypes() {
        return testWorkTypes;
    }
}
