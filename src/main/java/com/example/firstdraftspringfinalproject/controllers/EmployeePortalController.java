package com.example.firstdraftspringfinalproject.controllers;

import com.example.firstdraftspringfinalproject.data.EmployeeData;
import com.example.firstdraftspringfinalproject.data.ProjectData;
import com.example.firstdraftspringfinalproject.data.WorkTypeData;
import com.example.firstdraftspringfinalproject.models.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

        //?
        model.addAttribute("completionStatus", employee.getCurrentTimesheetCompletionStatus());

        return "employee/home";
    }

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

        //display the line entry form--> TODO - get these days of the week to enum, clean up this code.
        ArrayList<Project> projects = ProjectData.getAllProjects();
        ArrayList<WorkType> workTypes = WorkTypeData.getAllWorkTypes();
        ArrayList<String> daysOfWeek = new ArrayList<>();
        String monday = "Monday";
        String tuesday = "Tuesday";
        String wednesday = "Wednesday";
        String thursday = "Thursday";
        String friday = "Friday";
        String saturday = "Saturday";
        daysOfWeek.add(monday);
        daysOfWeek.add(tuesday);
        daysOfWeek.add(wednesday);
        daysOfWeek.add(thursday);
        daysOfWeek.add(friday);
        daysOfWeek.add(saturday);
        model.addAttribute("title", "Timesheet");
        model.addAttribute("daysOfTheWeek", daysOfWeek);
        model.addAttribute("projects", projects);
        model.addAttribute("workTypes", workTypes);

        return "employee/timesheettrial";
    }

//    @GetMapping("timesheet")
//    //@GetMapping("editTimesheet") -???
//    public String displayEmployeeTimesheetLineEntryForm(Model model) {
//
//        //Timesheet timesheet = getCurrentTimesheet(Employee employee){
//        currentTimesheet.setCompletionStatus(false);
//
//        ArrayList<Project> projects = ProjectData.getAllProjects();
//        ArrayList<WorkType> workTypes = WorkTypeData.getAllWorkTypes();
//        ArrayList<String> daysOfWeek = new ArrayList<>();
//        String monday = "Monday";
//        String tuesday = "Tuesday";
//        String wednesday = "Wednesday";
//        String thursday = "Thursday";
//        String friday = "Friday";
//        String saturday = "Saturday";
//        daysOfWeek.add(monday);
//        daysOfWeek.add(tuesday);
//        daysOfWeek.add(wednesday);
//        daysOfWeek.add(thursday);
//        daysOfWeek.add(friday);
//        daysOfWeek.add(saturday);
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
//    public String createEntry(@RequestParam String project, @RequestParam String workType, @RequestParam String daysOfTheWeek, @RequestParam Integer hours, @ModelAttribute LineEntriesOnTimesheet newLineEntry, Model model){
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
//        return "employee/timesheet";
//    }


}
