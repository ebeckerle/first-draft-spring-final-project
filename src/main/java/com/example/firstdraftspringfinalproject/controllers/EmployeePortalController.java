package com.example.firstdraftspringfinalproject.controllers;

import com.example.firstdraftspringfinalproject.data.EmployeeData;
import com.example.firstdraftspringfinalproject.data.ProjectData;
import com.example.firstdraftspringfinalproject.data.WorkTypeData;
import com.example.firstdraftspringfinalproject.models.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Controller
@RequestMapping("employee")
public class EmployeePortalController {

    //practice - move code, not right here.
    GregorianCalendar startDate = new GregorianCalendar(2022, Calendar.MAY, 16);
    Employee employee = EmployeeData.getEmployeeById(1);
    Timesheet currentTimesheet = new Timesheet(employee, startDate);


    //lives at /employee, renders employee>home.html
    @GetMapping
    public String displayEmployeeWelcome(@ModelAttribute Employee loggedInEmployee, Model model){

        //TODO - Delete? -some hardcoded stuff to delete when model binding is complete or MySQL comes along??
        Employee employee = EmployeeData.getEmployeeById(1);

        String employeeFirstName = employee.getFirstName();
        LocalDate todaysDate = LocalDate.now();
        Integer employeeId = employee.getEmployeeId();

        model.addAttribute("title", "Home");
        model.addAttribute("employeeName", employeeFirstName);
        model.addAttribute("todaysDate", todaysDate);
        model.addAttribute("employeeId", employeeId);
        model.addAttribute("completionStatus", employee.getCurrentTimesheetCompletionStatus());

        return "employee/home";
    }

    //lives at /employee, but renders employee>timesheettrial
    @PostMapping
    public String createNewTimesheet(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate todaysDate, @RequestParam Integer employeeId, Model model){
        //create a new timesheet object based on the employee (figured from employeeId)
        Timesheet newTimesheet = new Timesheet(EmployeeData.getEmployeeById(employeeId));
        //set the new timesheet's start date based on todaysDate using setDates() method
        GregorianCalendar startDateGC = newTimesheet.figureStartDateBasedOnTodaysDate(todaysDate);
        newTimesheet.setDates(startDateGC);
        //set the new timesheet completion status as false
        newTimesheet.setCompletionStatus(false);
        //set the employee's current timesheet completion status as false
        EmployeeData.getEmployeeById(employeeId).setCurrentTimesheetCompletionStatus(false);
        //add that new timesheet object to the timesheets arraylist of the appropriate employee object
        EmployeeData.getEmployeeById(employeeId).getTimesheets().add(newTimesheet);
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


        ArrayList<Project> projects = ProjectData.getAllProjects();
        ArrayList<WorkType> workTypes = WorkTypeData.getAllWorkTypes();

        model.addAttribute("title", "Timesheet");
        model.addAttribute("projects", projects);
        model.addAttribute("workTypes", workTypes);

        //will delete this soon when I better figure out the employee sign in / authentication process, for now I think
        // i need to hardcode it and pass it thtrough as a model attribute so I can grab it as a request parameter
        // in the Timesheet Controller
        model.addAttribute("employeeId", 1);

        //this is a quirk but want to try this:
        model.addAttribute("day", "don't display");

        return "employee/timesheettrial";
    }

    //the controller for when would hit the Edit current Timesheet Button - gonna go get the current timesheet from the server
//    @GetMapping("timesheet")
//    public String displayEmployeeCurrentTimesheetLineEntryForm(@RequestParam Integer employeeId, Model model){
//        //get employee by ID
//        //get the current timesheet from the logged in employee's ArrayList of timesheets
//        //create model attribute for the array list of log entries in the timesheet
//
//        //add model attributes to populate / display the first table, the line entry table, b
//        return "employee/timesheettrial";
//    }

//    @GetMapping("timesheet")
//    //@GetMapping("editTimesheet") -???
//    public String displayEmployeeTimesheetLineEntryForm(Model model) {
//
//        //Timesheet timesheet = getCurrentTimesheet(Employee employee){
//        currentTimesheet.setCompletionStatus(false);
//
//        ArrayList<Project> projects = ProjectData.getAllProjects();
//        ArrayList<WorkType> workTypes = WorkTypeData.getAllWorkTypes();
//
//        model.addAttribute("title", "Timesheet");
//        model.addAttribute("daysOfTheWeek", daysOfWeek);
//        model.addAttribute("projects", projects);
//        model.addAttribute("workTypes", workTypes);
//
//        currentTimesheet.setDates(startDate);
//        String startDate = currentTimesheet.formatDates(currentTimesheet.getStartDate());
//        String dueDate = currentTimesheet.formatDates(currentTimesheet.getDueDate());
//        String payDay = currentTimesheet.formatDates(currentTimesheet.getPayDay());
//        LocalDate currentDate = LocalDate.now();
//        String today = currentDate.getDayOfWeek()+", "+currentDate.getMonth()+"/"+currentDate.getDayOfMonth()+"/"+currentDate.getYear();
//        model.addAttribute("today", today);
//        model.addAttribute("startDate", startDate);
//        model.addAttribute("dueDate", dueDate);
//        model.addAttribute("payDay", payDay);
//
//        return "employee/timesheet";
//    }
//
//    @PostMapping("timesheet")
//    public String createLineEntryOnCurrentTimesheet(@RequestParam String project, @RequestParam String workType, @RequestParam String daysOfTheWeek, @RequestParam Integer hours, @ModelAttribute LineEntriesOnTimesheet newLineEntry, Model model){
//
//        ArrayList<Project> projects = ProjectData.getAllProjects();
//        model.addAttribute("projects", projects);
//        ArrayList<WorkType> workTypes = WorkTypeData.getAllWorkTypes();
//        model.addAttribute("workTypes", workTypes);
//
//        //1st find the current timesheet in EmployeeData/TimesheetData
//        LineEntriesOnTimesheet newEntry = new LineEntriesOnTimesheet(ProjectData.findProjectByName(project), WorkTypeData.findWorkTypeByCode(workType), daysOfTheWeek, hours);
//
//        ArrayList<LineEntriesOnTimesheet> logOfEntries = new ArrayList<>();
//        logOfEntries.add(newEntry);
//        currentTimesheet.setLineEntries(logOfEntries);
//        model.addAttribute("logOfEntries", logOfEntries);
//
//
//        model.addAttribute("daysOfTheWeek", daysOfTheWeek);
//        model.addAttribute("entry1Project", currentTimesheet.getLineEntries().get(0).getProject().toString());
//        model.addAttribute("entry1WorkType", currentTimesheet.getLineEntries().get(0).getWorkType().toStringWorkTypeCode());
//        model.addAttribute("entry1Hours", currentTimesheet.getLineEntries().get(0).getHours());
//        return "employee/timesheettrial";
//    }


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
