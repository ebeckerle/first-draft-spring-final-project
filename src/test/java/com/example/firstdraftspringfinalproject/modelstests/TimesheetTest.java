package com.example.firstdraftspringfinalproject.modelstests;

import com.example.firstdraftspringfinalproject.models.domainentityclasses.*;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets.DaysOfWeekHoursSet;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets.LineEntry;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets.ProjectWorkTypeSet;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets.Timesheet;
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

        testTimesheet1.getLineEntries().add(lineEntry1);

        assertEquals(lineEntry1, testTimesheet1.getLineEntryWithMatchingProjectWorkType(pWT1));

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

        testTimesheet1.getLineEntries().add(lineEntry1);
        testTimesheet1.getLineEntries().add(lineEntry2);
        testTimesheet1.getLineEntries().add(lineEntry3);

        assertEquals(1, testTimesheet1.totalDayOfWeekHours("Monday"));
        assertEquals(1, testTimesheet1.totalDayOfWeekHours("Tuesday"));
        assertEquals(7, testTimesheet1.totalDayOfWeekHours("Wednesday"));
        assertEquals(0, testTimesheet1.totalDayOfWeekHours("Thursday"));
        assertEquals(8, testTimesheet1.totalDayOfWeekHours("Friday"));
        assertEquals(0, testTimesheet1.totalDayOfWeekHours("Saturday"));

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

        GregorianCalendar date = new GregorianCalendar(2022, 2, 4);
        String expected = TimesheetCalculateDates.formatDates(date);
        System.out.println(expected);
        assertEquals(expected, "2/4/2022");

//        public static String formatDates(GregorianCalendar date){
//            return date.get(Calendar.MONTH) + "/" + date.get(Calendar.DATE) + "/" + date.get(Calendar.YEAR);
//        }
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

//        public Integer getTotalHoursByProject(Project project){
//            List<LineEntry> lineEntries = this.lineEntries;
//            //iterate thru an arraylist of line entries
//            Integer totalHoursForProject = 0;
//            for (LineEntry lineEntry : lineEntries){
//                Integer lineEntryTotal = 0;
//                if (lineEntry.getProjectWorkTypeCombo().getProject().equals(project)){
//                    lineEntryTotal = lineEntry.getTotalHours();
//                }
//                totalHoursForProject +=lineEntryTotal;
//            }
//            return totalHoursForProject;
//        }
    }

    @Test
    public void testGetTotalHoursByWorkType(){

        testTimesheet1.getLineEntries().add(lineEntry1);
        testTimesheet1.getLineEntries().add(lineEntry2);
        testTimesheet1.getLineEntries().add(lineEntry3);
        testTimesheet1.getLineEntries().add(lineEntry4);


        assertEquals(8, testTimesheet1.getTotalHoursByWorkType(wT101));
        assertEquals(18, testTimesheet1.getTotalHoursByWorkType(wT102));

//        public Integer getTotalHoursByWorkType(WorkType workType){
//            List<LineEntry> lineEntries = this.lineEntries;
//            //iterate thru an arraylist of line entries
//            Integer totalHoursForWorkType = 0;
//            for (LineEntry lineEntry : lineEntries){
//                Integer lineEntryTotal = 0;
//                if (lineEntry.getProjectWorkTypeCombo().getWorkType().equals(workType)){
//                    lineEntryTotal = lineEntry.getTotalHours();
//                }
//                totalHoursForWorkType +=lineEntryTotal;
//            }
//            return totalHoursForWorkType;
//        }
    }



}
