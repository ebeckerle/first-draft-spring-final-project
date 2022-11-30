package com.example.firstdraftspringfinalproject.controllers.supervisorportal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("supervisor/manageshipments")
public class ManageShipmentsController {

    @GetMapping
    public String displayManageShipments(Model model){
        model.addAttribute("title", "Manage Shipments");
        return "supervisor/manageshipments";
    }

    @GetMapping("/addIncoming")
    public String displayAddAnIncomingShipment(Model model){
        return "supervisor/newshipment";
    }

    @GetMapping("/addOutgoing")
    public String displayAddAnOutgoingShipment(Model model){
        return "supervisor/newshipment";
    }

}
