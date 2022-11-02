package com.example.firstdraftspringfinalproject.modelstests;

import com.example.firstdraftspringfinalproject.models.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimesheetTest {


    Employee practiceEmployee = new Employee("Elizabeth", "Beckerle");
    Project pIasc = new Project("IASC", "Iowa State Capitol");
    WorkType wT101 = new WorkType(101, "Inventory");
    WorkType wT102 = new WorkType(102, "Cut and Process Rough Parts");


    @Test
    public void testSetDates(){

        Timesheet testTimesheet = new Timesheet(practiceEmployee);

        GregorianCalendar startDateGC = new GregorianCalendar(2022, Calendar.OCTOBER, 31);
        testTimesheet.setDates(startDateGC);

        GregorianCalendar expectedDueDate = new GregorianCalendar(2022, Calendar.NOVEMBER, 7);
        GregorianCalendar expectedPayDay = new GregorianCalendar(2022, Calendar.NOVEMBER, 11);

        assertEquals(expectedDueDate, testTimesheet.getDueDate());
        assertEquals(expectedPayDay, testTimesheet.getPayDay());

//        public void setDates(GregorianCalendar startDate) {
//
//            this.startDate = startDate;
//            int yearOfStart = startDate.get(Calendar.YEAR);
//            int monthOfStart = startDate.get(Calendar.MONTH);
//            int dateOfStart = startDate.get(Calendar.DATE);
//
//            GregorianCalendar dueDate = new GregorianCalendar(yearOfStart, monthOfStart, dateOfStart);
//            dueDate.add(Calendar.DATE, 7);
//            this.dueDate = dueDate;
//
//            GregorianCalendar payDay = new GregorianCalendar(yearOfStart, monthOfStart, dateOfStart);
//            payDay.add(Calendar.DATE, 11);
//            this.payDay = payDay;
//        }
    }

    @Test
    public void testCheckALineEntryAlreadyExists(){
        assertEquals(3, 3);
    }

    @Test
    public void testSetCurrentPayRate(){
        assertEquals(3, 3);
    }

    @Test
    public void testCheckALineEntry(){
        assertEquals(3, 3);
    }

    @Test
    public void testGetLineEntryWithMatchingProjectWorkType(){
        assertEquals(3, 3);
    }

    @Test
    public void testTotalDayOfWeekHours(){

    }

    @Test
    public void testFormatDates(){
        assertEquals(3, 3);
    }

    @Test
    public void testFigureStartDateBasedOnTodaysDate(){
        assertEquals(3, 3);
    }

    @Test
    public void testFigureLastWeeksStartDateBasedOnTodaysDate(){
        assertEquals(3, 3);
    }

    @Test
    public void testGetTotalHoursByProject(){
        assertEquals(3, 3);
    }

    @Test
    public void testGetTotalHoursByWorkType(){
        assertEquals(3, 3);
    }



}
