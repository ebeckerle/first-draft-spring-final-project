package com.example.firstdraftspringfinalproject.controllers.employeeportal;

import com.example.firstdraftspringfinalproject.data.EmployeeRepository;
import com.example.firstdraftspringfinalproject.data.TimesheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("employee/timesheetrecords")
public class TimesheetRecordsController {

    @Autowired
    private TimesheetRepository timesheetRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping
    public String displayTimesheetRecords(HttpServletRequest request, Model model){

        HttpSession session = request.getSession();
        Integer employeeId = (Integer) session.getAttribute("user");

        if(employeeRepository.findById(employeeId).isPresent()){
            model.addAttribute("timesheets", timesheetRepository.findByEmployeeId(employeeId));
            model.addAttribute("employeeFirstName", employeeRepository.findById(employeeId).get().getFirstName());
        }

        model.addAttribute("title", "Your Timesheets");

        return "employee/timesheetrecords";
    }

    @GetMapping(value="/detail")
    public String displayTimesheetDetail(@RequestParam Integer timesheetId, Model model){
        timesheetRepository.findById(timesheetId).ifPresent(timesheet -> model.addAttribute("timesheet", timesheet));
        model.addAttribute("title", timesheetRepository.findById(timesheetId).get().getEmployee().getFirstName()+"'s Timesheet");
        return "employee/viewtimesheet";
    }
}
