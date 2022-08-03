package com.example.firstdraftspringfinalproject;

import com.example.firstdraftspringfinalproject.controllers.AuthenticationController;
import com.example.firstdraftspringfinalproject.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.example.firstdraftspringfinalproject.data.EmployeeRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AuthenticationFilter extends HandlerInterceptorAdapter {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    AuthenticationController authenticationController;


    // REVERSED THE WHITELIST LOGIC HERE - I was struggling with whitelisting the home page ("/"), and not loosing my css
    private static final List<String> blacklist = Arrays.asList("/employee", "/supervisor");
    private static boolean isBlacklisted(String path) {
        for (String pathRoot : blacklist) {
            if (path.startsWith(pathRoot)) {
                return false;
            }
        }
        return true;
    }

    private static final List<String> greylist = Arrays.asList("/supervisor");
    private static boolean isGreylisted(String path) {
        for (String pathRoot : greylist) {
            if (path.startsWith(pathRoot)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws IOException {

        if(isBlacklisted(request.getRequestURI())){
            System.out.println("I am in the prehandle under the if blacklisted statement");
            return true;
        }



        HttpSession session = request.getSession();
        Employee employee = authenticationController.getEmployeeFromSession(session);

        //Authorization - if the user is logged in and trying to access a restricted page
        if(isGreylisted(request.getRequestURI())){
            if (!employee.getSupervisorAccess()){
                response.sendRedirect("/restricted");
                return true;
            }
        }

        // The user is logged in
        if (employee != null) {
            return true;
        }

        // The user is NOT logged in
        response.sendRedirect("/");
        return false;
    }

}
