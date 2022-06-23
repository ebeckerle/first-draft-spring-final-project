package com.example.firstdraftspringfinalproject.data;

import com.example.firstdraftspringfinalproject.models.WorkType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface WorkTypeRepository extends CrudRepository<WorkType, Integer> {

    WorkType findByWorkTypeId(Integer workTypeId);
}
