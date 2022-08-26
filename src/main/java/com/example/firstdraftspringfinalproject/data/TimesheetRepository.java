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

    List<Timesheet> findByEmployeeEmployeeIdAndCompletionStatusAndSupervisorApproval(Integer employeeId, Boolean completionStatus, Boolean supervisorApproval);

    Optional<Timesheet> findByEmployeeEmployeeIdAndStartDate(Integer employeeId, GregorianCalendar startDate);

    List<Timesheet> findBySupervisorApprovalAndCompletionStatus(Boolean supervisorApproval, Boolean completionStatus);

    List<Timesheet> findByEmployeeEmployeeIdAndCompletionStatus(Integer employeeId, Boolean completionStatus);

    List<Timesheet> findByCompletionStatus(Boolean completionStatus);

    Optional<Timesheet> findByEmployeeEmployeeIdAndStartDateAndCompletionStatus(Integer employeeId, GregorianCalendar startDate, Boolean completionStatus);



}
