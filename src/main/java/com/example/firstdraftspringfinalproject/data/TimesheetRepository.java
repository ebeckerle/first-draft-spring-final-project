package com.example.firstdraftspringfinalproject.data;

import com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets.Timesheet;
import org.springframework.data.repository.CrudRepository;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

public interface TimesheetRepository extends CrudRepository <Timesheet, Integer> {


    List<Timesheet> findByEmployeeId(Integer employeeId);

    List<Timesheet> findByEmployeeIdAndCompletionStatusAndSupervisorApproval(Integer employeeId, Boolean completionStatus, Boolean supervisorApproval);

    Optional<Timesheet> findByEmployeeIdAndStartDate(Integer employeeId, GregorianCalendar startDate);

    List<Timesheet> findBySupervisorApprovalAndCompletionStatus(Boolean supervisorApproval, Boolean completionStatus);

    List<Timesheet> findByEmployeeIdAndCompletionStatus(Integer employeeId, Boolean completionStatus);

    List<Timesheet> findByCompletionStatus(Boolean completionStatus);

    Optional<Timesheet> findByEmployeeIdAndStartDateAndCompletionStatus(Integer employeeId, GregorianCalendar startDate, Boolean completionStatus);



}
