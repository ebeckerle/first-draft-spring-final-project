package com.example.firstdraftspringfinalproject.controllers;

import com.example.firstdraftspringfinalproject.data.EmployeeRepository;
import com.example.firstdraftspringfinalproject.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class AuthenticationController {

    @Autowired
    EmployeeRepository employeeRepository;

    private static final String userSessionKey = "user";

    public Employee getUserFromSession(HttpSession session) {
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


}
