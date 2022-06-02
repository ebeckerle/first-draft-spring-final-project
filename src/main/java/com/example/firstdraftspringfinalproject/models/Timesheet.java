package com.example.firstdraftspringfinalproject.models;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

public class Timesheet {
    private Employee employee;
    private GregorianCalendar startDate;
    private GregorianCalendar dueDate;
    private GregorianCalendar payDay;
    private Boolean completionStatus;
    private Boolean supervisorApproval;
    private ArrayList<LineEntry> lineEntries = new ArrayList<>();



    public Timesheet (Employee employee){
        this.employee = employee;
    }

    public Timesheet (Employee employee, GregorianCalendar startDate){
        this.employee=employee;
        this.startDate=startDate;
    }

    public Timesheet (Employee employee, GregorianCalendar startDate, GregorianCalendar dueDate, GregorianCalendar payDay, Boolean completionStatus, Boolean supervisorApproval){
        this.employee = employee;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.payDay = payDay;
        this.completionStatus = completionStatus;
        this.supervisorApproval = supervisorApproval;
    }


    // GETTERS & SETTERS

    public Employee getEmployee() {
        return employee;
    }
    public GregorianCalendar getStartDate() {
        return startDate;
    }
    public GregorianCalendar getDueDate() {
        return dueDate;
    }
    public GregorianCalendar getPayDay() {
        return payDay;
    }

    public Boolean getCompletionStatus() {
        return completionStatus;
    }

    public Boolean getSupervisorApproval() {
        return supervisorApproval;
    }

    public ArrayList<LineEntry> getLineEntries() {
        return lineEntries;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setDates(GregorianCalendar startDate) {

        this.startDate = startDate;
        int yearOfStart = startDate.get(Calendar.YEAR);
        int monthOfStart = startDate.get(Calendar.MONTH);
        int dateOfStart = startDate.get(Calendar.DATE);

        GregorianCalendar dueDate = new GregorianCalendar(yearOfStart, monthOfStart, dateOfStart);
        dueDate.add(Calendar.DATE, 7);
        this.dueDate = dueDate;

        GregorianCalendar payDay = new GregorianCalendar(yearOfStart, monthOfStart, dateOfStart);
        payDay.add(Calendar.DATE, 11);
        this.payDay = payDay;
    }

    public void setCompletionStatus(Boolean completionStatus) {
        this.completionStatus = completionStatus;
    }

    public void setSupervisorApproval(Boolean supervisorApproval) {
        this.supervisorApproval = supervisorApproval;
    }

    public void setLineEntries(ArrayList<LineEntry> lineEntries) {
        this.lineEntries = lineEntries;
    }

    public Boolean checkALineEntryAlreadyExists(LineEntry lineEntry){

        if (this.lineEntries.contains(lineEntry)){
            return true;
        }else {
            return false;
        }
    }

    public void addHoursToALineEntry(LineEntry lineEntry, String dayOfWeek, Integer hours){
        for (LineEntry entry :
             this.lineEntries) {
            if (entry.getProject().equals(lineEntry.getProject()) && entry.getWorkType().equals(lineEntry.getWorkType())){
                entry.setHashmapKeyValuePairIntoDayOfWeekAndHoursMap(dayOfWeek, hours);
            }
        }
    }

    public void checkAndAddALineEntry(LineEntry newEntry, String dayOfWeek, Integer hours){
        boolean doesLineEntryAlreadyExist = false;
        while (!doesLineEntryAlreadyExist) {
            for (LineEntry entry :
                    this.lineEntries) {
                if (entry.equals(newEntry)) {
                    entry.setHashmapKeyValuePairIntoDayOfWeekAndHoursMap(dayOfWeek, hours);
                    doesLineEntryAlreadyExist = true;
                    break;
                }
            }
            if (!doesLineEntryAlreadyExist){
                this.lineEntries.add(newEntry);
                break;
            }
        }
    }

    /* TODO - code a method like totalMondaysHours, but will work for any day of the week - totalDayOfWeekHours(Timesheet aTimesheet,
     - TODO  - Still need to throw error if amount of hours is over 24!!*/
    public Integer totalDayOfWeekHours(String dayOfWeek){
        Integer totalHours = 0;
        for (LineEntry lineEntry:
             this.lineEntries) {
            if (lineEntry.getDayOfWeekAndHours().containsKey(dayOfWeek)){
                totalHours += lineEntry.getDayOfWeekAndHours().get(dayOfWeek);
            }
        }
        return totalHours;
    }

    public String formatDates(GregorianCalendar date){
        return date.get(Calendar.MONTH)+1 + "/" + date.get(Calendar.DATE) + "/" + date.get(Calendar.YEAR);
    }

    public GregorianCalendar figureStartDateBasedOnTodaysDate(LocalDate todaysDate){
        //convert LocalDate to a new Gregorian Calendar Date
        int dayOfMonth = todaysDate.getDayOfMonth();
        int monthValue = todaysDate.getMonthValue();
        int year = todaysDate.getYear();
        DayOfWeek dayOfWeek = todaysDate.getDayOfWeek();
        GregorianCalendar todayGC = new GregorianCalendar(year, monthValue-1, dayOfMonth);
        //cycle thru days of the week (DayOfWeek Enum int values) to then reset (using add() method) the date back to the appropriate Monday
        if (dayOfWeek.getValue()== 2){
            todayGC.add(Calendar.DATE, -1);
            return todayGC;
        }else if (dayOfWeek.getValue()==3){
            todayGC.add(Calendar.DATE, -2);
            return todayGC;
        }else if (dayOfWeek.getValue()==4){
            todayGC.add(Calendar.DATE, -3);
            return todayGC;
        }else if (dayOfWeek.getValue()==5){
            todayGC.add(Calendar.DATE, -4);
            return todayGC;
        }else if (dayOfWeek.getValue()==6){
            todayGC.add(Calendar.DATE, -5);
            return todayGC;
        }else if (dayOfWeek.getValue()==7){
            todayGC.add(Calendar.DATE, -6);
            return todayGC;
        }else{
            return todayGC;
        }
    }

//    TODO - write a method that will total all of Day of week's hours for all line entries in timesheet



}
