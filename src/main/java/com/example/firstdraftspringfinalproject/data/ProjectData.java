package com.example.firstdraftspringfinalproject.data;

import com.example.firstdraftspringfinalproject.models.Project;

import java.util.ArrayList;
import java.util.Collection;

public class ProjectData {

    private static ArrayList<Project> projects = new ArrayList<>();

    //This will go away once we learn MySQL (??)
    public static ArrayList<Project> addHardcodedProjects() {
        Project pIasc = new Project("IASC", "Iowa State Capital");
        Project pNdc = new Project("NDC", "Notre Dame Cathedral");
        Project pNam = new Project("NAM", "Nelson Atkins Museum");
        Project pKcus = new Project("KCUS", "Kansas City Union Station");
        projects.add(pIasc);
        projects.add(pNdc);
        projects.add(pNam);
        projects.add(pKcus);
        return projects;
    }

    public static Project findProjectByName(String name){
        ArrayList<Project> projects = addHardcodedProjects();
        Project projectReturned = new Project("DEFAULT", "default name");
        for (Project project:
             projects) {
              if (project.getProjectName().equals(name)){
                projectReturned = project;
            }
        }
        return projectReturned;
    }

    public static ArrayList<Project> getAllProjects(){
        ArrayList<Project> projects = addHardcodedProjects();
        return projects;
    }




}
