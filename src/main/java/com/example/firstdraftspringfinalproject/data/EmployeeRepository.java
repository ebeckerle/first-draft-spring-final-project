package com.example.firstdraftspringfinalproject.data;

import com.example.firstdraftspringfinalproject.models.domainentityclasses.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EmployeeRepository extends CrudRepository <Employee, Integer> {

    Employee findByUsername(String username);

    Optional <Employee> findByFirstNameAndLastName(String firstName, String lastName);

    Optional<Employee> findByLastName(String lastName);

    Optional<Employee> findByFirstNameLastNameCombo(String firstNameLastNameCombo);
}
