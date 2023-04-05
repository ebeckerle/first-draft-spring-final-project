package com.example.firstdraftspringfinalproject.controllers.supervisorportal;

import com.example.firstdraftspringfinalproject.data.ProjectRepository;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("supervisor/manageprojects")
public class ManageProjectsController {

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping
    public String displayManageProjectsHome(Model model){
        model.addAttribute("projects", projectRepository.findAll());
        model.addAttribute("title", "Manage Projects");
        return "supervisor/manageprojects";
    }

    @GetMapping("newproject")
    public String displayAddNewProjectForm(Model model){
        model.addAttribute("title", "Add a Project");
        model.addAttribute(new Project());
        return "supervisor/newproject";
    }

    @PostMapping("newproject")
    public String processAddNewProjectForm(@ModelAttribute @Valid Project project, Errors errors, Model model){
        if (errors.hasErrors()){
            model.addAttribute("title", "Add a Project");
            return "supervisor/newproject";
        }
        projectRepository.save(project);
        model.addAttribute("projects", projectRepository.findAll());
        model.addAttribute("title", "Manage Projects");
        return "redirect:";
    }

    @GetMapping("projectdetails")
    public String displayProjectDetails(Model model, @RequestParam Integer projectId){
        model.addAttribute("project", projectRepository.findById(projectId).get());
        model.addAttribute("title", "Project Details");
        return "supervisor/projectdetails";
    }


}
