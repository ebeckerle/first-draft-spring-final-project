package com.example.firstdraftspringfinalproject.unittests;

import com.example.firstdraftspringfinalproject.models.domainentityclasses.*;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets.LineEntry;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets.Timesheet;
import com.example.firstdraftspringfinalproject.models.enums.DaysOfWeek;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;
import static org.testng.AssertJUnit.*;


public class LineEntryTest {

    Project testProject1;
    Project testProject2;
    WorkType testWorkType101;
    WorkType testWorkType102;
    Employee testEmployee;

    Timesheet testTimesheet1;

    LineEntry lineEntry1;
    LineEntry lineEntry2;
    LineEntry lineEntry3;
    LineEntry lineEntry4;
    @BeforeEach
    public static void initTestObjects(){
        TestObjects testObjects = TestObjectsBuilder.createTestObjects();
        Project testProject1 = testObjects.getTestProjects().get(0);
        Project testProject2 = testObjects.getTestProjects().get(1);
        WorkType testWorkType101 = testObjects.getTestWorkTypes().get(0);
        WorkType testWorkType102 = testObjects.getTestWorkTypes().get(1);
        System.out.println("before");
        System.out.println(testProject1.toString());

        Employee testEmployee = new Employee("Elizabeth", "Beckerle");

        Timesheet testTimesheet1 = new Timesheet(testEmployee);

        LineEntry lineEntry1 = new LineEntry(testProject1, testWorkType101, DaysOfWeek.MONDAY, 5, testTimesheet1);
        LineEntry lineEntry2 = new LineEntry(testProject1, testWorkType102, DaysOfWeek.TUESDAY, 5,testTimesheet1);
        LineEntry lineEntry3 = new LineEntry(testProject2, testWorkType101, DaysOfWeek.WEDNESDAY, 7, testTimesheet1);
        LineEntry lineEntry4 = new LineEntry(testProject2, testWorkType102, DaysOfWeek.FRIDAY, 8, testTimesheet1);

    }





    @Test
    public void testConstructorWithProjectWorkTypeDayOfWeekHoursAndTimesheetParams(){
        LineEntry lineEntry8 = new LineEntry(testProject2, testWorkType102, DaysOfWeek.FRIDAY, 7, testTimesheet1);

        Assertions.assertEquals(lineEntry8.getMondayHours().intValue(), 0);
        Assertions.assertEquals(lineEntry8.getTuesdayHours().intValue(), 0);
        Assertions.assertEquals(lineEntry8.getWednesdayHours().intValue(), 0);
        Assertions.assertEquals(lineEntry8.getThursdayHours().intValue(), 0);
        Assertions.assertEquals(lineEntry8.getFridayHours().intValue(), 7);
        Assertions.assertEquals(lineEntry8.getSaturdayHours().intValue(), 0);
        Assertions.assertEquals(lineEntry8.getProject(), testProject2);
        Assertions.assertEquals(lineEntry8.getWorkType(), testWorkType102);
    }

    @Test
    public void testConstructorWithProjectWorkTypeDayOfWeekHoursAndTimesheetParamsThrowsExceptionOver24(){
        Exception exception = assertThrows(RuntimeException.class, () -> {
                    LineEntry lineEntry8 = new LineEntry(testProject2, testWorkType102, DaysOfWeek.FRIDAY, 25, testTimesheet1);
                });

        String expectedMessage = "can not create a line entry with less than 1 hour or more than 24 hours for one day";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testConstructorWithProjectWorkTypeDayOfWeekHoursAndTimesheetParamsThrowsExceptionUnder1(){
        Exception exception = assertThrows(RuntimeException.class, () -> {
            LineEntry lineEntry8 = new LineEntry(testProject2, testWorkType102, DaysOfWeek.FRIDAY, 0, testTimesheet1);
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
