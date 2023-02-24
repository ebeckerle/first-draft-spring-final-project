package com.example.firstdraftspringfinalproject.models.dao;

import com.example.firstdraftspringfinalproject.data.EventRepository;
import com.example.firstdraftspringfinalproject.data.ShipmentRepository;
import com.example.firstdraftspringfinalproject.models.Event;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class EventsForCalendarDAO {


    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ShipmentRepository shipmentRepository;


    private List<EventDAO> events = new ArrayList<>();

    private HashMap<Integer, String> keyLegend = new HashMap<>();

    private List<String> keyOrLegend = new ArrayList<>();

    public void addEventsOfOneColorCode(List<Event> events, String colorCode){
        System.out.println("before for loope" + this.events.size());
        for (Event event :
                events) {
            EventDAO eventDAO = new EventDAO(event, colorCode);
            this.events.add(eventDAO);
        }
        System.out.println("after for loope" + this.events.size());

    }

    public List<EventDAO> getEvents() {
        return events;
    }

    public HashMap<Integer, String> getKeyLegend() {
        return keyLegend;
    }
}
