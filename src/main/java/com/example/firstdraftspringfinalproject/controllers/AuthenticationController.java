package com.example.firstdraftspringfinalproject.controllers;

import com.example.firstdraftspringfinalproject.data.EmployeeRepository;
import com.example.firstdraftspringfinalproject.models.Employee;
import com.example.firstdraftspringfinalproject.models.dto.LoginFormDTO;
import com.example.firstdraftspringfinalproject.models.dto.RegisterFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class AuthenticationController {

    @Autowired
    EmployeeRepository employeeRepository;

    private static final String userSessionKey = "user";

    public Employee getEmployeeFromSession(HttpSession session) {
        Integer employeeId = (Integer) session.getAttribute(userSessionKey);
        if (employeeId == null) {
            return null;
        }

        Optional<Employee> user = employeeRepository.findById(employeeId);

        if (user.isEmpty()) {
            return null;
        }

        return user.get();
    }

    private static void setUserInSession(HttpSession session, Employee employee) {
        session.setAttribute(userSessionKey, employee.getEmployeeId());
    }


    @GetMapping("register")
    public String displayRegistrationPage(Model model){
        model.addAttribute(new RegisterFormDTO());
        model.addAttribute("title", "Register");
        return "register";
    }

    @PostMapping("register")
    public String processRegistrationForm(@ModelAttribute @Valid RegisterFormDTO registerFormDTO,
                                          Errors errors, HttpServletRequest request, Model model){

        if(errors.hasErrors()){
            model.addAttribute("title", "Register");
            return "register";
        }

        Employee existingEmployee = employeeRepository.findByUsername(registerFormDTO.getUsername());

        if(existingEmployee != null) {
            errors.rejectValue("username", "username.alreadyexists", "A user with that username already exists");
            model.addAttribute("title", "Register");
            return "register";
        }

        String password = registerFormDTO.getPassword();
        String verifyPassword = registerFormDTO.getVerifyPassword();
        if (!password.equals(verifyPassword)) {
            errors.rejectValue("password", "passwords.mismatch", "Passwords do not match");
            model.addAttribute("title", "Register");
            return "register";
        }

        //so I think when I refactor this I don't want to create a new Employee but update an existing here
        Employee newEmployee = new Employee(registerFormDTO.getUsername(), registerFormDTO.getPassword());
        employeeRepository.save(newEmployee);
        setUserInSession(request.getSession(), newEmployee);

        //return "employee/home";
        //but if employee has supervisor access return "supervisor/home";
        return "employee/home";
    }

    @GetMapping
    public String displayHomePage(Model model){
        model.addAttribute(new LoginFormDTO());
        return "index";
    }

    @PostMapping
    public String processLoginForm(@ModelAttribute @Valid LoginFormDTO loginFormDTO, Errors errors, HttpServletRequest request, Model model){
        if (errors.hasErrors()){
            return "index";
        }
        Employee theEmployee = employeeRepository.findByUsername(loginFormDTO.getUsername());

        if (theEmployee == null){
            errors.rejectValue("username", "user.invalid", "The given username does not exist");
            return "index";
        }

        String password = loginFormDTO.getPassword();

        if (!theEmployee.isMatchingPassword(password)) {
            errors.rejectValue("password", "password.invalid", "Invalid password");
            return "login";
        }

        setUserInSession(request.getSession(), theEmployee);

        if (theEmployee.getSupervisorAccess()) {
            return "redirect:/supervisor";
        }

        model.addAttribute("employeeName", theEmployee.getFirstName());

        return "redirect:/employee";


    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "logout";
    }
}
