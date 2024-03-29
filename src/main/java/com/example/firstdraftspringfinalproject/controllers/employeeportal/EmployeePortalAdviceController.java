package com.example.firstdraftspringfinalproject.controllers.employeeportal;

import com.example.firstdraftspringfinalproject.controllers.employeeportal.timesheet.TimesheetAdviceController;
import com.example.firstdraftspringfinalproject.data.ProjectRepository;
import com.example.firstdraftspringfinalproject.data.TimesheetRepository;
import com.example.firstdraftspringfinalproject.data.WorkTypeRepository;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.contacts.Contact;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets.Timesheet;
import com.example.firstdraftspringfinalproject.models.enums.DaysOfWeek;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;


@ControllerAdvice("com.example.firstdraftspringfinalproject.controllers.employeeportal")
public class EmployeePortalAdviceController {

    @Autowired
    private TimesheetRepository timesheetRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private WorkTypeRepository workTypeRepository;


    @ModelAttribute("currentTimesheet")
    public void getCurrentTimesheet(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Integer employeeId = (Integer) session.getAttribute("user");

        ArrayList<Timesheet> timesheets = (ArrayList<Timesheet>) timesheetRepository.findByEmployeeIdAndCompletionStatusAndSupervisorApproval(employeeId, false, false);
        if(timesheets.size() != 1){
            System.out.println("There is no one current timesheet!");
            System.out.println("there are : "+ timesheets.size()+ "timesheets - Are in Employee Portal Advice Controller");
        } else {
            Timesheet currentTimesheet = timesheets.get(0);
            model.addAttribute("currentTimesheet", currentTimesheet);
        }
    }

    public static void getThisTimesheetsDatesForDisplay(@ModelAttribute("currentTimesheet") Timesheet currentTimesheet, Model model){
        TimesheetAdviceController.getTodaysDate(model);
        TimesheetAdviceController.getThisTimesheetsStartDate(currentTimesheet, model);
        TimesheetAdviceController.getThisTimesheetsDueDate(currentTimesheet, model);
        TimesheetAdviceController.getThisTimesheetsPayDay(currentTimesheet, model);
    }

    public static void getDropDownMenusOptions(Model model){
        TimesheetAdviceController.getDaysOfWeek(model);
    }

    @ModelAttribute("projects")
    public void getAllProjects(Model model){
        model.addAttribute("projects", projectRepository.findAll());
    }

    @ModelAttribute("workTypes")
    public void getAllWorkTypes(Model model){
        model.addAttribute("workTypes", workTypeRepository.findAll());
    }

    @ModelAttribute("states")
    public void getFiftyStates(Model model){
        model.addAttribute("states", Contact.ALLSTATESPOSTALCODES);
    }

}
