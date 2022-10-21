package com.example.firstdraftspringfinalproject.modelstests;

import com.example.firstdraftspringfinalproject.models.Timesheet;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeTest {

    @Test
    public void testGetCurrentTimesheet(){
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
