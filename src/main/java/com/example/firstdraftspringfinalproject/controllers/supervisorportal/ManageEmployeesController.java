package com.example.firstdraftspringfinalproject.controllers.supervisorportal;

import com.example.firstdraftspringfinalproject.data.EmployeeRepository;
import com.example.firstdraftspringfinalproject.data.TimesheetRepository;
import com.example.firstdraftspringfinalproject.models.Employee;
import com.example.firstdraftspringfinalproject.models.OtpGenerator;
import com.example.firstdraftspringfinalproject.models.Timesheet;
import com.example.firstdraftspringfinalproject.models.dto.CreateEmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.Optional;

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
                                                  Errors errors, Model model,
                                                  RedirectAttributes redirectAttributes){
        if (errors.hasErrors()){
            model.addAttribute("title", "Add an Employee");
            return new RedirectView("supervisor/newemployee");
        }
        OtpGenerator otpGenerator = new OtpGenerator();
        otpGenerator.setOtp(5);
        String oneTimePassword = otpGenerator.getOtp();
        createEmployeeDTO.setOneTimePassword(oneTimePassword);

        Employee newEmployee = new Employee(createEmployeeDTO.getFirstName(),
                createEmployeeDTO.getLastName(),
                createEmployeeDTO.getTitle(),
                createEmployeeDTO.getPayRate(),
                createEmployeeDTO.getPaidTimeOff(),
                createEmployeeDTO.getOneTimePassword());
        System.out.println(createEmployeeDTO.getFirstName());
        System.out.println(createEmployeeDTO.getOneTimePassword());
        employeeRepository.save(newEmployee);

        redirectAttributes.addFlashAttribute("employeeFirstName", createEmployeeDTO.getFirstName());
        redirectAttributes.addFlashAttribute("employeeFirstTimePassword", createEmployeeDTO.getOneTimePassword());

        return new RedirectView("/supervisor/manageemployeeprofiles/successNewEmployee", true);
    }

    @GetMapping(value = "editEmployee")
    public String displayEditEmployeeForm(Model model, @RequestParam Integer employeeId){
        model.addAttribute("employee", employeeRepository.findById(employeeId).get());
        return "supervisor/editemployee";
    }

    @GetMapping(value = "retireemployee")
    public String displayRetireEmployeeForm(){
        return "supervisor/retireemployee";
    }

    @GetMapping(value="timesheets")
    public String displayTimesheetsPage(Model model){
        model.addAttribute("all", "all");
        model.addAttribute("approval", "approval");
        model.addAttribute("previousChoice", "all");
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
                Integer employeeId = employeeRepository.findByLastName(lastName).get().getEmployeeId();
                model.addAttribute("timesheets", timesheetRepository.findByEmployeeEmployeeIdAndCompletionStatus(employeeId, true));
            }else{
                model.addAttribute("error", "There is no employee with that last name");
            }
        }
        model.addAttribute("all", "all");
        model.addAttribute("approval", "approval");
        model.addAttribute("previousChoice", searchType);
        return "supervisor/timesheets";
    }

    @GetMapping(value="timesheets/detail")
    public String displayTimesheetDetail(@RequestParam Integer timesheetId, Model model){
        timesheetRepository.findById(timesheetId).ifPresent(timesheet -> model.addAttribute("timesheet", timesheet));
        return "supervisor/viewtimesheet";
    }

    @PostMapping(value="timesheets/approve")
    public RedirectView processSubmitTimesheetForm(@RequestParam Integer timesheetId, RedirectAttributes redirectAttributes){
        if (timesheetRepository.findById(timesheetId).isPresent()){
            Timesheet timesheet = timesheetRepository.findById(timesheetId).get();
            timesheet.setSupervisorApproval(true);
            timesheetRepository.save(timesheet);

            redirectAttributes.addFlashAttribute("timesheetEmployee", timesheet.getEmployee().getFirstName() + " " + timesheet.getEmployee().getLastName());
            redirectAttributes.addFlashAttribute("timesheetWeek", Timesheet.formatDates(timesheet.getStartDate()) + " - " + Timesheet.formatDates(timesheet.getDueDate()));
        }
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

        return "supervisor/timesheets";
    }


}
