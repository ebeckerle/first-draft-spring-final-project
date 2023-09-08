package com.example.firstdraftspringfinalproject.unittests;

import com.example.firstdraftspringfinalproject.models.domainentityclasses.Project;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.WorkType;

import java.util.ArrayList;
import java.util.List;

public class TestObjectsBuilder {

    public static TestObjects createTestObjects(){
        Project testProject1 = new Project("IASC", "Iowa State Capitol");
        Project testProject2 = new Project("NAM", "Nelson Atkins Museum");
        List<Project> testProjects = new ArrayList<>();;
        testProjects.add(testProject1);
        testProjects.add(testProject2);

        WorkType testWorkType101 = new WorkType(101, "Inventory");
        WorkType testWorkType102 = new WorkType(102, "Cut and Process Rough Parts");
        List<WorkType> testWorkTypes = new ArrayList<>();
        testWorkTypes.add(testWorkType101);
        testWorkTypes.add(testWorkType102);

        TestObjects testObjects = new TestObjects(testProjects, testWorkTypes);
        return testObjects;
    }
}
