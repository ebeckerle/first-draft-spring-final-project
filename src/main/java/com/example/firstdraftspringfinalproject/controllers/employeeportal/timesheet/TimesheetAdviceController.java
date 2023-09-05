package com.example.firstdraftspringfinalproject.controllers.employeeportal.timesheet;

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
import java.util.*;

@ControllerAdvice("com.example.firstdraftspringfinalproject.controllers.employeeportal.timesheet")
public class TimesheetAdviceController {

        @Autowired
        private ProjectRepository projectRepository;

        @Autowired
        private WorkTypeRepository workTypeRepository;

        @Autowired
        private TimesheetRepository timesheetRepository;

        @Autowired
        private EmployeeRepository employeeRepository;

        @ModelAttribute("currentTimesheet")
        public void getCurrentTimesheet(HttpServletRequest request, Model model) {
            HttpSession session = request.getSession();
            Integer employeeId = (Integer) session.getAttribute("user");

            ArrayList<Timesheet> timesheets = (ArrayList<Timesheet>) timesheetRepository.findByEmployeeIdAndCompletionStatusAndSupervisorApproval(employeeId, false, false);
            if(timesheets.size() != 1){
                System.out.println("There is no one current timesheet! - TimesheetAdvice Controller");
                System.out.println("there are : "+ timesheets.size()+ "timesheets - ");
            } else {
                Timesheet currentTimesheet = timesheets.get(0);
                model.addAttribute("currentTimesheet", currentTimesheet);
            }
        }


        @ModelAttribute("todaysDate")
        public void getTodaysDate(Model model){
            LocalDate currentDate = LocalDate.now();
            String today = currentDate.getDayOfWeek()+", "+currentDate.getMonth()+"/"+currentDate.getDayOfMonth()+"/"+currentDate.getYear();
            model.addAttribute("today", today);
        }

        @ModelAttribute("startDate")
        public void getThisTimesheetsStartDate(@ModelAttribute("currentTimesheet") Timesheet currentTimesheet, Model model){
            if(currentTimesheet.getStartDate() == null){
                System.out.println("curent tmsheets start date is null");
            }else {
                model.addAttribute("startDate", TimesheetCalculateDates.formatDates(currentTimesheet.getStartDate()));
            }
        }

        @ModelAttribute("dueDate")
        public void getThisTimesheetsDueDate(@ModelAttribute("currentTimesheet") Timesheet currentTimesheet, Model model){
            if(currentTimesheet.getDueDate() == null){
                System.out.println("curent tmsheets due date is null");
            }else{
                model.addAttribute("dueDate", TimesheetCalculateDates.formatDates(currentTimesheet.getDueDate()));
            }
        }

        @ModelAttribute("payDay")
        public void getThisTimesheetsPayDay(@ModelAttribute("currentTimesheet") Timesheet currentTimesheet, Model model){
            model.addAttribute("payDay", TimesheetCalculateDates.formatDates(currentTimesheet.getPayDay()));
        }

        @ModelAttribute("logOfEntries")
        public void getLogOfEntries(@ModelAttribute("currentTimesheet") Timesheet currentTimesheet, Model model){
            model.addAttribute("logOfEntries", currentTimesheet.getLineEntries());
        }

        @ModelAttribute("mondayTotal")
        public void getMondayTotal(@ModelAttribute("currentTimesheet") Timesheet currentTimesheet, Model model){
            model.addAttribute("mondayTotal", currentTimesheet.totalDayOfWeekHours(DaysOfWeek.MONDAY));
            System.out.println("Timesheet Advice Controller: getMondayTotal Method: MOnday Total:"+ currentTimesheet.totalDayOfWeekHours(DaysOfWeek.MONDAY));
        }

        @ModelAttribute("tuesdayTotal")
        public void getTuesdayTotal(@ModelAttribute("currentTimesheet") Timesheet currentTimesheet, Model model){
            model.addAttribute("tuesdayTotal", currentTimesheet.totalDayOfWeekHours(DaysOfWeek.TUESDAY));
        }

        @ModelAttribute("wednesdayTotal")
        public void getWednesdayTotal(@ModelAttribute("currentTimesheet") Timesheet currentTimesheet, Model model){
            model.addAttribute("wednesdayTotal", currentTimesheet.totalDayOfWeekHours(DaysOfWeek.WEDNESDAY));
        }

        @ModelAttribute("thursdayTotal")
        public void getThursdayTotal(@ModelAttribute("currentTimesheet") Timesheet currentTimesheet, Model model){
            model.addAttribute("thursdayTotal", currentTimesheet.totalDayOfWeekHours(DaysOfWeek.THURSDAY));
        }

        @ModelAttribute("fridayTotal")
        public void getFridayTotal(@ModelAttribute("currentTimesheet") Timesheet currentTimesheet, Model model){
            model.addAttribute("fridayTotal", currentTimesheet.totalDayOfWeekHours(DaysOfWeek.FRIDAY));
        }

        @ModelAttribute("saturdayTotal")
        public void getSaturdayTotal(@ModelAttribute("currentTimesheet") Timesheet currentTimesheet, Model model){
            model.addAttribute("saturdayTotal", currentTimesheet.totalDayOfWeekHours(DaysOfWeek.SATURDAY));
        }

        @ModelAttribute("totalHoursForTheWeek")
        public void getTotalHoursForTheWeek(@ModelAttribute("currentTimesheet") Timesheet currentTimesheet, Model model){
            model.addAttribute("totalHoursForTheWeek", currentTimesheet.getTotalHours());
        }

        @ModelAttribute("dayOfWeekTotals")
        public void getDayOfWeekTotals(@ModelAttribute("currentTimesheet") Timesheet currentTimesheet, Model model){
            List<Integer> dayOfWeekTotals = new ArrayList<>();
            dayOfWeekTotals.add(currentTimesheet.totalDayOfWeekHours(DaysOfWeek.MONDAY));
            dayOfWeekTotals.add(currentTimesheet.totalDayOfWeekHours(DaysOfWeek.TUESDAY));
            dayOfWeekTotals.add(currentTimesheet.totalDayOfWeekHours(DaysOfWeek.WEDNESDAY));
            dayOfWeekTotals.add(currentTimesheet.totalDayOfWeekHours(DaysOfWeek.THURSDAY));
            dayOfWeekTotals.add(currentTimesheet.totalDayOfWeekHours(DaysOfWeek.FRIDAY));
            dayOfWeekTotals.add(currentTimesheet.totalDayOfWeekHours(DaysOfWeek.SATURDAY));
            model.addAttribute("dayOfWeekTotals", dayOfWeekTotals);
        }



        @ModelAttribute("projects")
        public void getAllProjects(Model model){
            model.addAttribute("projects", projectRepository.findAll());
        }

        @ModelAttribute("workTypes")
        public void getAllWorkTypes(Model model){
            model.addAttribute("workTypes", workTypeRepository.findAll());
        }

        @ModelAttribute("daysOfWeek")
        public void getDaysOfWeek(Model model){
            model.addAttribute("daysOfWeek", DaysOfWeek.values());
        }

}
