package com.example.firstdraftspringfinalproject.controllers.supervisorportal;

import com.example.firstdraftspringfinalproject.data.EmployeeRepository;
import com.example.firstdraftspringfinalproject.models.Employee;
import com.example.firstdraftspringfinalproject.models.OtpGenerator;
import com.example.firstdraftspringfinalproject.models.dto.CreateEmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("supervisor/manageemployeeprofiles")
public class ManageEmployeeProfilesController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping(value = "")
    public String displayManageEmployeeProfilesHome(Model model){
        model.addAttribute("employees", employeeRepository.findAll());
        return "supervisor/manageemployeeprofiles";
    }

    @GetMapping("newemployee")
    public String displayAddNewEmployeeForm(Model model){
        model.addAttribute("title", "Add an Employee");
//        OtpGenerator otpGenerator = new OtpGenerator();
//        otpGenerator.setOtp(5);
//        String oneTimePassword = otpGenerator.getOtp();
//        model.addAttribute("oneTimePassword", oneTimePassword);
//        model.addAttribute("supervisorAccess", true);
        model.addAttribute(new CreateEmployeeDTO());
        return "supervisor/newemployee";
    }

    @PostMapping("newemployee")
    public String processAddNewEmployeeForm(@ModelAttribute @Valid CreateEmployeeDTO createEmployeeDTO, Errors errors, Model model){
        if (errors.hasErrors()){
            model.addAttribute("title", "Add an Employee");
            return "supervisor/newemployee";
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
        return "redirect:";
    }

    @GetMapping(value = "editEmployee")
    public String displayEditEmployeeForm(Model model, @RequestParam Integer employeeId){
        model.addAttribute("employee", employeeRepository.findById(employeeId).get());
        return "supervisor/editemployee";
    }


}
