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
    private ProjectRepository projectRepository;

    @Autowired
    private WorkTypeRepository workTypeRepository;

    @Autowired
    private TimesheetRepository timesheetRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    //THE Following are all Model attributes for populating the Timesheet Form, and are used in methods in the Timesheet
    //Controller and the Employee Portal Controller

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


//    @ModelAttribute("todaysDate")
//    public void getTodaysDate(Model model){
//        LocalDate currentDate = LocalDate.now();
//        String today = currentDate.getDayOfWeek()+", "+currentDate.getMonth()+"/"+currentDate.getDayOfMonth()+"/"+currentDate.getYear();
//        model.addAttribute("today", today);
//    }
//
//    @ModelAttribute("startDate")
//    public void getThisTimesheetsStartDate(@ModelAttribute("currentTimesheet") Timesheet currentTimesheet, Model model){
//        if(currentTimesheet.getStartDate() == null){
//            System.out.println("curent tmsheets start date is null");
//        }else {
//            model.addAttribute("startDate", TimesheetCalculateDates.formatDates(currentTimesheet.getStartDate()));
//        }
//    }
//
//    @ModelAttribute("dueDate")
//    public void getThisTimesheetsDueDate(@ModelAttribute("currentTimesheet") Timesheet currentTimesheet, Model model){
//        if(currentTimesheet.getDueDate() == null){
//            System.out.println("curent tmsheets due date is null");
//        }else{
//            model.addAttribute("dueDate", TimesheetCalculateDates.formatDates(currentTimesheet.getDueDate()));
//        }
//    }
//
//    @ModelAttribute("payDay")
//    public void getThisTimesheetsPayDay(@ModelAttribute("currentTimesheet") Timesheet currentTimesheet, Model model){
//        model.addAttribute("payDay", TimesheetCalculateDates.formatDates(currentTimesheet.getPayDay()));
//    }
//
//    @ModelAttribute("logOfEntries")
//    public void getLogOfEntries(@ModelAttribute("currentTimesheet") Timesheet currentTimesheet, Model model){
//        model.addAttribute("logOfEntries", currentTimesheet.getLineEntries());
//    }
//
//    @ModelAttribute("mondayTotal")
//    public void getMondayTotal(@ModelAttribute("currentTimesheet") Timesheet currentTimesheet, Model model){
//        model.addAttribute("mondayTotal", currentTimesheet.totalDayOfWeekHours("Monday"));
//    }
//
//    @ModelAttribute("tuesdayTotal")
//    public void getTuesdayTotal(@ModelAttribute("currentTimesheet") Timesheet currentTimesheet, Model model){
//        model.addAttribute("tuesdayTotal", currentTimesheet.totalDayOfWeekHours("Tuesday"));
//    }
//
//    @ModelAttribute("wednesdayTotal")
//    public void getWednesdayTotal(@ModelAttribute("currentTimesheet") Timesheet currentTimesheet, Model model){
//        model.addAttribute("wednesdayTotal", currentTimesheet.totalDayOfWeekHours("Wednesday"));
//    }
//
//    @ModelAttribute("thursdayTotal")
//    public void getThursdayTotal(@ModelAttribute("currentTimesheet") Timesheet currentTimesheet, Model model){
//        model.addAttribute("thursdayTotal", currentTimesheet.totalDayOfWeekHours("Thursday"));
//    }
//
//    @ModelAttribute("fridayTotal")
//    public void getFridayTotal(@ModelAttribute("currentTimesheet") Timesheet currentTimesheet, Model model){
//        model.addAttribute("fridayTotal", currentTimesheet.totalDayOfWeekHours("Friday"));
//    }
//
//    @ModelAttribute("saturdayTotal")
//    public void getSaturdayTotal(@ModelAttribute("currentTimesheet") Timesheet currentTimesheet, Model model){
//        model.addAttribute("saturdayTotal", currentTimesheet.totalDayOfWeekHours("Saturday"));
//    }
//
//    @ModelAttribute("totalHoursForTheWeek")
//    public void getTotalHoursForTheWeek(@ModelAttribute("currentTimesheet") Timesheet currentTimesheet, Model model){
//        model.addAttribute("totalHoursForTheWeek", currentTimesheet.getTotalHours());
//    }


//    @ModelAttribute("projects")
//            public void getAllProjects(Model model){
//        model.addAttribute("projects", projectRepository.findAll());
//    }
//
//    @ModelAttribute("workTypes")
//    public void getAllWorkTypes(Model model){
//        model.addAttribute("workTypes", workTypeRepository.findAll());
//    }
//
//    @ModelAttribute("daysOfWeek")
//    public void getDaysOfWeek(Model model){
//        model.addAttribute("daysOfWeek", DaysOfWeek.values());
//    }




}
