package com.example.firstdraftspringfinalproject.data;

import com.example.firstdraftspringfinalproject.models.Employee;
import com.example.firstdraftspringfinalproject.models.Timesheet;
import org.springframework.data.repository.CrudRepository;

public interface TimesheetRepository extends CrudRepository <Timesheet, Integer> {

    static Timesheet findCurrentTimesheetForEmployee(Employee employee){
        Timesheet errorTimesheet = new Timesheet(new Employee("Error", "Error"));
        for (Timesheet timesheet:
                employee.getTimesheets()) {
            if (!timesheet.getCompletionStatus()){
                return timesheet;
            }
        }
        return errorTimesheet;
    }

    Timesheet findByEmployeeIdAndFindByCompletionStatusIsActiveFalse(Integer employeeId);
}
