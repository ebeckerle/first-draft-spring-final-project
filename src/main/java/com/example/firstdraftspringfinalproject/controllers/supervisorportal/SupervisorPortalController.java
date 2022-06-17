package com.example.firstdraftspringfinalproject.controllers.supervisorportal;

import com.example.firstdraftspringfinalproject.data.EmployeeRepository;
import com.example.firstdraftspringfinalproject.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
