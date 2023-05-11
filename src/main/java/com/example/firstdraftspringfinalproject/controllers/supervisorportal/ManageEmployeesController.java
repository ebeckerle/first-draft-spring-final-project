package com.example.firstdraftspringfinalproject.controllers.supervisorportal;

import com.example.firstdraftspringfinalproject.data.EmployeeRepository;
import com.example.firstdraftspringfinalproject.data.TimesheetRepository;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.Employee;
import com.example.firstdraftspringfinalproject.models.interfaces.TimesheetCalculateDates;
import com.example.firstdraftspringfinalproject.models.pojo.OtpGenerator;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets.Timesheet;
import com.example.firstdraftspringfinalproject.models.dto.CreateEmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("supervisor/manageemployees")
public class ManageEmployeesController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TimesheetRepository timesheetRepository;

    @GetMapping(value = "")
    public String displayManageEmployeeProfilesHome(Model model){
        model.addAttribute("employees", employeeRepository.findAll());
        if (!timesheetRepository.findBySupervisorApprovalAndCompletionStatus(false, true).isEmpty()){
            model.addAttribute("timesheetsForApproval", timesheetRepository.findBySupervisorApprovalAndCompletionStatus(false, true).size());
        }
        model.addAttribute("title", "Manage Employees");
        return "supervisor/manageemployees";
    }

    @GetMapping(value = "/successNewEmployee")
    public String displayManageEmployeeProfilesHomeSuccessNewEmployee(HttpServletRequest request, Model model){

        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        String employeeFirstName = (String) inputFlashMap.get("employeeFirstName");
        String employeeFirstTimePassword = (String) inputFlashMap.get("employeeFirstTimePassword");

        model.addAttribute("employeeFirstName", employeeFirstName);
        model.addAttribute("employeeFirstTimePassword", employeeFirstTimePassword);
        model.addAttribute("successSubmit", "true");
        model.addAttribute("employees", employeeRepository.findAll());

        model.addAttribute("title", "Manage Employees");
        return "supervisor/manageemployees";
    }

    @GetMapping("newemployee")
    public String displayAddNewEmployeeForm(Model model){
        model.addAttribute("title", "Add an Employee");
        model.addAttribute(new CreateEmployeeDTO());
        return "supervisor/newemployee";
    }

    @PostMapping("newemployee")
    public RedirectView processAddNewEmployeeForm(@ModelAttribute @Valid CreateEmployeeDTO createEmployeeDTO,
                                                  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
                                                  Errors errors, Model model,
                                                  RedirectAttributes redirectAttributes){
        if (errors.hasErrors()){
            model.addAttribute("title", "Add an Employee");
            redirectAttributes.addFlashAttribute("errors", errors);
            return new RedirectView("/supervisor/manageemployees/newemployee", true);
        }
        OtpGenerator otpGenerator = new OtpGenerator();
        otpGenerator.setOtp(5);
        String oneTimePassword = otpGenerator.getOtp();
        createEmployeeDTO.setOneTimePassword(oneTimePassword);

        //convert Date to a new GregorianCalendar date
        int dayOfMonth = startDate.getDate();
        int monthValue = startDate.getMonth();
        int year = startDate.getYear();
        GregorianCalendar startDateGC = new GregorianCalendar(year+1900, monthValue, dayOfMonth+1);

        Employee newEmployee = new Employee(createEmployeeDTO.getFirstName(),
                createEmployeeDTO.getLastName(),
                createEmployeeDTO.getTitle(),
                createEmployeeDTO.getPayRate(),
                createEmployeeDTO.getPaidTimeOff(),
                createEmployeeDTO.getOneTimePassword());
        newEmployee.setFirstDateOfWork(startDateGC);
        employeeRepository.save(newEmployee);

        redirectAttributes.addFlashAttribute("employeeFirstName", createEmployeeDTO.getFirstName());
        redirectAttributes.addFlashAttribute("employeeFirstTimePassword", createEmployeeDTO.getOneTimePassword());

        model.addAttribute("title", "Manage Employees");

        return new RedirectView("/supervisor/manageemployees/successNewEmployee", true);
    }

    @GetMapping(value = "editEmployee")
    public String displayEditEmployeeForm(Model model, @RequestParam Integer employeeId){
        model.addAttribute("employee", employeeRepository.findById(employeeId).get());
        model.addAttribute("title", "Edit Employee Details");
        return "supervisor/editemployee";
    }

    @GetMapping(value = "retireemployee")
    public String displayRetireEmployeeForm(Model model){
        model.addAttribute("title", "Retire An Employee");
        return "supervisor/retireemployee";
    }

    @GetMapping(value="timesheets")
    public String displayTimesheetsPage(Model model){
        model.addAttribute("all", "all");
        model.addAttribute("approval", "approval");
        model.addAttribute("previousChoice", "all");
        model.addAttribute("title", "Employees' Timesheets");
        return "supervisor/timesheets";
    }

    @PostMapping(value="timesheets/results")
    public String processTimesheetSearch(@RequestParam String searchType, @RequestParam(required = false) String lastName, Model model){
        if(searchType.equals("all")){
            model.addAttribute("timesheets", timesheetRepository.findByCompletionStatus(true));
        }else if (searchType.equals("approval")){
            model.addAttribute("timesheets", timesheetRepository.findBySupervisorApprovalAndCompletionStatus(false, true));
        }
        else if (searchType.equals("lastName")){
            if(employeeRepository.findByLastName(lastName).isPresent()){
                Integer employeeId = employeeRepository.findByLastName(lastName).get().getId();
                model.addAttribute("timesheets", timesheetRepository.findByEmployeeIdAndCompletionStatus(employeeId, true));
            }else{
                model.addAttribute("error", "There is no employee with that last name");
            }
        }
        model.addAttribute("all", "all");
        model.addAttribute("approval", "approval");
        model.addAttribute("previousChoice", searchType);

        model.addAttribute("title", "Employees' Timesheets");
        return "supervisor/timesheets";
    }

    @GetMapping(value="timesheets/detail")
    public String displayTimesheetDetail(@RequestParam Integer timesheetId, Model model){
        timesheetRepository.findById(timesheetId).ifPresent(timesheet -> model.addAttribute("timesheet", timesheet));
        model.addAttribute("title", timesheetRepository.findById(timesheetId).get().getEmployee().getFirstName()+"'s Timesheet");
        return "supervisor/viewtimesheet";
    }

    @PostMapping(value="timesheets/approve")
    public RedirectView processSubmitTimesheetForm(@RequestParam Integer timesheetId, RedirectAttributes redirectAttributes, Model model){
        if (timesheetRepository.findById(timesheetId).isPresent()){
            Timesheet timesheet = timesheetRepository.findById(timesheetId).get();
            timesheet.setSupervisorApproval(true);
            if(employeeRepository.findById(timesheet.getEmployee().getId()).isPresent()){
                Employee employee = employeeRepository.findById(timesheet.getEmployee().getId()).get();
                employee.resetTotalApprovedHoursWorkedToDate();
                employeeRepository.save(employee);
            }
            timesheetRepository.save(timesheet);

            redirectAttributes.addFlashAttribute("timesheetEmployee", timesheet.getEmployee().getFirstName() + " " + timesheet.getEmployee().getLastName());
            redirectAttributes.addFlashAttribute("timesheetWeek", TimesheetCalculateDates.formatDates(timesheet.getStartDate()) + " - " + TimesheetCalculateDates.formatDates(timesheet.getDueDate()));
        }

        model.addAttribute("title", "Success!");
        return new RedirectView("successfulapproval", true);
    }

    @GetMapping("timesheets/successfulapproval")
    public String displayTimesheetsPageAfterSuccessfulApproval(HttpServletRequest request, Model model){

        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        String timesheetEmployee = (String) inputFlashMap.get("timesheetEmployee");
        String timesheetWeek = (String) inputFlashMap.get("timesheetWeek");
        model.addAttribute("successSubmit", "You Have Successfully Approved ");
        model.addAttribute("timesheetEmployee", timesheetEmployee);
        model.addAttribute("timesheetWeek", timesheetWeek);

        model.addAttribute("all", "all");
        model.addAttribute("approval", "approval");

        model.addAttribute("title", "Employees' Timesheets");

        return "supervisor/timesheets";
    }


}
