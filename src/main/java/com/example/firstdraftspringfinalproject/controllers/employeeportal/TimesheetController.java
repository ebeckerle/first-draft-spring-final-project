package com.example.firstdraftspringfinalproject.controllers.employeeportal;

import com.example.firstdraftspringfinalproject.data.*;
import com.example.firstdraftspringfinalproject.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;

// - TODO  - TODO - add a PTO feature, make it so that the employee cannot submit more than one timesheet for the given week

@Controller
@RequestMapping("employee/timesheet")
public class TimesheetController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private WorkTypeRepository workTypeRepository;

    @Autowired
    private TimesheetRepository timesheetRepository;

    @Autowired
    private LineEntryRepository lineEntryRepository;

    @Autowired
    private ProjectWorkTypeSetRepository projectWorkTypeSetRepository;

    @Autowired
    private DaysOfWeekHoursSetRepository daysOfWeekHoursSetRepository;


    @GetMapping
    public String displayCurrentTimesheet(HttpServletRequest request, Model model){

        // find the correct employee for the session
        HttpSession session = request.getSession();
        Integer employeeId = (Integer) session.getAttribute("user");

        // find the current timesheet for correct employee
        ArrayList<Timesheet> timesheets = (ArrayList<Timesheet>) timesheetRepository.findByEmployeeEmployeeIdAndCompletionStatusAndSupervisorApproval(employeeId, false, false);
        Timesheet currentTimesheet = timesheets.get(0);

        //display the Dates for this Timesheet
        String startDate = Timesheet.formatDates(currentTimesheet.getStartDate());
        String dueDate = Timesheet.formatDates(currentTimesheet.getDueDate());
        String payDay = Timesheet.formatDates(currentTimesheet.getPayDay());
        LocalDate currentDate = LocalDate.now();
        String today = currentDate.getDayOfWeek()+", "+currentDate.getMonth()+"/"+currentDate.getDayOfMonth()+"/"+currentDate.getYear();
        model.addAttribute("today", today);
        model.addAttribute("startDate", startDate);
        model.addAttribute("dueDate", dueDate);
        model.addAttribute("payDay", payDay);

        //DISPLAY the necessary attributes for the timesheet table (the second table)
        model.addAttribute("logOfEntries", currentTimesheet.getLineEntries());

        //We need total hours worked on each individual day of the week and display them in the last row of the table
        Integer mondayTotal = currentTimesheet.totalDayOfWeekHours("Monday");
        Integer tuesdayTotal = currentTimesheet.totalDayOfWeekHours("Tuesday");
        Integer wednesdayTotal = currentTimesheet.totalDayOfWeekHours("Wednesday");
        Integer thursdayTotal = currentTimesheet.totalDayOfWeekHours("Thursday");
        Integer fridayTotal = currentTimesheet.totalDayOfWeekHours("Friday");
        Integer saturdayTotal = currentTimesheet.totalDayOfWeekHours("Saturday");
        model.addAttribute("mondayTotal", mondayTotal);
        model.addAttribute("tuesdayTotal", tuesdayTotal);
        model.addAttribute("wednesdayTotal", wednesdayTotal);
        model.addAttribute("thursdayTotal", thursdayTotal);
        model.addAttribute("fridayTotal", fridayTotal);
        model.addAttribute("saturdayTotal", saturdayTotal);

        model.addAttribute("totalHoursForTheWeek", mondayTotal + tuesdayTotal + wednesdayTotal + thursdayTotal + fridayTotal + saturdayTotal);

        model.addAttribute("title", "Current Timesheet");
        model.addAttribute("currentTimesheet", currentTimesheet);

        //CONTINUE to display the model attributes for the line entry Table Form (the 1st one)
        model.addAttribute("projects", projectRepository.findAll());
        model.addAttribute("workTypes", workTypeRepository.findAll());
        model.addAttribute("daysOfWeek", DaysOfWeek.values());
        model.addAttribute("employeeId", employeeId);

        return "employee/timesheet";
    }


    @PostMapping("/createlineentry")
    public String processCreateLineEntryForm(@RequestParam Integer employeeId,
                                             @RequestParam String project,
                                             @RequestParam String workType,
                                             @RequestParam String daysOfWeek,
                                             @RequestParam Integer hours,
                                             HttpServletRequest request, Model model){

        //find the current timesheet
        ArrayList<Timesheet> timesheets = (ArrayList<Timesheet>) timesheetRepository.findByEmployeeEmployeeIdAndCompletionStatusAndSupervisorApproval(employeeId, false, false);
        Timesheet currentTimesheet = timesheets.get(0);

        Integer workTypeId = WorkType.fromToStringToId(workType);

        //check if project & worktype combo already exists in database
        ProjectWorkTypeSet projectWorkTypeSet = projectWorkTypeSetRepository.findByProjectAndWorkType(projectRepository.findByProjectName(project), workTypeRepository.findByWorkTypeId(workTypeId));

        if (projectWorkTypeSet == null){
            // , if not, save the new project worktype combo
            projectWorkTypeSetRepository.save(new ProjectWorkTypeSet(projectRepository.findByProjectName(project), workTypeRepository.findByWorkTypeId(workTypeId)));
        }

        Integer projectWorkTypeId =  projectWorkTypeSetRepository.findByProjectAndWorkType(projectRepository.findByProjectName(project), workTypeRepository.findByWorkTypeId(workTypeId)).getId();
        ProjectWorkTypeSet projectWorkTypeCombo = projectWorkTypeSetRepository.findById(projectWorkTypeId).get();
        DaysOfWeekHoursSet daysOfWeekHoursCombo = new DaysOfWeekHoursSet(daysOfWeek, hours);
        daysOfWeekHoursSetRepository.save(daysOfWeekHoursCombo);

        //Create the new line entry object
        LineEntry newEntry = new LineEntry(projectWorkTypeCombo, daysOfWeekHoursCombo, currentTimesheet);

        //check if the lineEntry Project WorkType Combo already exists as a line entry,
        if (currentTimesheet.checkALineEntry(newEntry)){
            //first grab incoming entry's id so we can delete it after we update it and save a new one
            Integer incomingLineEntryId = currentTimesheet.getLineEntryWithMatchingProjectWorkType(projectWorkTypeCombo).getId();

            //find the existing matching line entry:
            LineEntry existingLineEntry = lineEntryRepository.findByProjectWorkTypeComboAndTimesheet(projectWorkTypeCombo, currentTimesheet);

            // if so, update that line entry,
            DaysOfWeekHoursSet newCombinedDaysOfWeekHoursCombo = newEntry.updateALineEntry(daysOfWeekHoursCombo, existingLineEntry.getDaysOfWeekHoursCombo());
            daysOfWeekHoursSetRepository.save(newCombinedDaysOfWeekHoursCombo);
            newEntry.setDaysOfWeekHoursCombo(newCombinedDaysOfWeekHoursCombo);

            //save for now
            lineEntryRepository.save(newEntry);
            LineEntry oldLineEntryToDelete = lineEntryRepository.findById(incomingLineEntryId).get();
            currentTimesheet.getLineEntries().remove(oldLineEntryToDelete);
            lineEntryRepository.delete(oldLineEntryToDelete);
        }
        // if not, add a new line entry.
        lineEntryRepository.save(newEntry);
        currentTimesheet.getLineEntries().add(newEntry);

        timesheetRepository.save(currentTimesheet);

        //display the Dates for this Timesheet
        String startDate = Timesheet.formatDates(currentTimesheet.getStartDate());
        String dueDate = Timesheet.formatDates(currentTimesheet.getDueDate());
        String payDay = Timesheet.formatDates(currentTimesheet.getPayDay());
        LocalDate currentDate = LocalDate.now();
        String today = currentDate.getDayOfWeek()+", "+currentDate.getMonth()+"/"+currentDate.getDayOfMonth()+"/"+currentDate.getYear();
        model.addAttribute("today", today);
        model.addAttribute("startDate", startDate);
        model.addAttribute("dueDate", dueDate);
        model.addAttribute("payDay", payDay);

        //DISPLAY the necessary attributes for the timesheet table (the second table)
        model.addAttribute("logOfEntries", currentTimesheet.getLineEntries());

        //We need total hours worked on each individual day of the week and display them in the last row of the table
        Integer mondayTotal = currentTimesheet.totalDayOfWeekHours("Monday");
        Integer tuesdayTotal = currentTimesheet.totalDayOfWeekHours("Tuesday");
        Integer wednesdayTotal = currentTimesheet.totalDayOfWeekHours("Wednesday");
        Integer thursdayTotal = currentTimesheet.totalDayOfWeekHours("Thursday");
        Integer fridayTotal = currentTimesheet.totalDayOfWeekHours("Friday");
        Integer saturdayTotal = currentTimesheet.totalDayOfWeekHours("Saturday");
        model.addAttribute("mondayTotal", mondayTotal);
        model.addAttribute("tuesdayTotal", tuesdayTotal);
        model.addAttribute("wednesdayTotal", wednesdayTotal);
        model.addAttribute("thursdayTotal", thursdayTotal);
        model.addAttribute("fridayTotal", fridayTotal);
        model.addAttribute("saturdayTotal", saturdayTotal);

        model.addAttribute("totalHoursForTheWeek", mondayTotal + tuesdayTotal + wednesdayTotal + thursdayTotal + fridayTotal + saturdayTotal);

        model.addAttribute("title", "Current Timesheet");
        model.addAttribute("currentTimesheet", currentTimesheet);

        //CONTINUE to display the model attributes for the line entry Table Form (the 1st one)
        model.addAttribute("projects", projectRepository.findAll());
        model.addAttribute("workTypes", workTypeRepository.findAll());

        model.addAttribute("daysOfWeek", DaysOfWeek.values());
        model.addAttribute("employeeId", employeeId);

        return "employee/timesheet";
    }



    @GetMapping("/editlineentry")
    public String displayEditLineEntryForm(@RequestParam Integer lineEntryId, HttpServletRequest request, Model model){
        // find the correct employee for the session
        HttpSession session = request.getSession();
        Integer employeeId = (Integer) session.getAttribute("user");
        // find the current timesheet for correct employee
        ArrayList<Timesheet> timesheets = (ArrayList<Timesheet>) timesheetRepository.findByEmployeeEmployeeIdAndCompletionStatusAndSupervisorApproval(employeeId, false, false);
        Timesheet currentTimesheet = timesheets.get(0);
        model.addAttribute("currentTimesheetId", currentTimesheet.getId());
        model.addAttribute("lineEntry", lineEntryRepository.findById(lineEntryId).get());
        model.addAttribute("title", "Edit Line Entry");
        return "employee/editlineentry";

    }

    @PostMapping(value = "editlineentry", params = "save")
    public String processEditLineEntryEdit(@RequestParam Integer lineEntryId, @RequestParam Integer currentTimesheetId,
                                           @RequestParam(required = false) Integer mondayHours,
                                           @RequestParam(required = false) Integer tuesdayHours,
                                           @RequestParam(required = false) Integer wednesdayHours,
                                           @RequestParam(required = false) Integer thursdayHours,
                                           @RequestParam(required = false) Integer fridayHours,
                                           @RequestParam(required = false) Integer saturdayHours, Model model){
        //get the current dayhour combo attached to line entry
        DaysOfWeekHoursSet currentDayHourCombo = lineEntryRepository.findById(lineEntryId).get().getDaysOfWeekHoursCombo();
        //get the current dayhour combo's id so we can delete it later
        Integer currentDayHourComboId = currentDayHourCombo.getId();
        //get the project workType combo attached to the line entry (it's id)
        Integer projectWorkTypeId = lineEntryRepository.findById(lineEntryId).get().getProjectWorkTypeCombo().getId();
        //create a new dayhour combo that will replace the old one
        Integer existingMonday = daysOfWeekHoursSetRepository.findById(currentDayHourComboId).get().getMondayHours();
        if (mondayHours == null){
            mondayHours = existingMonday;
        }
        Integer existingTuesday = daysOfWeekHoursSetRepository.findById(currentDayHourComboId).get().getTuesdayHours();
        if (tuesdayHours == null){
            tuesdayHours = existingTuesday;
        }
        Integer existingWednesday = daysOfWeekHoursSetRepository.findById(currentDayHourComboId).get().getWednesdayHours();
        if (wednesdayHours == null){
            wednesdayHours = existingWednesday;
        }
        Integer existingThursday = daysOfWeekHoursSetRepository.findById(currentDayHourComboId).get().getThursdayHours();
        if (thursdayHours == null){
            thursdayHours = existingThursday;
        }
        Integer existingFriday = daysOfWeekHoursSetRepository.findById(currentDayHourComboId).get().getFridayHours();
        if (fridayHours == null){
            fridayHours = existingFriday;
        }
        Integer existingSaturday = daysOfWeekHoursSetRepository.findById(currentDayHourComboId).get().getSaturdayHours();
        if (saturdayHours == null){
            saturdayHours = existingSaturday;
        }
        DaysOfWeekHoursSet newDayHourCombo = new DaysOfWeekHoursSet(mondayHours, tuesdayHours, wednesdayHours, thursdayHours, fridayHours, saturdayHours);

        //remove the lineEntry from the Current Timesheet's array list of line entries
        Timesheet currentTimesheet = timesheetRepository.findById(currentTimesheetId).get();
        currentTimesheet.getLineEntries().remove(lineEntryRepository.findById(lineEntryId).get());
        //create a new line entry with project workType combo & new day hour combo
        LineEntry newEditedEntry = new LineEntry(projectWorkTypeSetRepository.findById(projectWorkTypeId).get(), newDayHourCombo, currentTimesheet);

        //delete the old Line Entry
        System.out.println(lineEntryId);
        lineEntryRepository.deleteById(lineEntryId);
        //save the new line entry to the repository
        lineEntryRepository.save(newEditedEntry);
        //add the new entry to the array list of line entries on the current timesheet
        currentTimesheet.getLineEntries().add(newEditedEntry);
        //save the current timesheet
        timesheetRepository.save(currentTimesheet);

        model.addAttribute("title", "Timesheet");

        return "redirect:/employee/timesheet";
    }

    @PostMapping(value = "editlineentry", params = "delete")
    public String processEditLineEntryDelete(@RequestParam Integer lineEntryId, @RequestParam Integer currentTimesheetId, Model model){
        //remove the lineEntry from the Current Timesheet's array list of line entries
        Timesheet currentTimesheet = timesheetRepository.findById(currentTimesheetId).get();

        LineEntry lineEntryToDelete = lineEntryRepository.findById(lineEntryId).get();

        currentTimesheet.getLineEntries().remove(lineEntryToDelete);
        lineEntryRepository.deleteById(lineEntryId);

        model.addAttribute("title", "Timesheet");

        return "redirect:/employee/timesheet";
    }


}
