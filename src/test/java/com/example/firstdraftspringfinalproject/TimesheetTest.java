package com.example.firstdraftspringfinalproject;

import com.example.firstdraftspringfinalproject.models.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimesheetTest {


        @Test
    public void testCheckALineEntry(){
//         boolean checkALineEntry(LineEntry newEntry)

        Employee practiceEmployee = new Employee("Elizabeth", "Beckerle");
        Timesheet testTimesheet = new Timesheet(practiceEmployee);
        Timesheet testTimesheet2 = new Timesheet(practiceEmployee);

        Project pIasc = new Project("IASC", "Iowa State Capitol");
        WorkType wT101 = new WorkType(101, "Inventory");


        WorkType wT102 = new WorkType(102, "Cut and Process Rough Parts");

        assertEquals(3, 3);
    }

//    @Test
//    public void testCheckAndAddALineEntry(){
////        checkAndAddALineEntry(LineEntry newEntry, String dayOfWeek, Integer hours)
//        //check that the size of the two array lists are equal
//        Employee practiceEmployee = new Employee("Elizabeth", "Beckerle");
//        Timesheet testTimesheet = new Timesheet(practiceEmployee);
//        Timesheet testTimesheet2 = new Timesheet(practiceEmployee);
//
//        Project pIasc = new Project("IASC", "Iowa State Capitol");
//        WorkType wT101 = new WorkType(101, "Inventory");
//        HashMap<String, Integer> dayOfWeekAndHours = new HashMap<>();
//        dayOfWeekAndHours.put("MONDAY", 4);
//        dayOfWeekAndHours.put("TUESDAY", 4);
//        dayOfWeekAndHours.put("WEDNESDAY", 4);
//        LineEntry lineEntry1 = new LineEntry(pIasc, wT101, dayOfWeekAndHours);
//
//        WorkType wT102 = new WorkType(102, "Cut and Process Rough Parts");
//        LineEntry lineEntry2 = new LineEntry(pIasc, wT102, dayOfWeekAndHours);
//
//        HashMap<String, Integer> dayOfWeekAndHours2 = new HashMap<>();
//        dayOfWeekAndHours2.put("FRIDAY", 8);
//        LineEntry lineEntry3 = new LineEntry(pIasc, wT102, dayOfWeekAndHours2);
//
//        ArrayList<LineEntry> expected = new ArrayList<>();
//        expected.add(lineEntry1);
//        expected.add(lineEntry2);
//        testTimesheet.setLineEntries(expected);
//
//        ArrayList<LineEntry> actual = new ArrayList<>();
//        actual.add(lineEntry1);
//        actual.add(lineEntry2);
//        testTimesheet2.setLineEntries(actual);
//        testTimesheet2.checkAndAddALineEntry(lineEntry3, "FRIDAY", 8);
//
//        assertEquals(testTimesheet.getLineEntries().size(), testTimesheet2.getLineEntries().size());
//    }
//
//    @Test
//    public void testCheckAndAddALineEntry2(){
////        checkAndAddALineEntry(LineEntry newEntry, String dayOfWeek, Integer hours)
//        //check that the size of the two array lists are equal
//        Employee practiceEmployee = new Employee("Elizabeth", "Beckerle");
//        Timesheet testTimesheet = new Timesheet(practiceEmployee);
//        Timesheet testTimesheet2 = new Timesheet(practiceEmployee);
//
//        Project pIasc = new Project("IASC", "Iowa State Capitol");
//        WorkType wT101 = new WorkType(101, "Inventory");
//        HashMap<String, Integer> dayOfWeekAndHours = new HashMap<>();
//        dayOfWeekAndHours.put("MONDAY", 4);
//        LineEntry lineEntry1 = new LineEntry(pIasc, wT101, dayOfWeekAndHours);
//
//        WorkType wT102 = new WorkType(102, "Cut and Process Rough Parts");
//        LineEntry lineEntry2 = new LineEntry(pIasc, wT102, dayOfWeekAndHours);
//
//        HashMap<String, Integer> dayOfWeekAndHours2 = new HashMap<>();
//        dayOfWeekAndHours2.put("FRIDAY", 8);
//        LineEntry lineEntry3 = new LineEntry(pIasc, wT102, dayOfWeekAndHours2);
//
//        testTimesheet.checkAndAddALineEntry(lineEntry1, "MONDAY", 4);
//        testTimesheet.checkAndAddALineEntry(lineEntry2, "MONDAY", 4);
//
//        testTimesheet2.checkAndAddALineEntry(lineEntry1, "MONDAY", 4);
//        testTimesheet2.checkAndAddALineEntry(lineEntry2, "MONDAY", 4);
//        testTimesheet2.checkAndAddALineEntry(lineEntry3, "FRIDAY", 8);
//
//        assertEquals(testTimesheet.getLineEntries().size(), testTimesheet2.getLineEntries().size());
//    }
//
//    @Test
//    public void testTotalDayOfWeekHours(){
//        Employee practiceEmployee = new Employee("Elizabeth", "Beckerle");
//        Timesheet testTimesheet = new Timesheet(practiceEmployee);
//
//        Project pIasc = new Project("IASC", "Iowa State Capitol");
//        WorkType wT101 = new WorkType(101, "Inventory");
//        HashMap<String, Integer> dayOfWeekAndHours = new HashMap<>();
//        dayOfWeekAndHours.put("MONDAY", 4);
//        LineEntry lineEntry1 = new LineEntry(pIasc, wT101, dayOfWeekAndHours);
//
//        WorkType wT102 = new WorkType(102, "Cut and Process Rough Parts");
//        LineEntry lineEntry2 = new LineEntry(pIasc, wT102, dayOfWeekAndHours);
//
//        testTimesheet.checkAndAddALineEntry(lineEntry1, "MONDAY", 4);
//        testTimesheet.checkAndAddALineEntry(lineEntry2, "MONDAY", 4);
//
//        assertEquals(8, testTimesheet.totalDayOfWeekHours("MONDAY"));
//
//    }


}
