package com.example.firstdraftspringfinalproject.controllers.supervisorportal;

import com.example.firstdraftspringfinalproject.data.ContactRepository;
import com.example.firstdraftspringfinalproject.data.EventRepository;
import com.example.firstdraftspringfinalproject.data.ProjectRepository;
import com.example.firstdraftspringfinalproject.data.ShipmentRepository;
import com.example.firstdraftspringfinalproject.models.Contact;
import com.example.firstdraftspringfinalproject.models.Event;
import com.example.firstdraftspringfinalproject.models.Shipment;
import com.example.firstdraftspringfinalproject.models.enums.ContactType;
import com.example.firstdraftspringfinalproject.models.enums.ShipmentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@Controller
@RequestMapping("supervisor/manageshipments")
public class ManageShipmentsController {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private ContactRepository contactRepository;

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

        //TODO - coming from "Add an INcoming Shipment" link will take you to the AddShipment Form with the Type
        // pre-populated with "INCOMING" in the shipment type field., and vice-versa for outgoing.

        //TODO - display a list view vs. calendar view of shipments?  or just both to start, and then we can add the
        // toggle feature

        return "supervisor/manageshipments";
    }

    @GetMapping("/addShipment")
    public String displayAddAnIncomingShipment(Model model){
        model.addAttribute("title", "Add Shipment");

        model.addAttribute(new Shipment());
        //TODO - has to be a cleaner way to have an arraylist of my Shipment Types...
        ArrayList<ShipmentType> shipmentTypes = new ArrayList<>();
        shipmentTypes.add(ShipmentType.INCOMING);
        shipmentTypes.add(ShipmentType.OUTGOING);
        model.addAttribute("shipmentTypes", shipmentTypes);
        model.addAttribute("projects", projectRepository.findAll());
        if(contactRepository.findByContactType(ContactType.CARRIER).isPresent()){
            model.addAttribute("carriers", contactRepository.findByContactType(ContactType.CARRIER).get());
        }
        return "supervisor/newshipment";
    }

    @PostMapping("/addShipment")
    public String processAddAnIncomingShipment(Model model,
                                               @ModelAttribute @Valid Shipment newShipment,
                                               Errors errors,
                                               @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date incomingDateParam,
                                               @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date outgoingDateScheduledParam){

        if (errors.hasErrors()){
            model.addAttribute("title", "Add Shipment");

            model.addAttribute(new Shipment());
            //TODO - has to be a cleaner way to have an arraylist of my Shipment Types...
            ArrayList<ShipmentType> shipmentTypes = new ArrayList<>();
            shipmentTypes.add(ShipmentType.INCOMING);
            shipmentTypes.add(ShipmentType.OUTGOING);
            model.addAttribute("shipmentTypes", shipmentTypes);
            model.addAttribute("projects", projectRepository.findAll());
            return "supervisor/newshipment";
        }

        //if the new shipment is Incoming, appropriately set the incoming date
        if(newShipment.getType() == ShipmentType.INCOMING){
            //convert Date object to Calendar Object and create a new Event object?, the event Name will be
            // Shipment Name for now?
            Calendar incomingCal = Calendar.getInstance(); //move this businessey out of here?
            incomingCal.setTime(incomingDateParam);
            Event incomingDate = new Event(incomingCal, incomingCal, newShipment.getName());
            System.out.println(incomingDate);

            //find the contact in the repo and set it for the shipment

            System.out.println("incoming date"+newShipment.getIncomingDate().getStartDate());
            System.out.println("contact"+newShipment.getCarrier().toString());

            newShipment.setIncomingDate(incomingDate);
            //TODO - save the new shipment to the repository
            shipmentRepository.save(newShipment);

        }

        //if the new shipment is Outgoing, appropriately set the outgoing date
        if(newShipment.getType() == ShipmentType.OUTGOING){
            System.out.println("outgoing");
            System.out.println(outgoingDateScheduledParam.toString());
            //convert Date object to Calendar Object and create a new Event object?, the event Name will be
            // Shipment Name for now?
            Calendar outgoingCal = Calendar.getInstance(); //move this businessey out of here?
            outgoingCal.setTime(outgoingDateScheduledParam);
            Event outgoingDateScheduled = new Event(outgoingCal, outgoingCal, newShipment.getName());
            System.out.println(outgoingDateScheduled);

            newShipment.setOutgoingDateScheduled(outgoingDateScheduled);
            //save the new shipment to the repository
            shipmentRepository.save(newShipment);

        }



        //bound fields: name, project, type.



        return "supervisor/manageshipments";
    }

//    @GetMapping("/addOutgoing")
//    public String displayAddAnOutgoingShipment(Model model){
//        return "supervisor/newshipment";
//    }

}
