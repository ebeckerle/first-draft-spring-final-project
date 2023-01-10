package com.example.firstdraftspringfinalproject.data;

import com.example.firstdraftspringfinalproject.models.DaysOfWeekHoursSet;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository  extends CrudRepository<DaysOfWeekHoursSet, Integer> {
}