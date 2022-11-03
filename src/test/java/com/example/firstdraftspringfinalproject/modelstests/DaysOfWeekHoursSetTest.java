package com.example.firstdraftspringfinalproject.modelstests;

import com.example.firstdraftspringfinalproject.models.DaysOfWeekHoursSet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DaysOfWeekHoursSetTest {

    @Test
    public void testConstructorWith2params(){
        DaysOfWeekHoursSet test = new DaysOfWeekHoursSet("weDnesday", 2);
        assertEquals(2, test.getWednesdayHours());
    }

//    public DaysOfWeekHoursSet(String dayOfWeek, Integer hours){
//
//        if (dayOfWeek.equalsIgnoreCase("monday")){
//            this.mondayHours = hours;
//        } else if (dayOfWeek.equalsIgnoreCase("Tuesday")){
//            this.tuesdayHours = hours;
//        } else if (dayOfWeek.equalsIgnoreCase("Wednesday")){
//            this.wednesdayHours = hours;
//        } else if (dayOfWeek.equalsIgnoreCase("Thursday")){
//            this.thursdayHours = hours;
//        }else if (dayOfWeek.equalsIgnoreCase("Friday")){
//            this.fridayHours = hours;
//        }else if (dayOfWeek.equalsIgnoreCase("Saturday")){
//            this.saturdayHours = hours;
//        }
//
//    }
}
