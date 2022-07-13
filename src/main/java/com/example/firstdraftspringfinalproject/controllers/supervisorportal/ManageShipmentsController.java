package com.example.firstdraftspringfinalproject.controllers.supervisorportal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("supervisor/manageshipments")
public class ManageShipmentsController {

    @GetMapping
    public String displayManageShipments(){
        return "supervisor/manageshipments";
    }

}
