package com.example.firstdraftspringfinalproject.controllers.employeeportal.timesheet;

import com.example.firstdraftspringfinalproject.data.*;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.Project;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets.DaysOfWeekHoursSet;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets.LineEntry;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets.Timesheet;
import com.example.firstdraftspringfinalproject.models.enums.DaysOfWeek;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;

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
        LineEntry theNewLineEntry = new LineEntry(projectRepository.findByProjectId(projectId), workTypeRepository.findByWorkTypeId(workTypeId), DaysOfWeek.valueOf(daysOfWeek), hours,currentTimesheet);
        if(theNewLineEntry.isLineEntryOnTimesheet(currentTimesheet)){
            LineEntry existingLineEntry = currentTimesheet.findMatchingLineEntry(theNewLineEntry);
            currentTimesheet.updateLineEntry(existingLineEntry, theNewLineEntry);
        }else{
            currentTimesheet.getLineEntries().add(theNewLineEntry);
        }
        //update the timesheets total hours for each Day of the Week
        currentTimesheet.updateEachDayOfWeekTotalHours();
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
        model.addAttribute(new LineEntry());
        // find the current timesheet for correct employee
        ArrayList<Timesheet> timesheets = (ArrayList<Timesheet>) timesheetRepository.findByEmployeeEmployeeIdAndCompletionStatusAndSupervisorApproval(employeeId, false, false);
        Timesheet currentTimesheet = timesheets.get(0);
        model.addAttribute("currentTimesheetId", currentTimesheet.getId());
        model.addAttribute("lineEntry", lineEntryRepository.findById(lineEntryId).orElseThrow());
        model.addAttribute("title", "Edit Line Entry");
        return "employee/editlineentry";

    }

    @PostMapping(value = "editlineentry", params = "save")
    public String processEditLineEntryEdit(@RequestParam Integer lineEntryId, @RequestParam Integer currentTimesheetId,
                                           @ModelAttribute @Valid LineEntry editedLineEntry, Errors errors,
                                           @ModelAttribute("currentTimesheet") Timesheet currentTimesheet,
                                           Model model){
        if (errors.hasErrors()){
            model.addAttribute("currentTimesheetId", currentTimesheet.getId());
            model.addAttribute("lineEntry", lineEntryRepository.findById(lineEntryId).orElseThrow());
            model.addAttribute("title", "Edit Line Entry");
            System.out.println("in the errors");
            return "employee/editlineentry";
        }
        LineEntry existingLineEntry = lineEntryRepository.findById(lineEntryId).orElseThrow();
        currentTimesheet.replaceLineEntry(existingLineEntry, editedLineEntry);

        //update the timesheets total hours for each Day of the Week
        currentTimesheet.updateEachDayOfWeekTotalHours();
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
