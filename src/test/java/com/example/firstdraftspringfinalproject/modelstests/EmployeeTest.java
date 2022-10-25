package com.example.firstdraftspringfinalproject.modelstests;

import com.example.firstdraftspringfinalproject.models.Employee;
import com.example.firstdraftspringfinalproject.models.Project;
import com.example.firstdraftspringfinalproject.models.Timesheet;
import com.example.firstdraftspringfinalproject.models.WorkType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeTest {

    @Test
    public void testGetCurrentTimesheet(){

        //TODO: move all of these variables for the tests out of this method and some where DRYer
        Employee practiceEmployee = new Employee("Elizabeth", "Beckerle");
        Project pIasc = new Project("IASC", "Iowa State Capitol");
        WorkType wT101 = new WorkType(101, "Inventory");
        WorkType wT102 = new WorkType(102, "Cut and Process Rough Parts");

        Employee testEmployee = new Employee("Testey", "McTesterson", "The Testiest", 23, 80, "LgI6M");
        Timesheet testTimesheet1 = new Timesheet(testEmployee);

        assertEquals(3, 3);
    }

//    public Timesheet getCurrentTimesheet(){
//        Timesheet returnedTimesheet = null;
//        for (Timesheet timesheet:
//                this.timesheets) {
//            if (!timesheet.getCompletionStatus()){
//                returnedTimesheet = timesheet;
//            }
//        }
//        return returnedTimesheet;
//    }

    @Test
    public void testGetTotalHoursWorkedToDate(){
        assertEquals(3, 3);
    }
//    public Integer getTotalHoursWorkedToDate(){
//        List<Timesheet> timesheets = this.timesheets;
//        Integer totalHoursWorkedToDate = 0;
//        for (Timesheet timesheet:
//                timesheets) {
//            if(timesheet.getSupervisorApproval()){
//                totalHoursWorkedToDate += timesheet.getTotalHours();
//            }
//        }
//        return totalHoursWorkedToDate;
//    }
}
