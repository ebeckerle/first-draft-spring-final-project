package com.example.firstdraftspringfinalproject.data;

import com.example.firstdraftspringfinalproject.models.DaysOfWeekHoursSet;
import com.example.firstdraftspringfinalproject.models.Event;
import com.example.firstdraftspringfinalproject.models.Shipment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ShipmentRepository  extends CrudRepository<Shipment, Integer> {

    List<Shipment> findByIncomingDate(Event incomingDate);

//    List<Shipment> findByIncomingDatesMonth(String month){
//
//    };

    List<Shipment> findByInventoriedDate(Event inventoriedDate);

    List<Shipment> findByOutgoingDateScheduled(Event outgoingDateScheduled);

    List<Shipment> findByOutgoingDateActual(Event outgoingDateScheduled);


}
