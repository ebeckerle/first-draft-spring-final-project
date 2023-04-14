package com.example.firstdraftspringfinalproject.controllers.supervisorportal;

import com.example.firstdraftspringfinalproject.data.EmployeeRepository;
import com.example.firstdraftspringfinalproject.data.ProjectRepository;
import com.example.firstdraftspringfinalproject.data.TimesheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;

@Controller
@RequestMapping("supervisor")
public class SupervisorPortalController {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TimesheetRepository timesheetRepository;

    @GetMapping
    public String displayHomePage(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        Integer employeeId = (Integer) session.getAttribute("user");
        if (employeeRepository.findById(employeeId).isPresent()){
            model.addAttribute("employeeName", employeeRepository.findById(employeeId).get().getFirstName());
        }

        if (!timesheetRepository.findBySupervisorApprovalAndCompletionStatus(false, true).isEmpty()){
            model.addAttribute("timesheetsForApproval", timesheetRepository.findBySupervisorApprovalAndCompletionStatus(false, true).size());
        }

        LocalDate currentDate = LocalDate.now();
        String today = currentDate.getDayOfWeek()+", "+currentDate.getMonth()+"/"+currentDate.getDayOfMonth()+"/"+currentDate.getYear();
        model.addAttribute("today", today);
        model.addAttribute("projects", projectRepository.findAll());

        model.addAttribute("title", "Supervisor Portal");

        return "supervisor/home";
    }

}
