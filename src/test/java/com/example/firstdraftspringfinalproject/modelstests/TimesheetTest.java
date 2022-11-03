package com.example.firstdraftspringfinalproject.modelstests;

import com.example.firstdraftspringfinalproject.models.*;
import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class TimesheetTest {


    Employee practiceEmployee = new Employee("Elizabeth", "Beckerle");
    Project pIasc = new Project("IASC", "Iowa State Capitol");
    WorkType wT101 = new WorkType(101, "Inventory");
    WorkType wT102 = new WorkType(102, "Cut and Process Rough Parts");
    Timesheet testTimesheet1 = new Timesheet(practiceEmployee);
    ProjectWorkTypeSet pWT1 = new ProjectWorkTypeSet(pIasc, wT101);
    ProjectWorkTypeSet pWT2 = new ProjectWorkTypeSet(pIasc, wT102);
    DaysOfWeekHoursSet dWkHr1 = new DaysOfWeekHoursSet(0, 0, 4,0,0,0);
    DaysOfWeekHoursSet dWkHr2 = new DaysOfWeekHoursSet(0,0,0,0,4,0);
    DaysOfWeekHoursSet dWkHr3 = new DaysOfWeekHoursSet(1,1,3,0,4,0);

    LineEntry lineEntry1 = new LineEntry(pWT1, dWkHr1, testTimesheet1);
    LineEntry lineEntry2 = new LineEntry(pWT1, dWkHr2, testTimesheet1);
    LineEntry lineEntry3 = new LineEntry(pWT2, dWkHr3, testTimesheet1);


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
    public void testSetCurrentPayRate(){

        practiceEmployee.setPayRate(30);
        Timesheet testTimesheet = new Timesheet(practiceEmployee);
        testTimesheet.setCurrentPayRate();

        assertEquals(30, testTimesheet.getCurrentPayRate());

//        public void setCurrentPayRate() {
//            Employee employee = this.employee;
//            this.currentPayRate = employee.getPayRate();
//        }
    }

    @Test
    public void testCheckALineEntryForFalse(){

        assertFalse(testTimesheet1.checkALineEntry(lineEntry1));

        //        public boolean checkALineEntry(LineEntry newEntry){
//            boolean doesLineEntryAlreadyExist = false;
//            while (!doesLineEntryAlreadyExist) {
//                for (LineEntry entry :
//                        this.lineEntries) {
//                    if (entry.equals(newEntry)) {
//                        doesLineEntryAlreadyExist = true;
//                        break;
//                    }
//                }
//                if (!doesLineEntryAlreadyExist){
//                    break;
//                }
//            }
//            return doesLineEntryAlreadyExist;
//        }
    }

    @Test
    public void testCheckALineEntryForTrue(){

        testTimesheet1.getLineEntries().add(lineEntry1);
        assertTrue(testTimesheet1.checkALineEntry(lineEntry1));
    }

    @Test
    public void testGetLineEntryWithMatchingProjectWorkType(){

//        testTimesheet1.getLineEntries().add(lineEntry1);
//
//        assertEquals(lineEntry1, testTimesheet1.getLineEntryWithMatchingProjectWorkType(pWT1));

//        public LineEntry getLineEntryWithMatchingProjectWorkType(ProjectWorkTypeSet projectWorkTypeSet){
//            for (LineEntry lineEntry:
//                    this.lineEntries) {
//                if (lineEntry.getProjectWorkTypeCombo().equals(projectWorkTypeSet)){
//                    return lineEntry;
//                }
//            }
//            return new LineEntry();
//        }

    }

    @Test
    public void testTotalDayOfWeekHours(){

//        testTimesheet1.getLineEntries().add(lineEntry1);
//        testTimesheet1.getLineEntries().add(lineEntry2);
//        testTimesheet1.getLineEntries().add(lineEntry3);
//
//        assertEquals(1, testTimesheet1.totalDayOfWeekHours("Monday"));
//        assertEquals(1, testTimesheet1.totalDayOfWeekHours("Tuesday"));
//        assertEquals(7, testTimesheet1.totalDayOfWeekHours("Wednesday"));
//        assertEquals(0, testTimesheet1.totalDayOfWeekHours("Thursday"));
//        assertEquals(8, testTimesheet1.totalDayOfWeekHours("Friday"));
//        assertEquals(0, testTimesheet1.totalDayOfWeekHours("Saturday"));

//        public Integer totalDayOfWeekHours(String dayOfWeek){
//            Integer totalHours = 0;
//            if (dayOfWeek.equals("Monday")){
//                for (LineEntry lineEntry:
//                        this.lineEntries) {
//                    totalHours += lineEntry.getDaysOfWeekHoursCombo().getMondayHours();
//                }
//            } else if (dayOfWeek.equals("Tuesday")){
//                for (LineEntry lineEntry:
//                        this.lineEntries) {
//                    totalHours += lineEntry.getDaysOfWeekHoursCombo().getTuesdayHours();
//                }
//            }else if (dayOfWeek.equals("Wednesday")){
//                for (LineEntry lineEntry:
//                        this.lineEntries) {
//                    totalHours += lineEntry.getDaysOfWeekHoursCombo().getWednesdayHours();
//                }
//            }else if (dayOfWeek.equals("Thursday")){
//                for (LineEntry lineEntry:
//                        this.lineEntries) {
//                    totalHours += lineEntry.getDaysOfWeekHoursCombo().getThursdayHours();
//                }
//            }else if (dayOfWeek.equals("Friday")){
//                for (LineEntry lineEntry:
//                        this.lineEntries) {
//                    totalHours += lineEntry.getDaysOfWeekHoursCombo().getFridayHours();
//                }
//            }else if (dayOfWeek.equals("Saturday")){
//                for (LineEntry lineEntry:
//                        this.lineEntries) {
//                    totalHours += lineEntry.getDaysOfWeekHoursCombo().getSaturdayHours();
//                }
//            }
//            return totalHours;
//        }
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
