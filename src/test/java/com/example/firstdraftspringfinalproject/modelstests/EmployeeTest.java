package com.example.firstdraftspringfinalproject.modelstests;

import com.example.firstdraftspringfinalproject.models.*;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeTest {

    //TODO: move all of these variables for the tests out of this method and some where DRYer???
    Project pIasc = new Project("IASC", "Iowa State Capitol");
    Project pNam = new Project("NAM", "Nelson Atkins Museum");
    WorkType wT101 = new WorkType(101, "Inventory");
    WorkType wT102 = new WorkType(102, "Cut and Process Rough Parts");

    Employee testEmployee = new Employee("Testey", "McTesterson", "The Testiest", 23, 80, "LgI6M");
    GregorianCalendar date1 = new GregorianCalendar(2022, Calendar.FEBRUARY, 6);
    GregorianCalendar date2 = new GregorianCalendar(2022, Calendar.MARCH, 7);
    GregorianCalendar date3 = new GregorianCalendar(2022, Calendar.NOVEMBER, 7);

    Timesheet testTimesheet1 = new Timesheet(testEmployee, date1);
    Timesheet testTimesheet2 = new Timesheet(testEmployee, date2);
    Timesheet testTimesheet3 = new Timesheet(testEmployee, date3);

    ProjectWorkTypeSet pWT1 = new ProjectWorkTypeSet(pIasc, wT101);
    ProjectWorkTypeSet pWT2 = new ProjectWorkTypeSet(pIasc, wT102);
    ProjectWorkTypeSet pWT3 = new ProjectWorkTypeSet(pNam, wT102);
    DaysOfWeekHoursSet dWkHr1 = new DaysOfWeekHoursSet(0, 0, 4,0,0,0);
    DaysOfWeekHoursSet dWkHr2 = new DaysOfWeekHoursSet(0,0,0,0,4,0);
    DaysOfWeekHoursSet dWkHr3 = new DaysOfWeekHoursSet(1,1,3,0,4,0);

    LineEntry lineEntry1 = new LineEntry(pWT1, dWkHr1, testTimesheet1);
    LineEntry lineEntry2 = new LineEntry(pWT1, dWkHr2, testTimesheet1);
    LineEntry lineEntry3 = new LineEntry(pWT2, dWkHr3, testTimesheet1);
    LineEntry lineEntry4 = new LineEntry(pWT3, dWkHr3, testTimesheet1);


    @Test
    public void testGetCurrentTimesheet(){

        testTimesheet1.setSupervisorApproval(true);
        testTimesheet1.setCompletionStatus(true);
        testTimesheet2.setCompletionStatus(true);
        testTimesheet3.setCompletionStatus(false);

        testEmployee.getTimesheets().add(testTimesheet1);
        testEmployee.getTimesheets().add(testTimesheet2);
        testEmployee.getTimesheets().add(testTimesheet3);

        assertEquals(testTimesheet3, testEmployee.getCurrentTimesheet());

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

    }



    @Test
    public void testGetTotalHoursWorkedToDate(){

        testTimesheet1.getLineEntries().add(lineEntry1);
        testTimesheet1.getLineEntries().add(lineEntry2);
        testTimesheet1.getLineEntries().add(lineEntry3);
        testTimesheet1.setTotalHours();
        testTimesheet1.setCompletionStatus(true);
        testTimesheet1.setSupervisorApproval(true);
        System.out.println(testTimesheet1.getTotalHours());

        testTimesheet2.getLineEntries().add(lineEntry4);
        testTimesheet2.setTotalHours();
        testTimesheet2.setCompletionStatus(true);
        testTimesheet2.setSupervisorApproval(true);

        testTimesheet3.setCompletionStatus(false);
        testTimesheet3.setSupervisorApproval(false);

        testEmployee.getTimesheets().add(testTimesheet1);
        testEmployee.getTimesheets().add(testTimesheet2);
        testEmployee.getTimesheets().add(testTimesheet3);

        assertEquals(26, testEmployee.getTotalHoursWorkedToDate());

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

}
