package com.example.firstdraftspringfinalproject.data;

import org.springframework.data.repository.CrudRepository;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.Event;

import java.util.Calendar;
import java.util.List;

public interface EventRepository  extends CrudRepository<Event, Integer> {

    List<Event> findByCalStartDate(Calendar calStartDate);
    List<Event> findByCalEndDate(Calendar calEndDate);

    List<Event> findByCalStartDateAndCalEndDate(Calendar calStartDate, Calendar calEndDate);

    List<Event> findByCalStartDateBetween(Calendar firstMonthDate, Calendar lastMonthDate);



}
