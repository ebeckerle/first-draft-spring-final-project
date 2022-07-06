package com.example.firstdraftspringfinalproject.models;

import com.example.firstdraftspringfinalproject.data.LineEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

@Entity
public class Timesheet {


    @ManyToOne
    private Employee employee;

    @Id
    @GeneratedValue
    private Integer timesheetId;

    private GregorianCalendar startDate;
    private GregorianCalendar dueDate;
    private GregorianCalendar payDay;
    private Boolean completionStatus;
    private Boolean supervisorApproval;

//    @NotBlank(message = "There are no hours listed on your timesheet, you must add a line entry to submit a timesheet")
    @OneToMany(cascade = CascadeType.ALL)
    private List<LineEntry> lineEntries = new ArrayList<>();

    @Min(0) @Max(24)
    private Integer totalMondayHours;
    private Integer totalTuesdayHours;
    private Integer totalWednesdayHours;
    private Integer totalThursdayHours;
    private Integer totalFridayHours;
    private Integer totalSaturdayHours;

    private Integer totalHours;

    private Integer totalPay;



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

    public Timesheet () {}

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

    public List<LineEntry> getLineEntries() {
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

//    public void addHoursToALineEntry(LineEntry lineEntry, String dayOfWeek, Integer hours){
//        for (LineEntry entry :
//             this.lineEntries) {
//            if (entry.getProject().equals(lineEntry.getProject()) && entry.getWorkType().equals(lineEntry.getWorkType())){
//                entry.setHashmapKeyValuePairIntoDayOfWeekAndHoursMap(dayOfWeek, hours);
//            }
//        }
//    }
//
//    public void checkAndAddALineEntry(LineEntry newEntry, String dayOfWeek, Integer hours){
//        boolean doesLineEntryAlreadyExist = false;
//        while (!doesLineEntryAlreadyExist) {
//            for (LineEntry entry :
//                    this.lineEntries) {
//                if (entry.equals(newEntry)) {
//                    entry.setHashmapKeyValuePairIntoDayOfWeekAndHoursMap(dayOfWeek, hours);
//                    entry.setTotalHoursInLineEntry();
//                    doesLineEntryAlreadyExist = true;
//                    break;
//                }
//            }
//            if (!doesLineEntryAlreadyExist){
//                this.lineEntries.add(newEntry);
//                newEntry.setTotalHoursInLineEntry();
//                break;
//            }
//        }
//    }
//
//    /* TODO - code a method like totalMondaysHours, but will work for any day of the week - totalDayOfWeekHours(Timesheet aTimesheet,
//     - TODO  - Still need to throw error if amount of hours is over 24!!*/
//    public Integer totalDayOfWeekHours(String dayOfWeek){
//        Integer totalHours = 0;
//        for (LineEntry lineEntry:
//             this.lineEntries) {
//            if (lineEntry.getDayOfWeekAndHours().containsKey(dayOfWeek)){
//                totalHours += lineEntry.getDayOfWeekAndHours().get(dayOfWeek);
//            }
//        }
//        return totalHours;
//    }

//        public void checkAndAddALineEntry(LineEntry newEntry){
//        boolean doesLineEntryAlreadyExist = false;
//        while (!doesLineEntryAlreadyExist) {
//            for (LineEntry entry :
//                    this.lineEntries) {
//                if (entry.equals(newEntry)) {
//                    DaysOfWeekHoursSet dayHourCombo1 = newEntry.getDaysOfWeekHoursCombo();
//                    DaysOfWeekHoursSet dayHourCombo2 = entry.getDaysOfWeekHoursCombo();
//                    Integer newMondayTotal = dayHourCombo1.getMondayHours() + dayHourCombo2.getMondayHours();
//                    Integer newTuesdayTotal = dayHourCombo1.getTuesdayHours() + dayHourCombo2.getTuesdayHours();
//                    Integer newWednesdayTotal = dayHourCombo1.getWednesdayHours() + dayHourCombo2.getWednesdayHours();
//                    Integer newThursdayTotal = dayHourCombo1.getThursdayHours() + dayHourCombo2.getThursdayHours();
//                    Integer newFridayTotal = dayHourCombo1.getFridayHours() + dayHourCombo2.getFridayHours();
//                    Integer newSaturdayTotal = dayHourCombo1.getSaturdayHours() + dayHourCombo2.getSaturdayHours();
//                    DaysOfWeekHoursSet dayHourCombo3 = new DaysOfWeekHoursSet(newMondayTotal, newTuesdayTotal, newWednesdayTotal, newThursdayTotal, newFridayTotal, newSaturdayTotal);
//                    newEntry.setDaysOfWeekHoursCombo(dayHourCombo3);
//                    doesLineEntryAlreadyExist = true;
//                    break;
//                }
//            }
//            if (!doesLineEntryAlreadyExist){
//                this.lineEntries.add(newEntry);
//                break;
//            }
//        }
//    }

    public boolean checkALineEntry(LineEntry newEntry){
        boolean doesLineEntryAlreadyExist = false;
        while (!doesLineEntryAlreadyExist) {
            for (LineEntry entry :
                    this.lineEntries) {
                if (entry.equals(newEntry)) {
                    doesLineEntryAlreadyExist = true;
                    break;
                }
            }
            if (!doesLineEntryAlreadyExist){
                break;
            }
        }
        return doesLineEntryAlreadyExist;
    }

    public LineEntry getLineEntryWithMatchingProjectWorkType(ProjectWorkTypeSet projectWorkTypeSet){
        for (LineEntry lineEntry:
             this.lineEntries) {
            if (lineEntry.getProjectWorkTypeCombo().equals(projectWorkTypeSet)){
                return lineEntry;
            }
        }
        return new LineEntry();
    }

    public Integer totalDayOfWeekHours(String dayOfWeek){
        Integer totalHours = 0;
        if (dayOfWeek.equals("Monday")){
            for (LineEntry lineEntry:
                    this.lineEntries) {
                totalHours += lineEntry.getDaysOfWeekHoursCombo().getMondayHours();
                }
        } else if (dayOfWeek.equals("Tuesday")){
            for (LineEntry lineEntry:
                    this.lineEntries) {
                totalHours += lineEntry.getDaysOfWeekHoursCombo().getTuesdayHours();
            }
        }else if (dayOfWeek.equals("Wednesday")){
            for (LineEntry lineEntry:
                    this.lineEntries) {
                totalHours += lineEntry.getDaysOfWeekHoursCombo().getWednesdayHours();
            }
        }else if (dayOfWeek.equals("Thursday")){
            for (LineEntry lineEntry:
                    this.lineEntries) {
                totalHours += lineEntry.getDaysOfWeekHoursCombo().getThursdayHours();
            }
        }else if (dayOfWeek.equals("Friday")){
            for (LineEntry lineEntry:
                    this.lineEntries) {
                totalHours += lineEntry.getDaysOfWeekHoursCombo().getFridayHours();
            }
        }else if (dayOfWeek.equals("Saturday")){
            for (LineEntry lineEntry:
                    this.lineEntries) {
                totalHours += lineEntry.getDaysOfWeekHoursCombo().getSaturdayHours();
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
