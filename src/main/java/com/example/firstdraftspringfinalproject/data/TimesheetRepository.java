package com.example.firstdraftspringfinalproject.data;

import com.example.firstdraftspringfinalproject.models.Employee;
import com.example.firstdraftspringfinalproject.models.Timesheet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

public interface TimesheetRepository extends CrudRepository <Timesheet, Integer> {


    List<Timesheet> findByEmployeeEmployeeId(Integer employeeId);

    Timesheet findByEmployeeEmployeeIdAndCompletionStatus(Integer employeeId, Boolean completionStatus);

    Optional<Timesheet> findByEmployeeEmployeeIdAndStartDate(Integer employeeId, GregorianCalendar startDate);


}
