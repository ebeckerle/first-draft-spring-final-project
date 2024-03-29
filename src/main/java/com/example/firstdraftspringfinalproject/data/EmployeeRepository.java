package com.example.firstdraftspringfinalproject.data;

import com.example.firstdraftspringfinalproject.models.domainentityclasses.Employee;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.Shipment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.*;

public interface EmployeeRepository extends CrudRepository <Employee, Integer> {

    Employee findByUsername(String username);

    Optional <Employee> findByFirstNameAndLastName(String firstName, String lastName);

    Optional<Employee> findByLastName(String lastName);

    Optional<Employee> findByFirstNameLastNameCombo(String firstNameLastNameCombo);

    @Query(value = "SELECT first_name_last_name_combo FROM employee",
            nativeQuery = true)
    List<String> findAllEmployeesFirstNameLastNameCombo();

    @Query(value = "SELECT first_name_last_name_combo, total_hours_worked_to_date FROM employee",
            nativeQuery = true)
    List<String> findAllEmployeesFirstNameLastNameComboAndTotalHoursWorkedToDate();

}
