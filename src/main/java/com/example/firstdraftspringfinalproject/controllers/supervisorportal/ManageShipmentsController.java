package com.example.firstdraftspringfinalproject.controllers.supervisorportal;

import com.example.firstdraftspringfinalproject.data.ProjectRepository;
import com.example.firstdraftspringfinalproject.models.Event;
import com.example.firstdraftspringfinalproject.models.Shipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@Controller
@RequestMapping("supervisor/manageshipments")
public class ManageShipmentsController {

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping
    public String displayManageShipments(Model model){
        model.addAttribute("title", "Manage Shipments");

        ArrayList<Event> currentMonthEvents = new ArrayList<>();
//        Date dec1 = new Date(2022, 11, 01);
//        Date dec10 = new Date(2022, 11, 10);
//        Date dec15 = new Date(2022, 11, 15);

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
//        Event foxBirthday = new Event(dec1, dec1, "Fox's Birthday");
//        Event maggieBirthday = new Event(dec1, dec10, "Maggie's Birthday");
//        Event maddyBirthday = new Event(dec15, dec15, "Maddy Brithday");
        currentMonthEvents.add(foxBirthday);
        currentMonthEvents.add(maggieBirthday);
        currentMonthEvents.add(maddyBirthday);
        model.addAttribute("currentMonthEvents", currentMonthEvents);
        model.addAttribute("eventTotal", 3);
        return "supervisor/manageshipments";
    }

    @GetMapping("/addIncoming")
    public String displayAddAnIncomingShipment(Model model){
        model.addAttribute(new Shipment());
        model.addAttribute("projects", projectRepository.findAll());
        return "supervisor/newshipment";
    }

    @PostMapping("/addIncoming")
    public String processAddAnIncomingShipment(Model model){
        return "supervisor/manageshipments";
    }

    @GetMapping("/addOutgoing")
    public String displayAddAnOutgoingShipment(Model model){
        return "supervisor/newshipment";
    }

}
