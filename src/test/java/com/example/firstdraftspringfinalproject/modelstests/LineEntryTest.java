package com.example.firstdraftspringfinalproject.modelstests;

import com.example.firstdraftspringfinalproject.models.domainentityclasses.*;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets.DaysOfWeekHoursSet;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets.LineEntry;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets.ProjectWorkTypeSet;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets.Timesheet;
import org.junit.jupiter.api.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;


public class LineEntryTest {


//    @Test
//    public void testTests(){
//        String string = "N";
//        assertEquals("N", string);
//    }
    Employee practiceEmployee = new Employee("Elizabeth", "Beckerle");
    Timesheet testTimesheet1 = new Timesheet(practiceEmployee);

    Project pIasc = new Project("IASC", "Iowa State Capitol");
    WorkType wT101 = new WorkType(101, "Inventory");

    ProjectWorkTypeSet pWT1 = new ProjectWorkTypeSet(pIasc, wT101);




    @Test
    public void testUpdateALineEntry(){

        DaysOfWeekHoursSet dayWkHrSetTest1 = new DaysOfWeekHoursSet("weDnesday", 2);
        DaysOfWeekHoursSet dayWkHrSetTest2 = new DaysOfWeekHoursSet("Wednesday", 3);
        DaysOfWeekHoursSet daysOfWeekHoursSetExpected = new DaysOfWeekHoursSet("wednesday", 5);
        LineEntry lineEntry1 = new LineEntry(pWT1, dayWkHrSetTest1, testTimesheet1);

        //should I make this method static?
//        LineEntry.updateALineEntry(dayWkHrSetTest1, dayWkHrSetTest2);
        DaysOfWeekHoursSet actual = lineEntry1.updateHoursOnLineEntry(dayWkHrSetTest1, dayWkHrSetTest2);

        assertTrue(actual.equals(daysOfWeekHoursSetExpected));

        //    public DaysOfWeekHoursSet updateALineEntry(DaysOfWeekHoursSet dayHourCombo1, DaysOfWeekHoursSet dayHourCombo2){
//
//        Integer newMondayTotal = dayHourCombo1.getMondayHours() + dayHourCombo2.getMondayHours();
//        Integer newTuesdayTotal = dayHourCombo1.getTuesdayHours() + dayHourCombo2.getTuesdayHours();
//        Integer newWednesdayTotal = dayHourCombo1.getWednesdayHours() + dayHourCombo2.getWednesdayHours();
//        Integer newThursdayTotal = dayHourCombo1.getThursdayHours() + dayHourCombo2.getThursdayHours();
//        Integer newFridayTotal = dayHourCombo1.getFridayHours() + dayHourCombo2.getFridayHours();
//        Integer newSaturdayTotal = dayHourCombo1.getSaturdayHours() + dayHourCombo2.getSaturdayHours();
//
//        DaysOfWeekHoursSet dayHourCombo3 = new DaysOfWeekHoursSet(newMondayTotal, newTuesdayTotal, newWednesdayTotal, newThursdayTotal, newFridayTotal, newSaturdayTotal);
//        return dayHourCombo3;
//    }
    }

}
