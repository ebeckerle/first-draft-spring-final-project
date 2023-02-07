package com.example.firstdraftspringfinalproject.data;

import com.example.firstdraftspringfinalproject.models.DaysOfWeekHoursSet;
import com.example.firstdraftspringfinalproject.models.Shipment;
import org.springframework.data.repository.CrudRepository;

public interface ShipmentRepository  extends CrudRepository<Shipment, Integer> {
}
