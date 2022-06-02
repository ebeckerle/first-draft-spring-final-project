package com.example.firstdraftspringfinalproject.data;

import com.example.firstdraftspringfinalproject.models.Project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class ProjectData {

    private static final ArrayList<Project> projects = new ArrayList<>(Arrays.asList(
            new Project("IASC", "Iowa State Capital"),
        new Project("NDC", "Notre Dame Cathedral"),
        new Project("NAM", "Nelson Atkins Museum"),
        new Project("KCUS", "Kansas City Union Station")
    ));

    public static ArrayList<Project> getAllProjects(){
        return projects;
    }

    public static Project findProjectByName(String name){
        Project projectReturned = new Project("DEFAULT", "default name");
        for (Project project:
             projects) {
              if (project.getProjectName().equals(name)){
                projectReturned = project;
            }
        }
        return projectReturned;
    }

}
