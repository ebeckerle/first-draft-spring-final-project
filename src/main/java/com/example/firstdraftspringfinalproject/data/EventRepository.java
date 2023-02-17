package com.example.firstdraftspringfinalproject.data;

import com.example.firstdraftspringfinalproject.models.Shipment;
import org.springframework.data.repository.CrudRepository;
import com.example.firstdraftspringfinalproject.models.Event;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

public interface EventRepository  extends CrudRepository<Event, Integer> {

    List<Event> findByCalStartDate(Calendar calStartDate);
    List<Event> findByCalEndDate(Calendar calEndDate);

    List<Event> findByCalStartDateAndCalEndDate(Calendar calStartDate, Calendar calEndDate);

    List<Event> findByCalStartDateBetween(Calendar firstMonthDate, Calendar lastMonthDate);

}
