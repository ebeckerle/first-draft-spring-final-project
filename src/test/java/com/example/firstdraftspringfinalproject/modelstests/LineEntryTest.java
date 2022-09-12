package com.example.firstdraftspringfinalproject.modelstests;

import com.example.firstdraftspringfinalproject.models.LineEntry;
import com.example.firstdraftspringfinalproject.models.Project;
import com.example.firstdraftspringfinalproject.models.WorkType;
import org.junit.jupiter.api.Test;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashMap;
import java.util.Optional;

import static org.testng.AssertJUnit.assertEquals;


public class LineEntryTest {

//    @Test
//    public void testSetTotalHoursInLineEntry(){
//        Project pIasc = new Project("IASC", "Iowa State Capitol");
//        WorkType wT101 = new WorkType(101, "Inventory");
//        HashMap<String, Integer> dayOfWeekAndHours = new HashMap<>();
//        dayOfWeekAndHours.put("MONDAY", 8);
//        dayOfWeekAndHours.put("TUESDAY", 8);
//        dayOfWeekAndHours.put("WEDNESDAY", 8);
//        LineEntry lineEntry = new LineEntry(pIasc, wT101, dayOfWeekAndHours);
//
//        Integer expected = 24;
//        lineEntry.setTotalHoursInLineEntry();
//        Integer actual = lineEntry.getTotalHoursInLineEntry();
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void testSetHashmapKeyValuePairIntoDayOfWeekAndHoursMap(){
//        //when the day of week already has hours
//        Project pIasc = new Project("IASC", "Iowa State Capitol");
//        WorkType wT101 = new WorkType(101, "Inventory");
//        HashMap<String, Integer> dayOfWeekAndHours = new HashMap<>();
//        dayOfWeekAndHours.put("MONDAY", 8);
//        dayOfWeekAndHours.put("TUESDAY", 8);
//        dayOfWeekAndHours.put("WEDNESDAY", 8);
//        LineEntry newLineEntry = new LineEntry(pIasc, wT101, dayOfWeekAndHours);
//        newLineEntry.setHashmapKeyValuePairIntoDayOfWeekAndHoursMap("WEDNESDAY", 2);
////        setHashmapKeyValuePairIntoDayOfWeekAndHoursMap(String dayOfWeek, Integer hours)
//        Integer actual = newLineEntry.getDayOfWeekAndHours().get("WEDNESDAY");
//        Integer expected = 10;
//
//        assertEquals(expected, actual);
//
//
//    }
//
//    @Test
//    public void testTests(){
//        String string = "N";
//        assertEquals("N", string);
//    }
}
