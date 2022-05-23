package com.example.firstdraftspringfinalproject.controllers;

import com.example.firstdraftspringfinalproject.models.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("supervisor")
public class SupervisorPortalController {

    Employee practiceEmployee = new Employee("Fox", "Turner");

    @GetMapping
    public String displayHomePage(Model model){
        model.addAttribute("title", practiceEmployee.getFirstName()+"'s Portal");
        return "supervisor/home";
    }


}
