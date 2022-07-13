package com.example.firstdraftspringfinalproject.controllers.supervisorportal;

import com.example.firstdraftspringfinalproject.data.EmployeeRepository;
import com.example.firstdraftspringfinalproject.data.ProjectRepository;
import com.example.firstdraftspringfinalproject.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;

@Controller
@RequestMapping("supervisor")
public class SupervisorPortalController {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public String displayHomePage(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        Integer employeeId = (Integer) session.getAttribute("user");
        if (employeeRepository.findById(employeeId).isPresent()){
            model.addAttribute("title", employeeRepository.findById(employeeId).get().getFirstName()+"'s Portal");
            model.addAttribute("employeeName", employeeRepository.findById(employeeId).get().getFirstName());
        }

        LocalDate currentDate = LocalDate.now();
        String today = currentDate.getDayOfWeek()+", "+currentDate.getMonth()+"/"+currentDate.getDayOfMonth()+"/"+currentDate.getYear();
        model.addAttribute("today", today);
        model.addAttribute("projects", projectRepository.findAll());
        return "supervisor/home";
    }

}
