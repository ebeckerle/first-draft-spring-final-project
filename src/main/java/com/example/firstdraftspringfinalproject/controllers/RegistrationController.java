package com.example.firstdraftspringfinalproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("register")
public class RegistrationController {


    @GetMapping
    public String displayRegistrationPage(){
        return "register";
    }
}
