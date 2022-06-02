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
//        ArrayList<LineEntry> logOfEntries = currentTimesheet.getLineEntries();


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
        model.addAttribute("logOfEntries", currentTimesheet.getLineEntries());

        System.out.println("Fox is the Best baby");
        System.out.println("display Current Timesheet controller");
        for (LineEntry lineEntry:
                currentTimesheet.getLineEntries()) {
            System.out.println("entry: "+lineEntry.getProject().getProjectName()+lineEntry.getWorkType().getWorkTypeId().toString());
        }

        //We need total hours worked on each individual day of the week and display them in the last row of the table
        model.addAttribute("mondayTotal", currentTimesheet.totalDayOfWeekHours("MONDAY"));
        model.addAttribute("tuesdayTotal", currentTimesheet.totalDayOfWeekHours("TUESDAY"));
        model.addAttribute("wednesdayTotal", currentTimesheet.totalDayOfWeekHours("WEDNESDAY"));
        model.addAttribute("thursdayTotal", currentTimesheet.totalDayOfWeekHours("THURSDAY"));
        model.addAttribute("fridayTotal", currentTimesheet.totalDayOfWeekHours("FRIDAY"));
        model.addAttribute("saturdayTotal", currentTimesheet.totalDayOfWeekHours("SATURDAY"));

        model.addAttribute("title", "Current Timesheet");

        return "employee/timesheet";
    }

    @GetMapping("/createlineentry")
    public String displayCreateLineEntryForm(Model model){

        //CONTINUE to display the model attributes for the line entry Table Form (the 1st one)
//        ArrayList<Project> projects = ProjectData.getAllProjects();
        model.addAttribute("projects", ProjectData.getAllProjects());
//        ArrayList<WorkType> workTypes = WorkTypeData.getAllWorkTypes();
        model.addAttribute("workTypes", WorkTypeData.getAllWorkTypes());
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

        model.addAttribute("title", "Add hours to your Timesheet");
        //not sure what to do with this
        model.addAttribute("employeeId", 1);

        System.out.println("Display Create Line Entry Form (Get Request)");
        return "employee/createlineentry";
    }

    @PostMapping("createlineentry")
    public String processCreateLineEntryForm(@RequestParam Integer employeeId, @RequestParam String project, @RequestParam String workType, @RequestParam(required = false) String daysOfWeek, @RequestParam Integer hours, Model model){

        //1ST lets find the correct employee's arraylist of timesheets
        Employee employee = EmployeeData.getEmployeeById(employeeId);

        //2nd lets find the current timesheet
        Timesheet currentTimesheet = EmployeeData.findCurrentTimesheetForEmployee(employee);

        //Create the new line entry object
        LineEntry newEntry = new LineEntry(ProjectData.findProjectByName(project), WorkTypeData.findWorkTypeByCode(workType), daysOfWeek, hours);

        //check if the lineEntry Project WorkType Combo already exists as a line entry, if so, update that line entry, if not, add a new line entry.

        currentTimesheet.checkAndAddALineEntry(newEntry, daysOfWeek, hours);
        newEntry.setTotalHoursInLineEntry();

        System.out.println("Fox is sleeping, this is in the process create line entry form");
        for (LineEntry lineEntry:
                currentTimesheet.getLineEntries()) {
            System.out.println("entry: "+lineEntry.getProject().getProjectName()+lineEntry.getWorkType().getWorkTypeId().toString());
        }

        return "redirect:";
    }


}
