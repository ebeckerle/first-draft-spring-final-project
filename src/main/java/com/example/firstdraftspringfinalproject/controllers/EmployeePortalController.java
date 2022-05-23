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
@RequestMapping("employee")
public class EmployeePortalController {

    //practice - move code, not right here.
    GregorianCalendar startDate = new GregorianCalendar(2022, Calendar.MAY, 16);
    Employee employee = EmployeeData.getEmployeeByUsername("ebeckerle");
    Timesheet currentTimesheet = new Timesheet(employee, startDate);



    @GetMapping
    public String displayEmployeeWelcome(Model model){

        GregorianCalendar startDate = new GregorianCalendar(2022, Calendar.MAY, 16);
        Employee employee = EmployeeData.getEmployeeByUsername("ebeckerle");
        String employeeFirstName = employee.getFirstName();
        Timesheet currentTimesheet = new Timesheet(employee, startDate);

        model.addAttribute("title", "Home");
        model.addAttribute("employeeName", employeeFirstName);
//        model.addAttribute("employeeName", employee.getFirstName());
        return "employee/home";
    }

    @GetMapping("timesheet")
    public String displayEmployeeTimesheetLineEntryForm(Model model) {

        //Timesheet timesheet = getCurrentTimesheet(Employee employee){
        currentTimesheet.setCompletionStatus(false);

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

        currentTimesheet.setDates(startDate);
        String startDate = currentTimesheet.formatDates(currentTimesheet.getStartDate());
        String dueDate = currentTimesheet.formatDates(currentTimesheet.getDueDate());
        String payDay = currentTimesheet.formatDates(currentTimesheet.getPayDay());
        LocalDate currentDate = LocalDate.now();
        String today = currentDate.getDayOfWeek()+", "+currentDate.getMonth()+"/"+currentDate.getDayOfMonth()+"/"+currentDate.getYear();
        model.addAttribute("today", today);
        model.addAttribute("startDate", startDate);
        model.addAttribute("dueDate", dueDate);
        model.addAttribute("payDay", payDay);

        return "employee/timesheet";
    }

    @PostMapping("timesheet")
    public String createEntry(@RequestParam String project, @RequestParam String workType, @RequestParam String daysOfTheWeek, @RequestParam Integer hours, Model model){

        ArrayList<Project> projects = ProjectData.getAllProjects();
        model.addAttribute("projects", projects);
        ArrayList<WorkType> workTypes = WorkTypeData.getAllWorkTypes();
        model.addAttribute("workTypes", workTypes);

        LineEntriesOnTimesheet newEntry = new LineEntriesOnTimesheet(ProjectData.findProjectByName(project), WorkTypeData.findWorkTypeByCode(workType), daysOfTheWeek, hours);

        ArrayList<LineEntriesOnTimesheet> logOfEntries = new ArrayList<>();
        logOfEntries.add(newEntry);
        currentTimesheet.setLineEntries(logOfEntries);
        model.addAttribute("logOfEntries", logOfEntries);
        model.addAttribute("testAttribute", "Fox is the Best baby ever");
//        model.addAttribute("day", currentTimesheet.getLineEntries().get(0).getDayOfTheWeek());
        model.addAttribute("day", daysOfTheWeek);
        model.addAttribute("entry1Project", currentTimesheet.getLineEntries().get(0).getProject().toString());
        model.addAttribute("entry1WorkType", currentTimesheet.getLineEntries().get(0).getWorkType().toStringWorkTypeCode());
        model.addAttribute("entry1Hours", currentTimesheet.getLineEntries().get(0).getHours());
        return "employee/timesheet";
    }


}
