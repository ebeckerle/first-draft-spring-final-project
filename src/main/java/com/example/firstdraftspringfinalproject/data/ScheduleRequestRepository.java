package com.example.firstdraftspringfinalproject.data;

import com.example.firstdraftspringfinalproject.models.domainentityclasses.requests.ScheduleRequest;
import org.springframework.data.repository.CrudRepository;

public interface ScheduleRequestRepository extends CrudRepository <ScheduleRequest, Integer> {
}
