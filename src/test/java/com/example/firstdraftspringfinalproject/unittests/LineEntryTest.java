package com.example.firstdraftspringfinalproject.unittests;

import com.example.firstdraftspringfinalproject.models.domainentityclasses.*;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets.LineEntry;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets.Timesheet;
import com.example.firstdraftspringfinalproject.models.enums.DaysOfWeek;
import org.junit.jupiter.api.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;


public class LineEntryTest {

    Employee practiceEmployee = new Employee("Elizabeth", "Beckerle");
    Project pIasc = new Project("IASC", "Iowa State Capitol");
    Project pNam = new Project("NAM", "Nelson Atkins Museum");
    WorkType wT101 = new WorkType(101, "Inventory");
    WorkType wT102 = new WorkType(102, "Cut and Process Rough Parts");

    Timesheet testTimesheet1 = new Timesheet(practiceEmployee);

    LineEntry lineEntry1 = new LineEntry(pIasc, wT101, DaysOfWeek.MONDAY, 5, testTimesheet1);
    LineEntry lineEntry2 = new LineEntry(pIasc, wT102, DaysOfWeek.TUESDAY, 5,testTimesheet1);
    LineEntry lineEntry3 = new LineEntry(pNam, wT101, DaysOfWeek.WEDNESDAY, 7, testTimesheet1);
    LineEntry lineEntry4 = new LineEntry(pNam, wT102, DaysOfWeek.FRIDAY, 8, testTimesheet1);

    //TODO : test the constructor with project, workType, dayOfWeek, hours, and timesheet params
        //TODO : try with zero hours or more than 24;

    @Test
    public void testConstructorWithProjectWorkTypeDayOfWeekHoursAndTimesheetParams(){
//        LineEntry lineEntry = new LineEntry(pNam, wT102, DaysOfWeek.FRIDAY, 25, testTimesheet1);
//        String string = "N";
//        assertThrows("N", string);

//        LineEntry lineEntry;
//        Object LineEntry = new Object();
//        Exception exception = assertThrows(RuntimeException.class, () ->
//                LineEntry lineEntry = new LineEntry(pNam, wT102, DaysOfWeek.FRIDAY, 25, testTimesheet1);
//        assertEquals("can not create a line entry with less than 1 hour or more than 24 hours for one day", exception.getMessage());
    }
        //TODO : does total hours and the day of week match?

    //TODO : test "isLineEntryOnTimesheet" method

    //TODO : test "updateTotalHours" method


}
