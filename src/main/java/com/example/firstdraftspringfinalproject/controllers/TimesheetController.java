package com.example.firstdraftspringfinalproject.controllers;

import com.example.firstdraftspringfinalproject.data.EmployeeData;
import com.example.firstdraftspringfinalproject.data.ProjectData;
import com.example.firstdraftspringfinalproject.data.WorkTypeData;
import com.example.firstdraftspringfinalproject.models.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Controller
@RequestMapping("employee/timesheet")
public class TimesheetController {

    @PostMapping
    public String createLineEntryOnCurrentTimesheet(@RequestParam Integer employeeId, @RequestParam String project, @RequestParam String workType, @RequestParam String day, @RequestParam Integer hours, Model model){

        //create a reference variable for the DayOfWeek enum, which we have passed here as a string,
        // but we want to convert back to an enum to put in our line entry constructor
        DayOfWeek day2 = DayOfWeek.valueOf(day);
        //update the current timesheet with the saved new line entry
        //1st lets create the line entry object
        LineEntriesOnTimesheet newEntry = new LineEntriesOnTimesheet(ProjectData.findProjectByName(project), WorkTypeData.findWorkTypeByCode(workType), day2, hours);
        //2nd lets find the correct employee's arraylist of timesheets
        Employee employee = EmployeeData.getEmployeeById(employeeId);
        ArrayList<Timesheet> timesheets = employee.getTimesheets();
        //3rd lets find the current timesheet
            // write a method in Employee class called find current timesheet?  or write in EmployeeData Class?
        Timesheet currentTimesheet = EmployeeData.findCurrentTimesheetForEmployee(employee);
        //4th update the current timesheet by adding this line entry
        ArrayList<LineEntriesOnTimesheet> logOfEntries = currentTimesheet.getLineEntries();
        logOfEntries.add(newEntry);

        //CONTINUE to display the model attributes for the line entry Table Form (the 1st one)
        ArrayList<Project> projects = ProjectData.getAllProjects();
        model.addAttribute("projects", projects);
        ArrayList<WorkType> workTypes = WorkTypeData.getAllWorkTypes();
        model.addAttribute("workTypes", workTypes);

        //DISPLAY the necessary attributes for the timesheet table (the second table)
        model.addAttribute("logOfEntries", logOfEntries);

//        String dayToString  = day.name();

        model.addAttribute("day", day);
        model.addAttribute("entry1Project", currentTimesheet.getLineEntries().get(0).getProject().toString());
        model.addAttribute("entry1WorkType", currentTimesheet.getLineEntries().get(0).getWorkType().toStringWorkTypeCode());
        model.addAttribute("entry1Hours", currentTimesheet.getLineEntries().get(0).getHours());
        return "employee/timesheettrial";
    }

}
