package com.example.firstdraftspringfinalproject.data;

import com.example.firstdraftspringfinalproject.models.DaysOfWeekHoursSet;
import com.example.firstdraftspringfinalproject.models.Event;
import com.example.firstdraftspringfinalproject.models.Shipment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Calendar;
import java.util.List;

public interface ShipmentRepository  extends CrudRepository<Shipment, Integer> {

    List<Shipment> findByIncomingDate(Event incomingDate);

//    List<Shipment> findByIncomingDatesMonth(String month){
//
//    };

    List<Shipment> findByInventoriedDate(Event inventoriedDate);

    List<Shipment> findByOutgoingDateScheduled(Event outgoingDateScheduled);

    List<Shipment> findByOutgoingDateActual(Event outgoingDateScheduled);

    @Query(value = "SELECT * FROM shipment INNER JOIN event ON shipment.incoming_date_id=event.id WHERE cal_start_date BETWEEN :startRange AND :endRange",
            nativeQuery = true)
    List<Shipment> findShipmentsWithInDateRange(@Param("startRange")Calendar startOfDateRange,
                                                @Param("endRange")Calendar endOfDateRange);


}
