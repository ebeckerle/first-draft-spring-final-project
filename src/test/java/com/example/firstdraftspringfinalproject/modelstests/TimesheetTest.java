package com.example.firstdraftspringfinalproject.modelstests;

import com.example.firstdraftspringfinalproject.models.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimesheetTest {


    Employee practiceEmployee = new Employee("Elizabeth", "Beckerle");
    Project pIasc = new Project("IASC", "Iowa State Capitol");
    WorkType wT101 = new WorkType(101, "Inventory");
    WorkType wT102 = new WorkType(102, "Cut and Process Rough Parts");


    @Test
    public void testSetDates(){
        assertEquals(3, 3);
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
