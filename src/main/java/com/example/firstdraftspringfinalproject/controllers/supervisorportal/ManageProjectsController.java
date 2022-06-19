package com.example.firstdraftspringfinalproject.controllers.supervisorportal;

import com.example.firstdraftspringfinalproject.data.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("supervisor/manageprojects")
public class ManageProjectsController {

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping
    public String displayManageProjectsHome(Model model){
        model.addAttribute("projects", projectRepository.findAll());
        return "supervisor/manageprojects";
    }

}
