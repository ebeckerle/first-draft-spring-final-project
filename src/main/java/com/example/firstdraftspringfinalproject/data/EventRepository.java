package com.example.firstdraftspringfinalproject.data;

import com.example.firstdraftspringfinalproject.models.DaysOfWeekHoursSet;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository  extends CrudRepository<DaysOfWeekHoursSet, Integer> {
}
