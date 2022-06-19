package com.example.firstdraftspringfinalproject.data;

import com.example.firstdraftspringfinalproject.models.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository <Employee, Integer> {

    Employee findByUsername(String username);
}
