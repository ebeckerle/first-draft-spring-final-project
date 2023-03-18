package com.example.firstdraftspringfinalproject.models.interfaces;

import java.time.LocalDate;
import java.util.Calendar;

public interface OurCalendar {


   static Calendar findMonthStartDateBasedOnTodaysDate(LocalDate todaysDate){
       int todaysMonth = todaysDate.getMonth().getValue() - 1;
       int todaysYear = todaysDate.getYear();
       Calendar startOfMonth = Calendar.getInstance();
       startOfMonth.set(todaysYear, todaysMonth, 1);
       return startOfMonth;
    }

    static Calendar findMonthEndDateBasedOnTodaysDate(LocalDate todaysDate){
        int todaysMonth = todaysDate.getMonth().getValue() - 1;
        int todaysYear = todaysDate.getYear();
        LocalDate lastDayOfMonthDate = todaysDate.withDayOfMonth(todaysDate.getMonth().length(todaysDate.isLeapYear()));
        int lastDayOfMonth = lastDayOfMonthDate.getDayOfMonth();
        Calendar endOfMonth = Calendar.getInstance();
        endOfMonth.set(todaysYear, todaysMonth, lastDayOfMonth);
        return endOfMonth;
    }
}
