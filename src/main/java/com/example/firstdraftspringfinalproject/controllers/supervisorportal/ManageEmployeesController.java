package com.example.firstdraftspringfinalproject.controllers.supervisorportal;

import com.example.firstdraftspringfinalproject.data.EmployeeRepository;
import com.example.firstdraftspringfinalproject.data.TimesheetRepository;
import com.example.firstdraftspringfinalproject.models.Employee;
import com.example.firstdraftspringfinalproject.models.OtpGenerator;
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
import java.util.Map;

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
        return "supervisor/timesheets";
    }

    @PostMapping(value="timesheets/results")
    public String processTimesheetSearch(@RequestParam String searchType, @RequestParam(required = false) String lastName, Model model){
        if(searchType.equals("all")){
            model.addAttribute("timesheets", timesheetRepository.findAll());
        }else if (searchType.equals("approval")){
            model.addAttribute("timesheets", timesheetRepository.findBySupervisorApprovalAndCompletionStatus(false, true));
        }
//        else if (searchType.equals("lastName")){
//            model.addAttribute("timesheets", timesheetRepository.findByLastNameAndCompletionStatus(lastName, true));
//        }
        return "supervisor/timesheets";
    }

    @GetMapping(value="timesheets/detail")
    public String displayTimesheetDetail(@RequestParam Integer timesheetId, Model model){
        model.addAttribute("timesheet", timesheetRepository.findById(timesheetId));
        return "viewtimesheet";
    }

}
