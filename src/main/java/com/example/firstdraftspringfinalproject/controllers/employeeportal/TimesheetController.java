package com.example.firstdraftspringfinalproject.controllers.employeeportal;

import com.example.firstdraftspringfinalproject.data.*;
import com.example.firstdraftspringfinalproject.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;


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
        Timesheet currentTimesheet = timesheetRepository.findByEmployeeEmployeeIdAndCompletionStatus(employeeId, false);

        //display the Dates for this Timesheet
        String startDate = currentTimesheet.formatDates(currentTimesheet.getStartDate());
        String dueDate = currentTimesheet.formatDates(currentTimesheet.getDueDate());
        String payDay = currentTimesheet.formatDates(currentTimesheet.getPayDay());
        LocalDate currentDate = LocalDate.now();
        String today = currentDate.getDayOfWeek()+", "+currentDate.getMonth()+"/"+currentDate.getDayOfMonth()+"/"+currentDate.getYear();
        model.addAttribute("today", today);
        model.addAttribute("startDate", startDate);
        model.addAttribute("dueDate", dueDate);
        model.addAttribute("payDay", payDay);

        //DISPLAY the necessary attributes for the timesheet table (the second table)
        model.addAttribute("logOfEntries", currentTimesheet.getLineEntries());

        for (LineEntry lineEntry:
                currentTimesheet.getLineEntries()) {
            System.out.println("entry: "
                    + lineEntry.getProjectWorkTypeCombo().getProject().getProjectCode()
                    + lineEntry.getProjectWorkTypeCombo().getWorkType().getWorkTypeId().toString());
        }

        //We need total hours worked on each individual day of the week and display them in the last row of the table
        model.addAttribute("mondayTotal", currentTimesheet.totalDayOfWeekHours("Monday"));
        model.addAttribute("tuesdayTotal", currentTimesheet.totalDayOfWeekHours("Tuesday"));
        model.addAttribute("wednesdayTotal", currentTimesheet.totalDayOfWeekHours("Wednesday"));
        model.addAttribute("thursdayTotal", currentTimesheet.totalDayOfWeekHours("Thursday"));
        model.addAttribute("fridayTotal", currentTimesheet.totalDayOfWeekHours("Friday"));
        model.addAttribute("saturdayTotal", currentTimesheet.totalDayOfWeekHours("Saturday"));

        model.addAttribute("title", "Current Timesheet");

        return "employee/timesheet";
    }

    @GetMapping("/createlineentry")
    public String displayCreateLineEntryForm(HttpServletRequest request, Model model){

        //CONTINUE to display the model attributes for the line entry Table Form (the 1st one)
        model.addAttribute("projects", projectRepository.findAll());
        model.addAttribute("workTypes", workTypeRepository.findAll());

        ArrayList<String> daysOfWeek1 = new ArrayList<>();
        String monday = "Monday";
        String tuesday = "Tuesday";
        String wednesday = "Wednesday";
        String thursday = "Thursday";
        String friday = "Friday";
        String saturday = "Saturday";
        daysOfWeek1.add(monday);
        daysOfWeek1.add(tuesday);
        daysOfWeek1.add(wednesday);
        daysOfWeek1.add(thursday);
        daysOfWeek1.add(friday);
        daysOfWeek1.add(saturday);
        model.addAttribute("daysOfWeek", daysOfWeek1);

        LocalDate currentDate = LocalDate.now();
        String today = currentDate.getDayOfWeek()+", "+currentDate.getMonth()+"/"+currentDate.getDayOfMonth()+"/"+currentDate.getYear();
        model.addAttribute("today", today);

        model.addAttribute("title", "Add hours to your Timesheet");
        //not sure what to do with this

        HttpSession session = request.getSession();
        Integer employeeId = (Integer) session.getAttribute("user");
        model.addAttribute("employeeId", employeeId);

        return "employee/createlineentry";
    }


    @PostMapping("createlineentry")
    public String processCreateLineEntryForm(@RequestParam Integer employeeId, @RequestParam String project, @RequestParam String workType, @RequestParam String daysOfWeek, @RequestParam Integer hours){

        //1ST lets find the correct employee's arraylist of timesheets
//        Employee employee = EmployeeData.getEmployeeById(employeeId);

        //2nd lets find the current timesheet
        Timesheet currentTimesheet = timesheetRepository.findByEmployeeEmployeeIdAndCompletionStatus(employeeId, false);

        Integer workTypeId = WorkType.fromToStringToId(workType);

        //check if project & worktype combo already exists in database
        ProjectWorkTypeSet projectWorkTypeSet = projectWorkTypeSetRepository.findByProjectAndWorkType(projectRepository.findByProjectName(project), workTypeRepository.findByWorkTypeId(workTypeId));

        if (projectWorkTypeSet == null){
            // , if not, save the new project worktype combo
            projectWorkTypeSetRepository.save(new ProjectWorkTypeSet(projectRepository.findByProjectName(project), workTypeRepository.findByWorkTypeId(workTypeId)));
            System.out.println("we are in the if statement");

        }

//        ProjectWorkTypeSet projectWorkTypeCombo = projectWorkTypeSetRepository.findByProjectAndWorkType(projectRepository.findByProjectName(project), workTypeRepository.findByWorkTypeId(workTypeId));
//        Integer projectWorkTypeId = projectWorkTypeCombo.getId();
        Integer projectWorkTypeId =  projectWorkTypeSetRepository.findByProjectAndWorkType(projectRepository.findByProjectName(project), workTypeRepository.findByWorkTypeId(workTypeId)).getId();
        ProjectWorkTypeSet projectWorkTypeCombo = projectWorkTypeSetRepository.findById(projectWorkTypeId).get();
        System.out.println(projectWorkTypeId);
        DaysOfWeekHoursSet daysOfWeekHoursCombo = new DaysOfWeekHoursSet(daysOfWeek, hours);
        System.out.println(hours);
        System.out.println(daysOfWeek);
        daysOfWeekHoursSetRepository.save(daysOfWeekHoursCombo);

        //Create the new line entry object
        LineEntry newEntry = new LineEntry(projectWorkTypeCombo, daysOfWeekHoursCombo);

        lineEntryRepository.deleteById(83);

        //check if the lineEntry Project WorkType Combo already exists as a line entry,
        if (currentTimesheet.checkALineEntry(newEntry)){
            //first grab existing entry's id so we can delete it after we update it and save a new one
            Integer existingLineEntryId = currentTimesheet.getLineEntryWithMatchingProjectWorkType(projectWorkTypeCombo).getId();
//            Integer existingLineEntryId = lineEntryRepository.findByProjectWorkTypeComboIdAndTimesheet(projectWorkTypeCombo, currentTimesheet).getId();
            System.out.println("existingLineEntryId" + existingLineEntryId);
            // if so, update that line entry,
            newEntry.updateALineEntry(daysOfWeekHoursCombo);
            System.out.println("we are in the if statement to check if the Project WorkType Combo already exists as a line entry");
            System.out.println("Before save" + newEntry.getId());
            //save for now
            lineEntryRepository.save(newEntry);
            System.out.println("After save" +newEntry.getId());
            LineEntry oldLineEntryToDelete = lineEntryRepository.findById(existingLineEntryId).get();
            lineEntryRepository.delete(oldLineEntryToDelete);
            System.out.println();
        }
        // if not, add a new line entry.
        lineEntryRepository.save(newEntry);
        currentTimesheet.getLineEntries().add(newEntry);

        timesheetRepository.save(currentTimesheet);

        return "redirect:";
    }

    //MODEL BOUND VERSION OF ABOVE
//    @PostMapping("createlineentry")
//    public String processCreateLineEntryForm(@RequestParam Integer employeeId, @ModelAttribute LineEntry lineEntry){
//
//               //1ST lets find the correct employee's arraylist of timesheets
//        Employee employee = EmployeeData.getEmployeeById(employeeId);
//
//        //2nd lets find the current timesheet
//        Timesheet currentTimesheet = EmployeeData.findCurrentTimesheetForEmployee(employee);
//
//        //check if the lineEntry Project WorkType Combo already exists as a line entry, if so, update that line entry, if not, add a new line entry.
//        String dayOfWeek = (String) lineEntry.getDayOfWeekAndHours().keySet().toArray()[0];
//        Integer hours = lineEntry.getDayOfWeekAndHours().get(dayOfWeek);
//        currentTimesheet.checkAndAddALineEntry(lineEntry, dayOfWeek, hours);
//
//        return "redirect:";
//    }


}
