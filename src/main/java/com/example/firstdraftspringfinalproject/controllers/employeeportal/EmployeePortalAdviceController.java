package com.example.firstdraftspringfinalproject.controllers.employeeportal;

import com.example.firstdraftspringfinalproject.data.ProjectRepository;
import com.example.firstdraftspringfinalproject.data.TimesheetRepository;
import com.example.firstdraftspringfinalproject.data.WorkTypeRepository;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets.Timesheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;

@ControllerAdvice("com.example.firstdraftspringfinalproject.controllers.employeeportal")
public class EmployeePortalAdviceController {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private WorkTypeRepository workTypeRepository;

    @Autowired
    private TimesheetRepository timesheetRepository;


    @ModelAttribute("currentTimesheet")
    public void getCurrentTimesheet(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        Integer employeeId = (Integer) session.getAttribute("user");

        ArrayList<Timesheet> timesheets = (ArrayList<Timesheet>) timesheetRepository.findByEmployeeEmployeeIdAndCompletionStatusAndSupervisorApproval(employeeId, false, false);
        Timesheet currentTimesheet = timesheets.get(0);
        model.addAttribute("currentTimesheet", currentTimesheet);
    }

    @ModelAttribute("todaysDate")
    public void getTodaysDate(Model model){
        LocalDate currentDate = LocalDate.now();
        String today = currentDate.getDayOfWeek()+", "+currentDate.getMonth()+"/"+currentDate.getDayOfMonth()+"/"+currentDate.getYear();
        model.addAttribute("today", today);
    }

//    @ModelAttribute("startDate")
//    public void getThisTimesheetsStartDate(@RequestParam Integer employeeId, Model model){
//
//    }

    @ModelAttribute("projects")
            public void getAllProjects(Model model){
        model.addAttribute("projects", projectRepository.findAll());
    }

    @ModelAttribute("workTypes")
    public void getAllWorkTypes(Model model){
        model.addAttribute("workTypes", workTypeRepository.findAll());
    }


}
