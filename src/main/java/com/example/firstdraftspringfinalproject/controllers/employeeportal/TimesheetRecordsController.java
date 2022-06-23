package com.example.firstdraftspringfinalproject.controllers.employeeportal;

import com.example.firstdraftspringfinalproject.data.TimesheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("employee/timesheetrecords")
public class TimesheetRecordsController {

    @Autowired
    private TimesheetRepository timesheetRepository;

    @GetMapping
    public String displayTimesheetRecords(Model model){
        model.addAttribute("timesheets", timesheetRepository.findByEmployeeEmployeeId(7));
//        model.addAttribute("timesheets", timesheetRepository.findByEmployeeEmployeeIdAndCompletionStatus(7,false));
        return "employee/timesheetrecords";
    }
}
