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
    private HashMap <WorkType, HashMap<Project, Integer>> monday;
    private HashMap <WorkType, HashMap<Project, Integer>> tuesday;
    private HashMap <WorkType, HashMap<Project, Integer>> wednesday;
    private HashMap <WorkType, HashMap<Project, Integer>> thursday;
    private HashMap <WorkType, HashMap<Project, Integer>> friday;
    private HashMap <WorkType, HashMap<Project, Integer>> saturday;
    private ArrayList<LineEntriesOnTimesheet> lineEntries = new ArrayList<>();
//    private String error = "Error";


    public Timesheet (Employee employee){
        this.employee = employee;
    }

    public Timesheet (Employee employee, GregorianCalendar startDate){
        this.employee=employee;
        this.startDate=startDate;
    }

    public Timesheet (Employee employee, GregorianCalendar startDate, GregorianCalendar dueDate, GregorianCalendar payDay, Boolean completionStatus, Boolean supervisorApproval, HashMap<WorkType, HashMap<Project, Integer>> monday, HashMap<WorkType, HashMap<Project, Integer>> tuesday, HashMap<WorkType, HashMap<Project, Integer>> wednesday, HashMap<WorkType, HashMap<Project, Integer>> thursday, HashMap<WorkType, HashMap<Project, Integer>> friday, HashMap<WorkType, HashMap<Project, Integer>> saturday){
        this.employee = employee;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.payDay = payDay;
        this.completionStatus = completionStatus;
        this.supervisorApproval = supervisorApproval;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
    }

//    public Timesheet(Employee employee, String error){
//        this.employee=employee;
//        this.error=error;
//    }

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

    public HashMap<WorkType, HashMap<Project, Integer>> getMonday() {
        return monday;
    }
    public ArrayList<String> getMondayWorkTypes(){
        Collection<WorkType> mondayWorkTypeCollection = monday.keySet();
        ArrayList<String> mondayWorkTypeCollectionToString = new ArrayList<>();
        String workTypeAsString;
        for (WorkType theWorkType : mondayWorkTypeCollection){
            workTypeAsString = WorkType.toStringWorkTypes(theWorkType);
            mondayWorkTypeCollectionToString.add(workTypeAsString);
        }
        return mondayWorkTypeCollectionToString;
    }

    public static void printMondayWorkTypes(ArrayList<String> mondayWorkTypes){
        for (String workType:
                mondayWorkTypes) {
            System.out.println(workType);
        }
    }

    public String getMondayWorkTypeInString(){
        return "Work on this method";
    }

    public HashMap<WorkType, HashMap<Project, Integer>> getTuesday() {
        return tuesday;
    }

    public HashMap<WorkType, HashMap<Project, Integer>> getWednesday() {
        return wednesday;
    }

    public HashMap<WorkType, HashMap<Project, Integer>> getThursday() {
        return thursday;
    }

    public HashMap<WorkType, HashMap<Project, Integer>> getFriday() {
        return friday;
    }

    public HashMap<WorkType, HashMap<Project, Integer>> getSaturday() {
        return saturday;
    }

    public ArrayList<LineEntriesOnTimesheet> getLineEntries() {
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

        GregorianCalendar dueDate = new GregorianCalendar(yearOfStart, monthOfStart, dateOfStart );
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

    public void setMonday(HashMap<WorkType, HashMap<Project, Integer>> monday) {
        this.monday = monday;
    }

    public void setTuesday(HashMap<WorkType, HashMap<Project, Integer>> tuesday) {
        this.tuesday = tuesday;
    }

    public void setWednesday(HashMap<WorkType, HashMap<Project, Integer>> wednesday) {
        this.wednesday = wednesday;
    }

    public void setThursday(HashMap<WorkType, HashMap<Project, Integer>> thursday) {
        this.thursday = thursday;
    }

    public void setFriday(HashMap<WorkType, HashMap<Project, Integer>> friday) {
        this.friday = friday;
    }

    public void setSaturday(HashMap<WorkType, HashMap<Project, Integer>> saturday) {
        this.saturday = saturday;
    }

    public void setLineEntries(ArrayList<LineEntriesOnTimesheet> lineEntries) {
        this.lineEntries = lineEntries;
    }
/* Timesheet - you can't have a workType key have a value where there are two entries with the same project
     - write a method to validate that? & unit Test- ie. we can't have:
     workType1: {
        projectA: 6hrs;
        projectB: 2hrs;
        projectA: 2hrs; <---------- right here can't have this twice.
     */

    /* Timesheet - you can't have two of the same work types in one day (ie, monday, tuesday, etc)
     - write a method to validate that does not happen? & unit Test- ie. we can't have:
     monday: {
        workTypeSand: {projectA: 6hrs;
                       projectC: 2hrs;
        workTypePaint: {projectA: 6hrs;}
        workTypeSand: {projectB: 6hrs;} <---------- right here can't have this twice, project C would need to go in the index 0
     */

    public static Integer totalMondaysHours(Timesheet aTimeSheet){
        Integer totalHours = 0;
        HashMap <WorkType, HashMap<Project, Integer>> monday;
        monday = aTimeSheet.getMonday();
        for (Map.Entry<WorkType, HashMap<Project, Integer>> row : monday.entrySet()){
            for (Map.Entry<Project, Integer> subRow : row.getValue().entrySet()){
                totalHours += subRow.getValue();
            }
        }
        return totalHours;
    }

    // code a method like totalMondaysHours, but will work for any day of the week - totalDayOfWeekHours(Timesheet aTimesheet,


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

}
