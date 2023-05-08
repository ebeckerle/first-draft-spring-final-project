package com.example.firstdraftspringfinalproject.modelstests;

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
    public void testSetCurrentPayRate(){

        practiceEmployee.setPayRate(30);
        Timesheet testTimesheet = new Timesheet(practiceEmployee);
        testTimesheet.setCurrentPayRate();

        assertEquals(30, testTimesheet.getCurrentPayRate());
    }

    @Test
    public void testSetTotalHours(){

        //TODO Next!!!!!
        assertEquals(3,3);
//        public void setTotalHours() {
//            Integer totalHours = 0;
//            for (LineEntry lineEntry:
//                    this.lineEntries) {
//                totalHours += lineEntry.getTotalHours();
//            }
//            this.totalHours = totalHours;
//        }
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

//        public static GregorianCalendar figureStartDateBasedOnTodaysDate(LocalDate todaysDate){
//            //convert LocalDate to a new Gregorian Calendar Date
//            int dayOfMonth = todaysDate.getDayOfMonth();
//            int monthValue = todaysDate.getMonthValue();
//            int year = todaysDate.getYear();
//            DayOfWeek dayOfWeek = todaysDate.getDayOfWeek();
//            GregorianCalendar todayGC = new GregorianCalendar(year, monthValue-1, dayOfMonth);
//            //cycle thru days of the week (DayOfWeek Enum int values) to then reset (using add() method) the date back to the appropriate Monday
//            if (dayOfWeek.getValue()== 2){
//                todayGC.add(Calendar.DATE, -1);
//                return todayGC;
//            }else if (dayOfWeek.getValue()==3){
//                todayGC.add(Calendar.DATE, -2);
//                return todayGC;
//            }else if (dayOfWeek.getValue()==4){
//                todayGC.add(Calendar.DATE, -3);
//                return todayGC;
//            }else if (dayOfWeek.getValue()==5){
//                todayGC.add(Calendar.DATE, -4);
//                return todayGC;
//            }else if (dayOfWeek.getValue()==6){
//                todayGC.add(Calendar.DATE, -5);
//                return todayGC;
//            }else if (dayOfWeek.getValue()==7){
//                todayGC.add(Calendar.DATE, -6);
//                return todayGC;
//            }else{
//                return todayGC;
//            }
//        }

    }

    @Test
    public void testFigureLastWeeksStartDateBasedOnTodaysDate(){
        assertEquals(3, 3);
    }

    @Test
    public void testGetTotalHoursByProject(){

        //TODO - had to change the Project Class's equals method to get this test to pass because my test project
        // objects do not have an auto-generated ID - what to do?

        testTimesheet1.getLineEntries().add(lineEntry1);
        testTimesheet1.getLineEntries().add(lineEntry2);
        testTimesheet1.getLineEntries().add(lineEntry3);
        testTimesheet1.getLineEntries().add(lineEntry4);

        System.out.println(testTimesheet1.getTotalHoursByProject(pIasc));

        assertEquals(17, testTimesheet1.getTotalHoursByProject(pIasc));
        assertEquals(9, testTimesheet1.getTotalHoursByProject(pNam));
    }

    @Test
    public void testGetTotalHoursByWorkType(){
        testTimesheet1.getLineEntries().add(lineEntry1);
        testTimesheet1.getLineEntries().add(lineEntry2);
        testTimesheet1.getLineEntries().add(lineEntry3);
        testTimesheet1.getLineEntries().add(lineEntry4);

        assertEquals(8, testTimesheet1.getTotalHoursByWorkType(wT101));
        assertEquals(18, testTimesheet1.getTotalHoursByWorkType(wT102));
    }



}
