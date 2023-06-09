package com.example.firstdraftspringfinalproject.unittests;

import com.example.firstdraftspringfinalproject.models.domainentityclasses.*;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets.LineEntry;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets.Timesheet;
import com.example.firstdraftspringfinalproject.models.enums.DaysOfWeek;
import com.example.firstdraftspringfinalproject.models.interfaces.TimesheetCalculateDates;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TimesheetTest {


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
    public void testSetDates(){

        Timesheet testTimesheet = new Timesheet(practiceEmployee);

        GregorianCalendar startDateGC = new GregorianCalendar(2022, Calendar.OCTOBER, 31);
        testTimesheet.setDates(startDateGC);

        GregorianCalendar expectedDueDate = new GregorianCalendar(2022, Calendar.NOVEMBER, 7);
        GregorianCalendar expectedPayDay = new GregorianCalendar(2022, Calendar.NOVEMBER, 11);

        assertEquals(expectedDueDate, testTimesheet.getDueDate());
        assertEquals(expectedPayDay, testTimesheet.getPayDay());
    }

    @Test
    public void testSetCompletionStatus(){
        testTimesheet1.setCompletionStatus(false);
        assertFalse(testTimesheet1.getSupervisorApproval());
    }

    @Test
    public void testUpdateEachDayOfWeekTotalHours(){
        testTimesheet1.getLineEntries().add(lineEntry1);
        testTimesheet1.getLineEntries().add(lineEntry2);
        testTimesheet1.getLineEntries().add(lineEntry3);
        testTimesheet1.getLineEntries().add(lineEntry4);
        testTimesheet1.updateEachDayOfWeekTotalHours();
        LineEntry lineEntry5 = new LineEntry(pNam, wT102, DaysOfWeek.FRIDAY, 1, testTimesheet1);
        LineEntry existingLineEntry = testTimesheet1.findMatchingLineEntry(lineEntry5);
        assertEquals(8, testTimesheet1.getTotalFridayHours());

        testTimesheet1.updateLineEntry(existingLineEntry, lineEntry5);

        testTimesheet1.updateEachDayOfWeekTotalHours();

        assertEquals(9, testTimesheet1.getTotalFridayHours());

        LineEntry lineEntry6 = new LineEntry(pNam, wT102, DaysOfWeek.MONDAY, 2, testTimesheet1);


    }

    @Test
    public void testSetTotalHours(){
        testTimesheet1.getLineEntries().add(lineEntry1);
        testTimesheet1.getLineEntries().add(lineEntry2);
        testTimesheet1.getLineEntries().add(lineEntry3);
        testTimesheet1.getLineEntries().add(lineEntry4);
        testTimesheet1.setTotalHours();
        assertEquals(25, testTimesheet1.getTotalHours());

    }
    @Test
    public void testSetCurrentPayRate(){

        practiceEmployee.setPayRate(30);
        Timesheet testTimesheet = new Timesheet(practiceEmployee);
        testTimesheet.setCurrentPayRate();

        assertEquals(30, testTimesheet.getCurrentPayRate());
    }

    @Test
    public void testTotalDayOfWeekHours(){
        lineEntry1.setTuesdayHours(3);
        testTimesheet1.getLineEntries().add(lineEntry1);
        testTimesheet1.getLineEntries().add(lineEntry2);
        testTimesheet1.getLineEntries().add(lineEntry3);
        testTimesheet1.getLineEntries().add(lineEntry4);

        assertEquals(5, testTimesheet1.totalDayOfWeekHours(DaysOfWeek.MONDAY));
        assertEquals(8, testTimesheet1.totalDayOfWeekHours(DaysOfWeek.TUESDAY));
        assertEquals(7, testTimesheet1.totalDayOfWeekHours(DaysOfWeek.WEDNESDAY));
        assertEquals(0, testTimesheet1.totalDayOfWeekHours(DaysOfWeek.THURSDAY));
        assertEquals(8, testTimesheet1.totalDayOfWeekHours(DaysOfWeek.FRIDAY));
        assertEquals(0, testTimesheet1.totalDayOfWeekHours(DaysOfWeek.SATURDAY));
    }
    @Test
    public void testGetTotalHoursByProject(){
        testTimesheet1.getLineEntries().add(lineEntry1);
        testTimesheet1.getLineEntries().add(lineEntry2);
        testTimesheet1.getLineEntries().add(lineEntry3);
        testTimesheet1.getLineEntries().add(lineEntry4);

        System.out.println(testTimesheet1.getTotalHoursByProject(pIasc));

        assertEquals(10, testTimesheet1.getTotalHoursByProject(pIasc));
        assertEquals(15, testTimesheet1.getTotalHoursByProject(pNam));
    }

    @Test
    public void testGetTotalHoursByWorkType(){
        testTimesheet1.getLineEntries().add(lineEntry1);
        testTimesheet1.getLineEntries().add(lineEntry2);
        testTimesheet1.getLineEntries().add(lineEntry3);
        testTimesheet1.getLineEntries().add(lineEntry4);

        assertEquals(12, testTimesheet1.getTotalHoursByWorkType(wT101));
        assertEquals(13, testTimesheet1.getTotalHoursByWorkType(wT102));
    }

    @Test
    public void testFindMatchingLineEntry(){
        testTimesheet1.getLineEntries().add(lineEntry4);
        LineEntry lineEntry5 = new LineEntry(pNam, wT102, DaysOfWeek.FRIDAY, 1, testTimesheet1);
        assertEquals(lineEntry4, testTimesheet1.findMatchingLineEntry(lineEntry5));
    }

    @Test
    public void testUpdateLineEntry(){
        testTimesheet1.getLineEntries().add(lineEntry1);
        testTimesheet1.getLineEntries().add(lineEntry2);
        testTimesheet1.getLineEntries().add(lineEntry3);
        testTimesheet1.getLineEntries().add(lineEntry4);
        LineEntry lineEntry5 = new LineEntry(pNam, wT102, DaysOfWeek.FRIDAY, 1, testTimesheet1);
        LineEntry existingLineEntry = testTimesheet1.findMatchingLineEntry(lineEntry5);
        testTimesheet1.updateLineEntry(existingLineEntry, lineEntry5);
        assertEquals(4, testTimesheet1.getLineEntries().size());
        testTimesheet1.updateEachDayOfWeekTotalHours();
        assertEquals(9, testTimesheet1.getTotalFridayHours());
    }

    //TODO : testUpdateLineEntry - does it update the total hours of the line entry

    @Test
    public void testReplaceLineEntry(){
        testTimesheet1.getLineEntries().add(lineEntry1);
        testTimesheet1.getLineEntries().add(lineEntry2);
        testTimesheet1.getLineEntries().add(lineEntry3);
        testTimesheet1.getLineEntries().add(lineEntry4);
        LineEntry lineEntry5 = new LineEntry(pNam, wT102, DaysOfWeek.FRIDAY, 1, testTimesheet1);
        LineEntry existingLineEntry = testTimesheet1.findMatchingLineEntry(lineEntry5);
        testTimesheet1.replaceLineEntry(existingLineEntry, lineEntry5);
        assertEquals(4, testTimesheet1.getLineEntries().size());
        testTimesheet1.updateEachDayOfWeekTotalHours();
        assertEquals(1, testTimesheet1.getTotalFridayHours());
    }

    //TODO : testReplaceLineEntry - does it update the total hours of the line entry

    @Test
    public void testFormatDates(){
        GregorianCalendar date = new GregorianCalendar(2022, 2, 4);
        String expected = TimesheetCalculateDates.formatDates(date);
        System.out.println(expected);
        assertEquals(expected, "2/4/2022");
    }

    @Test
    public void testFigureStartDateBasedOnTodaysDate(){

        LocalDate today = LocalDate.now();
        GregorianCalendar startDateExpected = TimesheetCalculateDates.figureStartDateBasedOnTodaysDate(today);
        GregorianCalendar startDateActual = TimesheetCalculateDates.figureStartDateBasedOnTodaysDate(today);

        System.out.println(TimesheetCalculateDates.formatDates(startDateExpected));

        //TODO : wrong ?????
        assertEquals(startDateExpected.getClass(), startDateActual.getClass());
        assertEquals(startDateExpected, startDateActual);

    }

    @Test
    public void testFigureLastWeeksStartDateBasedOnTodaysDate(){
        assertEquals(3, 3);
    }




}
