package com.example.firstdraftspringfinalproject.controllers.supervisorportal;

import com.example.firstdraftspringfinalproject.data.ProjectRepository;
import com.example.firstdraftspringfinalproject.models.Event;
import com.example.firstdraftspringfinalproject.models.Shipment;
import com.example.firstdraftspringfinalproject.models.enums.ShipmentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Calendar;

@Controller
@RequestMapping("supervisor/manageshipments")
public class ManageShipmentsController {

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping
    public String displayManageShipments(Model model){
        model.addAttribute("title", "Manage Shipments");

        //TODO - delete following after having added shipments / events to the database

        ArrayList<Event> currentMonthEvents = new ArrayList<>();

        Calendar calFeb4 = Calendar.getInstance();
        calFeb4.set(Calendar.YEAR, 2022);
        calFeb4.set(Calendar.MONTH, Calendar.FEBRUARY);
        calFeb4.set(Calendar.DAY_OF_MONTH, 4);

        Calendar calDec01 = Calendar.getInstance();
        calDec01.set(Calendar.YEAR, 2022);
        calDec01.set(Calendar.MONTH, Calendar.DECEMBER);
        calDec01.set(Calendar.DAY_OF_MONTH, 1);

        Calendar calDec10 = Calendar.getInstance();
        calDec10.set(Calendar.YEAR, 2022);
        calDec10.set(Calendar.MONTH, Calendar.DECEMBER);
        calDec10.set(Calendar.DAY_OF_MONTH, 10);

        Calendar calDec15 = Calendar.getInstance();
        calDec15.set(Calendar.YEAR, 2022);
        calDec15.set(Calendar.MONTH, Calendar.DECEMBER);
        calDec15.set(Calendar.DAY_OF_MONTH, 15);
        Event foxBirthday = new Event(calDec01, calFeb4, "Fox's Birthday");
        Event maggieBirthday = new Event(calDec01, calDec10, "Maggie's Birthday");
        Event maddyBirthday = new Event(calDec15, calDec15, "Maddy Brithday");
        currentMonthEvents.add(foxBirthday);
        currentMonthEvents.add(maggieBirthday);
        currentMonthEvents.add(maddyBirthday);
        model.addAttribute("currentMonthEvents", currentMonthEvents);
        model.addAttribute("eventTotal", 3);


        return "supervisor/manageshipments";
    }

    @GetMapping("/addShipment")
    public String displayAddAnIncomingShipment(Model model){
        model.addAttribute("title", "Add Shipment");

        model.addAttribute(new Shipment());
        ArrayList<ShipmentType> shipmentTypes = new ArrayList<>();
        shipmentTypes.add(ShipmentType.INCOMING);
        shipmentTypes.add(ShipmentType.OUTGOING);
        model.addAttribute("shipmentTypes", shipmentTypes);
        model.addAttribute("projects", projectRepository.findAll());

        return "supervisor/newshipment";
    }

    @PostMapping("/addShipment")
    public String processAddAnIncomingShipment(Model model,
                                               @RequestParam(required = false) String companyName,
                                               @RequestParam(required = false) String firstName,
                                               @RequestParam(required = false) String lastName,
                                               @RequestParam(required = false) String address,
                                               @RequestParam(required = false) String city,
                                               @RequestParam(required = false) String state,
                                               @RequestParam(required = false) String zipCode,
                                               @RequestParam(required = false) String email,
                                               @RequestParam(required = false) String phoneNumber){

        System.out.println(companyName);
        return "supervisor/manageshipments";
    }

//    @GetMapping("/addOutgoing")
//    public String displayAddAnOutgoingShipment(Model model){
//        return "supervisor/newshipment";
//    }

}
