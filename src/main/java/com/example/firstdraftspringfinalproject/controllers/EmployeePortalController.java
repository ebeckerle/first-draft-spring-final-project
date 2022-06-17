package com.example.firstdraftspringfinalproject.controllers;

import com.example.firstdraftspringfinalproject.data.EmployeeData;
import com.example.firstdraftspringfinalproject.data.EmployeeRepository;
import com.example.firstdraftspringfinalproject.data.ProjectData;
import com.example.firstdraftspringfinalproject.data.WorkTypeData;
import com.example.firstdraftspringfinalproject.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Optional;

@Controller
@RequestMapping("employee")
public class EmployeePortalController {

    @Autowired
    private EmployeeRepository employeeRepository;

    //lives at /employee, renders employee>home.html
    @GetMapping
    public String displayEmployeeWelcome(@ModelAttribute Employee loggedInEmployee, Model model){

        //TODO - Delete? -some hardcoded stuff to delete when model binding is complete or MySQL comes along??
        Employee employee = employeeRepository.findById(7).get();

        String employeeFirstName = employee.getFirstName();
        LocalDate todaysDate = LocalDate.now();
        Integer employeeId = employee.getEmployeeId();

        model.addAttribute("title", "Home");
        model.addAttribute("employeeName", employeeFirstName);
        model.addAttribute("todaysDate", todaysDate);
        model.addAttribute("employeeId", employeeId);
        model.addAttribute("completionStatus", employee.getCurrentTimesheetCompletionStatus());

        System.out.println("display Employee Welcome controller");

        return "employee/home";
    }

    //lives at /employee, but renders employee>timesheettrial
    @PostMapping
    public String createNewTimesheet(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate todaysDate, @RequestParam Integer employeeId, Model model){
        //create a new timesheet object based on the employee (figured from employeeId)
        Timesheet newTimesheet = new Timesheet(employeeRepository.findById(employeeId).get());
        //set the new timesheet's start date based on todaysDate using setDates() method
        GregorianCalendar startDateGC = newTimesheet.figureStartDateBasedOnTodaysDate(todaysDate);
        newTimesheet.setDates(startDateGC);
        //set the new timesheet completion status as false
        newTimesheet.setCompletionStatus(false);
        //set the employee's current timesheet completion status as false
        employeeRepository.findById(employeeId).get().setCurrentTimesheetCompletionStatus(false);
        //add that new timesheet object to the timesheets arraylist of the appropriate employee object
        employeeRepository.findById(employeeId).get().getTimesheets().add(newTimesheet);
        // ??? set employee's currentTimesheetCompletionStatus as false?

        //display the Dates for this Timesheet
        String startDate = newTimesheet.formatDates(newTimesheet.getStartDate());
        String dueDate = newTimesheet.formatDates(newTimesheet.getDueDate());
        String payDay = newTimesheet.formatDates(newTimesheet.getPayDay());
        LocalDate currentDate = LocalDate.now();
        String today = currentDate.getDayOfWeek()+", "+currentDate.getMonth()+"/"+currentDate.getDayOfMonth()+"/"+currentDate.getYear();
        model.addAttribute("today", today);
        model.addAttribute("startDate", startDate);
        model.addAttribute("dueDate", dueDate);
        model.addAttribute("payDay", payDay);

//        //ENUM Effort - still an issue with it all being uppercase, maybe I need to write a method that
//        // using getDisplayName(TextStyle.FULL, Locale.US) - will put them in format i want (you can't do in the view it seems)
////        model.addAttribute("daysOfWeek", DayOfWeek.values());
//        model.addAttribute("daysOfWeek", DayOfWeek.values());
        //revert back to Days of week in an Array List, don't use enum type
        ArrayList<String> daysOfWeek = new ArrayList<>();
        String monday = "MONDAY";
        String tuesday = "TUESDAY";
        String wednesday = "WEDNESDAY";
        String thursday = "THURSDAY";
        String friday = "FRIDAY";
        String saturday = "SATURDAY";
        daysOfWeek.add(monday);
        daysOfWeek.add(tuesday);
        daysOfWeek.add(wednesday);
        daysOfWeek.add(thursday);
        daysOfWeek.add(friday);
        daysOfWeek.add(saturday);
        model.addAttribute("daysOfWeek", daysOfWeek);

        model.addAttribute("title", "Timesheet");

        //will delete this soon when I better figure out the employee sign in / authentication process, for now I think
        // i need to hardcode it and pass it thtrough as a model attribute so I can grab it as a request parameter
        // in the Timesheet Controller
        model.addAttribute("employeeId", 1);


        return "employee/timesheet";
    }




//    //controller for when the timesheet is complete and the employee submits it / posts it to the server
//    @PostMapping("timesheet")
//    public String submitCompleteTimesheet(Model model){
//        //set the employee's currentTimesheetCompletionStatus to true
//        //set the timesheet's completionStatus to True
//        //return the user to their home page, but I would like to leave a little message up there that's like:
//        // "Timesheet has been Submitted"
//        return "redirect:";
//    }


}
