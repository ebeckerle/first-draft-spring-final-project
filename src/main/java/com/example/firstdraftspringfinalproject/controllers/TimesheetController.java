package com.example.firstdraftspringfinalproject.controllers;

import com.example.firstdraftspringfinalproject.data.EmployeeData;
import com.example.firstdraftspringfinalproject.data.ProjectData;
import com.example.firstdraftspringfinalproject.data.WorkTypeData;
import com.example.firstdraftspringfinalproject.models.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Controller
@RequestMapping("employee/timesheet")
public class TimesheetController {

    @GetMapping
    public String displayCurrentTimesheet(Model model){

        //2nd lets find the correct employee's arraylist of timesheets
        Employee employee = EmployeeData.getEmployeeById(1);//--> TODO - is this a requestparam?

        // find the current timesheet
        Timesheet currentTimesheet = EmployeeData.findCurrentTimesheetForEmployee(employee);
        //grab the ArrayList of Line Entries in current timesheet
        ArrayList<LineEntriesOnTimesheet> logOfEntries = currentTimesheet.getLineEntries();


        //display the Dates for this Timesheet
        String startDate = currentTimesheet.formatDates(currentTimesheet.getStartDate());
        String dueDate = currentTimesheet.formatDates(currentTimesheet.getDueDate());
        String payDay = currentTimesheet.formatDates(currentTimesheet.getPayDay());
        LocalDate currentDate = LocalDate.now();
        String today = currentDate.getDayOfWeek()+", "+currentDate.getMonth()+"/"+currentDate.getDayOfMonth()+"/"+currentDate.getYear();
        model.addAttribute("today", today);
        model.addAttribute("startDate", startDate);
        model.addAttribute("dueDate", dueDate);
        model.addAttribute("payDay", payDay);

        //DISPLAY the necessary attributes for the timesheet table (the second table)
        model.addAttribute("logOfEntries", logOfEntries);
        return "employee/timesheet2";
    }

    @GetMapping("/createlineentry")
    public String displayCreateLineEntryForm(Model model){

        //CONTINUE to display the model attributes for the line entry Table Form (the 1st one)
        ArrayList<Project> projects = ProjectData.getAllProjects();
        model.addAttribute("projects", projects);
        ArrayList<WorkType> workTypes = WorkTypeData.getAllWorkTypes();
        model.addAttribute("workTypes", workTypes);
        ArrayList<String> daysOfWeek1 = new ArrayList<>();
        String monday = "MONDAY";
        String tuesday = "TUESDAY";
        String wednesday = "WEDNESDAY";
        String thursday = "THURSDAY";
        String friday = "FRIDAY";
        String saturday = "SATURDAY";
        daysOfWeek1.add(monday);
        daysOfWeek1.add(tuesday);
        daysOfWeek1.add(wednesday);
        daysOfWeek1.add(thursday);
        daysOfWeek1.add(friday);
        daysOfWeek1.add(saturday);
        model.addAttribute("daysOfWeek", daysOfWeek1);
        //display the Dates for this Timesheet
//        String startDate = currentTimesheet.formatDates(currentTimesheet.getStartDate());
//        String dueDate = currentTimesheet.formatDates(currentTimesheet.getDueDate());
//        String payDay = currentTimesheet.formatDates(currentTimesheet.getPayDay());
        LocalDate currentDate = LocalDate.now();
        String today = currentDate.getDayOfWeek()+", "+currentDate.getMonth()+"/"+currentDate.getDayOfMonth()+"/"+currentDate.getYear();
        model.addAttribute("today", today);
//        model.addAttribute("startDate", startDate);
//        model.addAttribute("dueDate", dueDate);
//        model.addAttribute("payDay", payDay);

        //not sure what to do with this
        model.addAttribute("employeeId", 1);
        return "employee/createlineentry";
    }

    @PostMapping("createlineentry")
    public String processCreateLineEntryForm(@RequestParam Integer employeeId, @RequestParam String project, @RequestParam String workType, @RequestParam(required = false) String daysOfWeek, @RequestParam Integer hours, Model model){

        //1st lets create the line entry object
        LineEntriesOnTimesheet newEntry = new LineEntriesOnTimesheet(ProjectData.findProjectByName(project), WorkTypeData.findWorkTypeByCode(workType), daysOfWeek, hours);

        //2nd lets find the correct employee's arraylist of timesheets
        Employee employee = EmployeeData.getEmployeeById(employeeId);

        //3rd lets find the current timesheet
        Timesheet currentTimesheet = EmployeeData.findCurrentTimesheetForEmployee(employee);

        //4th update the current timesheet by adding this line entry
        ArrayList<LineEntriesOnTimesheet> logOfEntries = currentTimesheet.getLineEntries();

        logOfEntries.add(newEntry);

        return "redirect:";
    }

//    @PostMapping
//    public String createLineEntryOnCurrentTimesheet(@RequestParam Integer employeeId, @RequestParam String project, @RequestParam String workType, @RequestParam(required = false) String daysOfWeek, @RequestParam Integer hours, Model model){
//
////        //String Request Parameter converted to ENUM inorder to go into Line Entry constructor:
////        // create a reference variable for the DayOfWeek enum, which we have passed here as a string,
////        // but we want to convert back to an enum to put in our line entry constructor
////        DayOfWeek day2 = DayOfWeek.valueOf(day);
////        //update the current timesheet with the saved new line entry
////        //1st lets create the line entry object
////        LineEntriesOnTimesheet newEntry = new LineEntriesOnTimesheet(ProjectData.findProjectByName(project), WorkTypeData.findWorkTypeByCode(workType), day2, hours);
//
//        //DayOfTheWeek Enum as the Request Parameter type for day
//        LineEntriesOnTimesheet newEntry = new LineEntriesOnTimesheet(ProjectData.findProjectByName(project), WorkTypeData.findWorkTypeByCode(workType), daysOfWeek, hours);
//
//        //2nd lets find the correct employee's arraylist of timesheets
//        Employee employee = EmployeeData.getEmployeeById(employeeId);
//        ArrayList<Timesheet> timesheets = employee.getTimesheets();
//        //3rd lets find the current timesheet
//            // write a method in Employee class called find current timesheet?  or write in EmployeeData Class?
//        Timesheet currentTimesheet = EmployeeData.findCurrentTimesheetForEmployee(employee);
//        //4th update the current timesheet by adding this line entry
//        ArrayList<LineEntriesOnTimesheet> logOfEntries = currentTimesheet.getLineEntries();
//        logOfEntries.add(newEntry);
//
//        //CONTINUE to display the model attributes for the line entry Table Form (the 1st one)
//        ArrayList<Project> projects = ProjectData.getAllProjects();
//        model.addAttribute("projects", projects);
//        ArrayList<WorkType> workTypes = WorkTypeData.getAllWorkTypes();
//        model.addAttribute("workTypes", workTypes);
//        ArrayList<String> daysOfWeek1 = new ArrayList<>();
//        String monday = "MONDAY";
//        String tuesday = "TUESDAY";
//        String wednesday = "WEDNESDAY";
//        String thursday = "THURSDAY";
//        String friday = "FRIDAY";
//        String saturday = "SATURDAY";
//        daysOfWeek1.add(monday);
//        daysOfWeek1.add(tuesday);
//        daysOfWeek1.add(wednesday);
//        daysOfWeek1.add(thursday);
//        daysOfWeek1.add(friday);
//        daysOfWeek1.add(saturday);
//        model.addAttribute("daysOfWeek", daysOfWeek1);
//        //display the Dates for this Timesheet
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
//        //DISPLAY the necessary attributes for the timesheet table (the second table)
//        model.addAttribute("logOfEntries", logOfEntries);
//
////        String dayToString  = day2.name();
//
//        model.addAttribute("day", daysOfWeek);
//        model.addAttribute("entry1Project", currentTimesheet.getLineEntries().get(0).getProject().toString());
//        model.addAttribute("entry1WorkType", currentTimesheet.getLineEntries().get(0).getWorkType().toStringWorkTypeCode());
//        model.addAttribute("entry1Hours", currentTimesheet.getLineEntries().get(0).getHours());
//        return "employee/timesheettrial";
//    }

}
