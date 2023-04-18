package com.example.firstdraftspringfinalproject.controllers.employeeportal;

import com.example.firstdraftspringfinalproject.data.EmployeeRepository;
import com.example.firstdraftspringfinalproject.data.ProjectRepository;
import com.example.firstdraftspringfinalproject.data.TimesheetRepository;
import com.example.firstdraftspringfinalproject.data.WorkTypeRepository;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets.Timesheet;
import com.example.firstdraftspringfinalproject.models.enums.DaysOfWeek;
import com.example.firstdraftspringfinalproject.models.interfaces.TimesheetCalculateDates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;


@ControllerAdvice("com.example.firstdraftspringfinalproject.controllers.employeeportal")
public class EmployeePortalAdviceController {

    @Autowired
    private TimesheetRepository timesheetRepository;


    @ModelAttribute("currentTimesheet")
    public void getCurrentTimesheet(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Integer employeeId = (Integer) session.getAttribute("user");

        ArrayList<Timesheet> timesheets = (ArrayList<Timesheet>) timesheetRepository.findByEmployeeEmployeeIdAndCompletionStatusAndSupervisorApproval(employeeId, false, false);
        if(timesheets.size() != 1){
            System.out.println("There is no one current timesheet!");
        } else {
            Timesheet currentTimesheet = timesheets.get(0);
            model.addAttribute("currentTimesheet", currentTimesheet);
        }
    }

}
