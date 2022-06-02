package com.example.firstdraftspringfinalproject;

import com.example.firstdraftspringfinalproject.data.ProjectData;
import com.example.firstdraftspringfinalproject.models.Project;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.testng.AssertJUnit.assertEquals;


public class ProjectDataTests {

    @Test
    public void testFindProjectByName(){
        ArrayList<Project> projects = new ArrayList<>();
        Project pIasc = new Project("IASC", "Iowa State Capitol");
        Project pNdc = new Project("NDC", "Notre Dame Cathedral");
        Project pNam = new Project("NAM", "Nelson Atkins Museum");
        Project pKcus = new Project("KCUS", "Kansas City Union Station");
        projects.add(pIasc);
        projects.add(pNdc);
        projects.add(pNam);
        projects.add(pKcus);

        Project expected = ProjectData.findProjectByName("Notre Dame Cathedral");

        assertEquals(pNdc, expected);
    }
}
