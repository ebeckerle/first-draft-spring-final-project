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




    private static final List<String> whitelist = Arrays.asList("/", "/register", "/logout", "/css");

    private static boolean isWhitelisted(String path) {
        for (String pathRoot : whitelist) {
            if (path.startsWith(pathRoot)) {
                return true;
            }
        }
        return false;
    }

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

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws IOException {

        if(isBlacklisted(request.getRequestURI())){
            return true;
        }

//        if(isWhitelisted(request.getRequestURI())){
//            return true;
//        }

        HttpSession session = request.getSession();
        Employee employee = authenticationController.getEmployeeFromSession(session);

        // The user is logged in
        if (employee != null) {
            return true;
        }

        // The user is NOT logged in
        response.sendRedirect("/");
        return false;
    }

}
