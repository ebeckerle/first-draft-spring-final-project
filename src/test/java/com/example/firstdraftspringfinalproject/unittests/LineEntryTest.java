package com.example.firstdraftspringfinalproject.unittests;

import com.example.firstdraftspringfinalproject.models.domainentityclasses.*;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets.LineEntry;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets.Timesheet;
import com.example.firstdraftspringfinalproject.models.enums.DaysOfWeek;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;
import static org.testng.AssertJUnit.*;


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


    @Test
    public void testConstructorWithProjectWorkTypeDayOfWeekHoursAndTimesheetParams(){
        LineEntry lineEntry8 = new LineEntry(pNam, wT102, DaysOfWeek.FRIDAY, 7, testTimesheet1);

        Assertions.assertEquals(lineEntry8.getMondayHours().intValue(), 0);
        Assertions.assertEquals(lineEntry8.getTuesdayHours().intValue(), 0);
        Assertions.assertEquals(lineEntry8.getWednesdayHours().intValue(), 0);
        Assertions.assertEquals(lineEntry8.getThursdayHours().intValue(), 0);
        Assertions.assertEquals(lineEntry8.getFridayHours().intValue(), 7);
        Assertions.assertEquals(lineEntry8.getSaturdayHours().intValue(), 0);
        Assertions.assertEquals(lineEntry8.getProject(), pNam);
        Assertions.assertEquals(lineEntry8.getWorkType(), wT102);
    }

    @Test
    public void testConstructorWithProjectWorkTypeDayOfWeekHoursAndTimesheetParamsThrowsExceptionOver24(){
        Exception exception = assertThrows(RuntimeException.class, () -> {
                    LineEntry lineEntry8 = new LineEntry(pNam, wT102, DaysOfWeek.FRIDAY, 25, testTimesheet1);
                });

        String expectedMessage = "can not create a line entry with less than 1 hour or more than 24 hours for one day";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testConstructorWithProjectWorkTypeDayOfWeekHoursAndTimesheetParamsThrowsExceptionUnder1(){
        Exception exception = assertThrows(RuntimeException.class, () -> {
            LineEntry lineEntry8 = new LineEntry(pNam, wT102, DaysOfWeek.FRIDAY, 0, testTimesheet1);
        });

        String expectedMessage = "can not create a line entry with less than 1 hour or more than 24 hours for one day";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testIsLineEntryOnTimesheet() {
        testTimesheet1.getLineEntries().add(lineEntry1);
        testTimesheet1.getLineEntries().add(lineEntry2);
        testTimesheet1.getLineEntries().add(lineEntry3);

        assertTrue(lineEntry1.isLineEntryOnTimesheet(testTimesheet1));
        assertFalse(lineEntry4.isLineEntryOnTimesheet(testTimesheet1));
    }

    @Test
    public void testUpdateTotalHours(){
        lineEntry4.setMondayHours(8);
        lineEntry4.updateTotalHours();

        assertEquals(16, lineEntry4.getTotalHours().intValue());
    }


}
