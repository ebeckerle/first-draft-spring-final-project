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
import java.time.Month;
import java.time.ZonedDateTime;
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

        //Finding the start and end of the current month based on today's date
        // TODO? -- move this logic to a Business Class? - interface that does translating between Date/Time/Calendar
        //  classes that both timesheet, event, and shipment classes can inherit such methods from??
        LocalDate todaysDate = LocalDate.now();
        int todaysMonth = todaysDate.getMonth().getValue() - 1;
        int todaysYear = todaysDate.getYear();
        Calendar startOfMonth = Calendar.getInstance();
        startOfMonth.set(todaysYear, todaysMonth, 1);
        LocalDate lastDayOfMonthDate = todaysDate.withDayOfMonth(todaysDate.getMonth().length(todaysDate.isLeapYear()));
        int lastDayOfMonth = lastDayOfMonthDate.getDayOfMonth();
        Calendar endOfMonth = Calendar.getInstance();
        endOfMonth.set(todaysYear, todaysMonth, lastDayOfMonth);

        //populate the calendar with shipment dates
        model.addAttribute("currentMonthEvents", eventRepository.findByCalStartDateBetween(startOfMonth, endOfMonth));
        model.addAttribute("eventTotal",  eventRepository.findByCalStartDateBetween(startOfMonth, endOfMonth).size());

        //TODO - coming from "Add an Incoming Shipment" link will take you to the AddShipment Form with the Type
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
            System.out.println(errors);
            model.addAttribute(new Shipment());
            //TODO - has to be a cleaner way to have an arraylist of my Shipment Types...
            ArrayList<ShipmentType> shipmentTypes = new ArrayList<>();
            shipmentTypes.add(ShipmentType.INCOMING);
            shipmentTypes.add(ShipmentType.OUTGOING);
            model.addAttribute("shipmentTypes", shipmentTypes);
            model.addAttribute("projects", projectRepository.findAll());
            return "supervisor/newshipment";
        }
        System.out.println(newShipment.getType());
        //if the new shipment is Incoming, appropriately set the incoming date
        if(newShipment.getType() == ShipmentType.INCOMING){
            //convert Date object to Calendar Object and create a new Event object?, the event Name will be
            // Shipment Name for now?
            Calendar incomingCal = Calendar.getInstance(); //move this businessey out of here?
            incomingCal.setTime(incomingDateParam);
            Event incomingDate = new Event(incomingCal, incomingCal, newShipment.getName());
            System.out.println(incomingDate);

            newShipment.setIncomingDate(incomingDate);
            System.out.println("incoming date"+newShipment.getIncomingDate().getStartDate());
            System.out.println("contact"+newShipment.getCarrier().toString());
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



        return "supervisor/manageshipments";
    }

//    @GetMapping("/addOutgoing")
//    public String displayAddAnOutgoingShipment(Model model){
//        return "supervisor/newshipment";
//    }

}
