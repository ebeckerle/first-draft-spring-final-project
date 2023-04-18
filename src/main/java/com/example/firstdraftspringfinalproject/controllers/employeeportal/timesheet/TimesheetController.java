package com.example.firstdraftspringfinalproject.controllers.employeeportal.timesheet;

import com.example.firstdraftspringfinalproject.data.*;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.*;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets.DaysOfWeekHoursSet;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets.LineEntry;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets.ProjectWorkTypeSet;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets.Timesheet;
import com.example.firstdraftspringfinalproject.models.enums.DaysOfWeek;
import com.example.firstdraftspringfinalproject.models.interfaces.TimesheetCalculateDates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// - TODO  -  add a PTO feature,
//  TODO - make it so that the employee cannot submit more than one timesheet for the given week

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

        //DISPLAY
        model.addAttribute("title", "Current Timesheet");
        model.addAttribute("employeeId", employeeId);

        return "employee/timesheet";
    }


    @PostMapping("/createlineentry")
    public String processCreateLineEntryForm(@RequestParam Integer employeeId,
                                             @RequestParam Integer projectId,
                                             @RequestParam Integer workTypeId,
                                             @RequestParam String daysOfWeek,
                                             @RequestParam Integer hours,
                                             Model model,
                                             @ModelAttribute("currentTimesheet") Timesheet currentTimesheet){
        //check if line entry already exists on this Timesheet in particular, so we can either add or update
        DaysOfWeekHoursSet newDaysOfWeekHoursSet = new DaysOfWeekHoursSet(daysOfWeek, hours);
        LineEntry theNewLineEntry = new LineEntry(projectRepository.findByProjectId(projectId), workTypeRepository.findByWorkTypeId(workTypeId), newDaysOfWeekHoursSet, currentTimesheet);
        if(theNewLineEntry.isLineEntryOnTimesheet(currentTimesheet)){
            LineEntry existingLineEntry = currentTimesheet.findMatchingLineEntry(theNewLineEntry);
            currentTimesheet.updateLineEntry(existingLineEntry, theNewLineEntry);
        }else{
            currentTimesheet.getLineEntries().add(theNewLineEntry);
        }
        //update the timesheets total hours for each Day of the Week
        currentTimesheet.updateAndResetEachDayOfWeekTotalHours();
        currentTimesheet.setTotalHours();
        timesheetRepository.save(currentTimesheet);

        //DISPLAY
        model.addAttribute("title", "Current Timesheet");
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
        //update total hours field in current timesheet
        currentTimesheet.setTotalHours();
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

        currentTimesheet.setTotalHours();

        model.addAttribute("title", "Timesheet");

        return "redirect:/employee/timesheet";
    }


}
