package com.example.firstdraftspringfinalproject.controllers.employeeportal;

import com.example.firstdraftspringfinalproject.controllers.AuthenticationController;
import com.example.firstdraftspringfinalproject.data.*;
import com.example.firstdraftspringfinalproject.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Map;
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

        if (employeeRepository.findById(employeeId).isPresent()){
            Employee employee = employeeRepository.findById(employeeId).get();

            LocalDate todaysDate = LocalDate.now();

            model.addAttribute("title", "Home");
            model.addAttribute("employeeName", employee.getFirstName());

            //the following are hidden in the form to create or edit timesheet
            model.addAttribute("todaysDate", todaysDate);
            model.addAttribute("employeeId", employeeId);
            model.addAttribute("completionStatus", employee.getCurrentTimesheetCompletionStatus());

            //add a model attribute
            GregorianCalendar lastWeeksStartDate = Timesheet.figureLastWeeksStartDateBasedOnTodaysDate(todaysDate);
            Optional<Timesheet> lastWeeksTimesheet = timesheetRepository.findByEmployeeEmployeeIdAndStartDate(employeeId, lastWeeksStartDate);
            if(lastWeeksTimesheet.isPresent()){
                String startDate = Timesheet.formatDates(lastWeeksTimesheet.get().getStartDate());

                if(lastWeeksTimesheet.get().getSupervisorApproval()){
                    model.addAttribute("lastWeekTimesheet", "Your timesheet for the week of "+startDate+ " has been approved!");
                }else if (!lastWeeksTimesheet.get().getSupervisorApproval()){
                    model.addAttribute("lastWeekTimesheet", "Your timesheet for the week of "+startDate+ " is awaiting approval.");
                }
            }
        }

        return "employee/home";
    }

    @GetMapping("/successSubmit")
    public String displayEmployeeWelcomeAfterSuccessSubmit(HttpServletRequest request, Model model){

        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        GregorianCalendar startDate = (GregorianCalendar) inputFlashMap.get("timesheetStartDate");
        model.addAttribute("successSubmit", "You Have Successfully Submitted your Timesheet for the week of: ");
        model.addAttribute("startDate", startDate);

        HttpSession session = request.getSession();
        Integer employeeId = (Integer) session.getAttribute("user");

        if (employeeRepository.findById(employeeId).isPresent()){
            Employee employee = employeeRepository.findById(employeeId).get();

            LocalDate todaysDate = LocalDate.now();

            model.addAttribute("title", "Home");
            model.addAttribute("employeeName", employee.getFirstName());

            //the following are hidden in the form
            model.addAttribute("todaysDate", todaysDate);
            model.addAttribute("employeeId", employeeId);
            model.addAttribute("completionStatus", employee.getCurrentTimesheetCompletionStatus());
        }

        return "employee/home";
    }


    //lives at /employee, but renders employee>timesheet
    @PostMapping
    public String createNewTimesheet(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate todaysDate, @RequestParam Integer employeeId, Model model){


        if (employeeRepository.findById(employeeId).isPresent()){
            //create a new timesheet object based on the employee (figured from employeeId)
            Timesheet newTimesheet = new Timesheet(employeeRepository.findById(employeeId).get());

            //set the new timesheet's start date based on todaysDate using setDates() method
            GregorianCalendar startDateGC = newTimesheet.figureStartDateBasedOnTodaysDate(todaysDate);
            newTimesheet.setDates(startDateGC);

            //set the new timesheet completion status and supervisor approval as false
            newTimesheet.setCompletionStatus(false);
            newTimesheet.setSupervisorApproval(false);
            timesheetRepository.save(newTimesheet);

            //set the employee's current timesheet completion status as false
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

            model.addAttribute("title", "Timesheet");
        }

        return "employee/timesheet";
    }



}
