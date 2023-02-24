package com.example.firstdraftspringfinalproject.models.dao;

import com.example.firstdraftspringfinalproject.data.EventRepository;
import com.example.firstdraftspringfinalproject.data.ShipmentRepository;
import com.example.firstdraftspringfinalproject.models.Event;
import com.example.firstdraftspringfinalproject.models.Shipment;
import com.example.firstdraftspringfinalproject.models.enums.ShipmentType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ShipmentDAO {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ShipmentRepository shipmentRepository;

    public static List<Event> createAListOfCurrentMonthShipmentsEvents(Calendar startDate, Calendar endDate){
        List<Shipment> currentMonthShipmentsWithAnIncomingDate = this.shipmentRepository.findShipmentsWithAIncomingDateWithInDateRange(startDate, endDate);
        List<Shipment> currentMonthShipmentsWithAnOutgoingDateScheduled = this.shipmentRepository.findShipmentsWithAnOutgoingDateScheduledWithInDateRange(startDate, endDate);

        List<Event> currentMonthShipmentIncomingEvents = new ArrayList<>();
        List<Event> currentMonthShipmentOutgoingEvents = new ArrayList<>();
        for(Shipment shipment : currentMonthShipmentsWithAnIncomingDate){
            if(shipment.getType() == ShipmentType.INCOMING){
                System.out.println("incoming");
                if(eventRepository.findById(shipment.getIncomingDate().getId()).isPresent()){
                    Event event = eventRepository.findById(shipment.getIncomingDate().getId()).get();
                    System.out.println(event.getName());
                    currentMonthShipmentIncomingEvents.add(event);
                }
            }
            if(shipment.getType() == ShipmentType.OUTGOING){
                System.out.println("outgoing");
                if(eventRepository.findById(shipment.getOutgoingDateScheduled().getId()).isPresent()){
                    Event event = eventRepository.findById(shipment.getOutgoingDateScheduled().getId()).get();
                    currentMonthShipmentOutgoingEvents.add(event);
                }
            }
        }
        for(Shipment shipment : currentMonthShipmentsWithAnOutgoingDateScheduled){
            if(shipment.getType() == ShipmentType.INCOMING){
                System.out.println("incoming");
                if(eventRepository.findById(shipment.getIncomingDate().getId()).isPresent()){
                    Event event = eventRepository.findById(shipment.getIncomingDate().getId()).get();
                    System.out.println(event.getName());
                    currentMonthShipmentIncomingEvents.add(event);
                }
            }
            if(shipment.getType() == ShipmentType.OUTGOING){
                System.out.println("outgoing");
                if(eventRepository.findById(shipment.getOutgoingDateScheduled().getId()).isPresent()){
                    Event event = eventRepository.findById(shipment.getOutgoingDateScheduled().getId()).get();
                    currentMonthShipmentOutgoingEvents.add(event);
                }
            }
        }
        System.out.println("current month shipment outgoing events"+currentMonthShipmentOutgoingEvents.size());
        EventsForCalendarDAO listOfEvents = new EventsForCalendarDAO();
        listOfEvents.addEventsOfOneColorCode(currentMonthShipmentIncomingEvents, "1");
        listOfEvents.addEventsOfOneColorCode(currentMonthShipmentOutgoingEvents, "2");
    }

}
