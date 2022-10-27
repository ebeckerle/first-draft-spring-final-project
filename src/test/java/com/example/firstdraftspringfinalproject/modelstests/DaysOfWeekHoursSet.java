package com.example.firstdraftspringfinalproject.modelstests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DaysOfWeekHoursSet {




    @Test
    public void testConstructorWith2params(){
        DaysOfWeekHoursSet test = new DaysOfWeekHoursSet("weDnesday", 2);
        System.out.println(test);
        assertEquals(3, 3);
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
