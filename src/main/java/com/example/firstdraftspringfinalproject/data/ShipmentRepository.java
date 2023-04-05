package com.example.firstdraftspringfinalproject.data;

import com.example.firstdraftspringfinalproject.models.DaysOfWeekHoursSet;
import com.example.firstdraftspringfinalproject.models.Event;
import com.example.firstdraftspringfinalproject.models.Shipment;
import com.example.firstdraftspringfinalproject.models.enums.ShipmentType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

public interface ShipmentRepository  extends CrudRepository<Shipment, Integer> {

    List<Shipment> findByIncomingDate(Event incomingDate);

    List<Shipment> findByInventoriedDate(Event inventoriedDate);

    List<Shipment> findByOutgoingDateScheduled(Event outgoingDateScheduled);

    List<Shipment> findByOutgoingDateActual(Event outgoingDateScheduled);

    //TODO TODODODODODODODO!!!!! FIX THIS SELECT ALLLLL!!!! AND MAYBE ADD 'IF EXISTS'....
    // Following on the list of the MySQL best practices, do not just blindly use SELECT * in the code.
    // If the table has many columns, all will be returned. This will slow down the response time,
    // especially if you send the result to a front-end application. Instead, explicitly type out the
    // column names which are actually needed. For checking the existence of data, use EXISTS clause
    // which is faster in response time. For example, use:If EXISTS(SELECT * from Table WHERE col=’some value’)

    @Query(value = "SELECT * FROM shipment INNER JOIN event ON shipment.incoming_date_id=event.id WHERE cal_start_date BETWEEN :startRange AND :endRange",
            nativeQuery = true)
    List<Shipment> findShipmentsWithAIncomingDateWithInDateRange(@Param("startRange")Calendar startOfDateRange,
                                                @Param("endRange")Calendar endOfDateRange);

    @Query(value = "SELECT * FROM shipment INNER JOIN event ON shipment.outgoing_date_scheduled_id=event.id WHERE cal_start_date BETWEEN :startRange AND :endRange",
            nativeQuery = true)
    List<Shipment> findShipmentsWithAnOutgoingDateScheduledWithInDateRange(@Param("startRange")Calendar startOfDateRange,
                                                                 @Param("endRange")Calendar endOfDateRange);

    List<Shipment> findByType(ShipmentType shipmentType);

    Optional<Shipment> findByTypeAndIncomingDateId(ShipmentType shipmentType, Integer incomingDateId);

    Optional<Shipment> findByTypeAndOutgoingDateScheduledId(ShipmentType shipmentType, Integer outgoingDateScheduledId);

    //TODO - query that will give us only UPCOMING incoming shipment events
    //TODO - query that will give us only UPCOMING outgoing shipment events

}
