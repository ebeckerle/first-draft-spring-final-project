package com.example.firstdraftspringfinalproject.controllers.employeeportal;

import com.example.firstdraftspringfinalproject.controllers.AuthenticationController;
import com.example.firstdraftspringfinalproject.data.*;
import com.example.firstdraftspringfinalproject.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Optional;

@Controller
@RequestMapping("employee")
public class EmployeePortalController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TimesheetRepository timesheetRepository;

    //lives at /employee, renders employee>home.html
    @GetMapping
    public String displayEmployeeWelcome(HttpServletRequest request, Model model){

        HttpSession session = request.getSession();
        Integer employeeId = (Integer) session.getAttribute("user");

        Employee employee = employeeRepository.findById(employeeId).get();

        String employeeFirstName = employee.getFirstName();
        LocalDate todaysDate = LocalDate.now();

        model.addAttribute("title", "Home");
        model.addAttribute("employeeName", employeeFirstName);
        //the following are hidden in the form
        model.addAttribute("todaysDate", todaysDate);
        model.addAttribute("employeeId", employeeId);
        model.addAttribute("completionStatus", employee.getCurrentTimesheetCompletionStatus());

        return "employee/home";
    }

    //lives at /employee, but renders employee>timesheet
    @PostMapping
    public String createNewTimesheet(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate todaysDate, @RequestParam Integer employeeId, Model model){

        //create a new timesheet object based on the employee (figured from employeeId)
        Timesheet newTimesheet = new Timesheet(employeeRepository.findById(employeeId).get());
        //set the new timesheet's start date based on todaysDate using setDates() method
        GregorianCalendar startDateGC = newTimesheet.figureStartDateBasedOnTodaysDate(todaysDate);
        newTimesheet.setDates(startDateGC);
        //set the new timesheet completion status and supervisor approval as false
        newTimesheet.setCompletionStatus(false);
        newTimesheet.setSupervisorApproval(false);
        timesheetRepository.save(newTimesheet);

        //set the employee's current timesheet completion status as false - this line of code currently isn't working, not sure why...
        Employee employee = employeeRepository.findById(employeeId).get();
        employee.setCurrentTimesheetCompletionStatus(false);
        employeeRepository.save(employee);


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
        // i need to hardcode it and pass it through as a model attribute so I can grab it as a request parameter
        // in the Timesheet Controller
        model.addAttribute("employeeId", employeeId);


        return "employee/timesheet";
    }



}
