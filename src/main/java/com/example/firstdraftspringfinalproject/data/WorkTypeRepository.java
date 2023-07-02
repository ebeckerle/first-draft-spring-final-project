package com.example.firstdraftspringfinalproject.data;

import com.example.firstdraftspringfinalproject.models.domainentityclasses.WorkType;
import org.springframework.data.repository.CrudRepository;

public interface WorkTypeRepository extends CrudRepository<WorkType, Integer> {


    WorkType findByWorkTypeName(String name);
}
