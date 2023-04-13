package com.example.firstdraftspringfinalproject.controllers.employeeportal;

import com.example.firstdraftspringfinalproject.data.*;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.Employee;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets.Timesheet;
import com.example.firstdraftspringfinalproject.models.enums.DaysOfWeek;
import com.example.firstdraftspringfinalproject.models.interfaces.TimesheetCalculateDates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
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

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private WorkTypeRepository workTypeRepository;

    //lives at /employee, renders employee>home.html
    @GetMapping
    public String displayEmployeeWelcome(HttpServletRequest request, Model model){

        HttpSession session = request.getSession();
        Integer employeeId = (Integer) session.getAttribute("user");

        if (employeeRepository.findById(employeeId).isPresent()){
            Employee employee = employeeRepository.findById(employeeId).get();

            LocalDate todaysDate = LocalDate.now();
            GregorianCalendar thisWeeksStartDate = TimesheetCalculateDates.figureStartDateBasedOnTodaysDate(todaysDate);

            model.addAttribute("title", "Home");
            model.addAttribute("employeeName", employee.getFirstName());

            //if the employee has already completed a timesheet for this week notify them of the status or display the
            //button with hidden form to create a new timesheet or link to edit current timesheet
            if (timesheetRepository.findByEmployeeEmployeeIdAndStartDateAndCompletionStatus(employeeId, thisWeeksStartDate, true).isPresent()){
                model.addAttribute("thisWeekTimesheetSubmissionMessage", "You have already submitted a timesheet for this week.");
            }else{
                //the following are hidden in the form to create or edit timesheet
                model.addAttribute("todaysDate", todaysDate);
                model.addAttribute("employeeId", employeeId);
                model.addAttribute("completionStatus", employee.getCurrentTimesheetCompletionStatus());
            }

            //add a model attribute for message about this week's timesheet
            Optional<Timesheet> thisWeeksTimesheet = timesheetRepository.findByEmployeeEmployeeIdAndStartDate(employeeId, thisWeeksStartDate);
            if(thisWeeksTimesheet.isPresent()){
                String startDate = TimesheetCalculateDates.formatDates(thisWeeksTimesheet.get().getStartDate());
                if(thisWeeksTimesheet.get().getCompletionStatus()){
                    if(thisWeeksTimesheet.get().getSupervisorApproval()){
                        model.addAttribute("thisWeekTimesheet", "Your timesheet for the week of "+startDate+ " has been approved!");
                    }else if (!thisWeeksTimesheet.get().getSupervisorApproval()){
                        model.addAttribute("thisWeekTimesheet", "Your timesheet for the week of "+startDate+ " is awaiting approval.");
                    }
                }
            }
            //add a model attribute for message about last week's timesheet
            GregorianCalendar lastWeeksStartDate = TimesheetCalculateDates.figureLastWeeksStartDateBasedOnTodaysDate(todaysDate);
            Optional<Timesheet> lastWeeksTimesheet = timesheetRepository.findByEmployeeEmployeeIdAndStartDate(employeeId, lastWeeksStartDate);
            if(lastWeeksTimesheet.isPresent()){
                String startDate = TimesheetCalculateDates.formatDates(lastWeeksTimesheet.get().getStartDate());

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
    @PostMapping("/timesheet")
    public String createNewTimesheet(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate todaysDate, @RequestParam Integer employeeId, Model model){


        if (employeeRepository.findById(employeeId).isPresent()){
            //create a new timesheet object based on the employee (figured from employeeId)
            Timesheet newTimesheet = new Timesheet(employeeRepository.findById(employeeId).get());

            //set the new timesheet's start date based on todaysDate using setDates() method
            GregorianCalendar startDateGC = TimesheetCalculateDates.figureStartDateBasedOnTodaysDate(todaysDate);
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
            String startDate = TimesheetCalculateDates.formatDates(newTimesheet.getStartDate());
            String dueDate = TimesheetCalculateDates.formatDates(newTimesheet.getDueDate());
            String payDay = TimesheetCalculateDates.formatDates(newTimesheet.getPayDay());
            LocalDate currentDate = LocalDate.now();
            String today = currentDate.getDayOfWeek()+", "+currentDate.getMonth()+"/"+currentDate.getDayOfMonth()+"/"+currentDate.getYear();
            model.addAttribute("today", today);
            model.addAttribute("startDate", startDate);
            model.addAttribute("dueDate", dueDate);
            model.addAttribute("payDay", payDay);

            model.addAttribute("title", "Timesheet");

            //display the model attributes for the line entry Table Form (the 1st one)
            model.addAttribute("projects", projectRepository.findAll());
            model.addAttribute("workTypes", workTypeRepository.findAll());
            model.addAttribute("daysOfWeek", DaysOfWeek.values());
            model.addAttribute("employeeId", employeeId);
        }

        return "employee/timesheet";
    }

    //form submit lives at employee/timesheet url (or employee/timesheet/createnewlineentry url), renders at employee/successSubmit url
    @PostMapping("/successSubmit")
    public RedirectView processSubmitTimesheet(@RequestParam Integer currentTimesheetId,
                                               @RequestParam Integer mondayTotal,
                                               @RequestParam Integer tuesdayTotal,
                                               @RequestParam Integer wednesdayTotal,
                                               @RequestParam Integer thursdayTotal,
                                               @RequestParam Integer fridayTotal,
                                               @RequestParam Integer saturdayTotal,
                                               HttpServletRequest request,
                                               RedirectAttributes redirectAttributes, Model model){
        //grab the current timesheet
        Timesheet currentTimesheet = timesheetRepository.findById(currentTimesheetId).get();
        //set the total of monday's hours, tuesdays hours, etc
        currentTimesheet.setTotalMondayHours(mondayTotal);
        currentTimesheet.setTotalTuesdayHours(tuesdayTotal);
        currentTimesheet.setTotalWednesdayHours(wednesdayTotal);
        currentTimesheet.setTotalThursdayHours(thursdayTotal);
        currentTimesheet.setTotalFridayHours(fridayTotal);
        currentTimesheet.setTotalSaturdayHours(saturdayTotal);
        // set the current payrate
        currentTimesheet.setCurrentPayRate();
        //set the total hours
        currentTimesheet.setTotalHours();
        //set the completion Status to true
        currentTimesheet.setCompletionStatus(true);
        //save the current timesheet
        timesheetRepository.save(currentTimesheet);
        //set the employee's current timesheet completion status to true
        HttpSession session = request.getSession();
        Integer employeeId = (Integer) session.getAttribute("user");
        Employee loggedInEmployee = employeeRepository.findById(employeeId).get();
        loggedInEmployee.setCurrentTimesheetCompletionStatus(true);
        //save the employee
        employeeRepository.save(loggedInEmployee);

        redirectAttributes.addFlashAttribute("timesheetStartDate", currentTimesheet.getStartDate());
        redirectAttributes.addFlashAttribute("timesheetTotalHours", currentTimesheet.getTotalHours());
        redirectAttributes.addFlashAttribute("timesheetPayDay", currentTimesheet.getPayDay());

        model.addAttribute("title", "Success!");

        return new RedirectView("/employee/successSubmit", true);
    }



}
