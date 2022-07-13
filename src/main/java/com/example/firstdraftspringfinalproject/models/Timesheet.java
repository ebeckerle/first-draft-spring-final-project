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
    @OneToMany(mappedBy = "timesheet", cascade = CascadeType.ALL)
    private List<LineEntry> lineEntries = new ArrayList<>();

//    @Min(0) @Max(24)
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

    public Integer getId() {
        return timesheetId;
    }

    public Integer getTimesheetId() {
        return timesheetId;
    }

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

    public Integer getTotalMondayHours() {
        return totalMondayHours;
    }

    public void setTotalMondayHours(Integer totalMondayHours) {
        this.totalMondayHours = totalMondayHours;
    }

    public Integer getTotalTuesdayHours() {
        return totalTuesdayHours;
    }

    public void setTotalTuesdayHours(Integer totalTuesdayHours) {
        this.totalTuesdayHours = totalTuesdayHours;
    }

    public Integer getTotalWednesdayHours() {
        return totalWednesdayHours;
    }

    public void setTotalWednesdayHours(Integer totalWednesdayHours) {
        this.totalWednesdayHours = totalWednesdayHours;
    }

    public Integer getTotalThursdayHours() {
        return totalThursdayHours;
    }

    public void setTotalThursdayHours(Integer totalThursdayHours) {
        this.totalThursdayHours = totalThursdayHours;
    }

    public Integer getTotalFridayHours() {
        return totalFridayHours;
    }

    public void setTotalFridayHours(Integer totalFridayHours) {
        this.totalFridayHours = totalFridayHours;
    }

    public Integer getTotalSaturdayHours() {
        return totalSaturdayHours;
    }

    public void setTotalSaturdayHours(Integer totalSaturdayHours) {
        this.totalSaturdayHours = totalSaturdayHours;
    }

    public Integer getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(Integer totalHours) {
        this.totalHours = totalHours;
    }


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

    public static String formatDates(GregorianCalendar date){
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

    public static GregorianCalendar figureLastWeeksStartDateBasedOnTodaysDate(LocalDate todaysDate){
        //convert LocalDate to a new Gregorian Calendar Date
        int dayOfMonth = todaysDate.getDayOfMonth();
        int monthValue = todaysDate.getMonthValue();
        int year = todaysDate.getYear();
        DayOfWeek dayOfWeek = todaysDate.getDayOfWeek();
        GregorianCalendar todayGC = new GregorianCalendar(year, monthValue-1, dayOfMonth);
        //cycle thru days of the week (DayOfWeek Enum int values) to then reset (using add() method) the date back to the appropriate Monday
        if (dayOfWeek.getValue()== 2){
            todayGC.add(Calendar.DATE, -8);
            return todayGC;
        }else if (dayOfWeek.getValue()==3){
            todayGC.add(Calendar.DATE, -9);
            return todayGC;
        }else if (dayOfWeek.getValue()==4){
            todayGC.add(Calendar.DATE, -10);
            return todayGC;
        }else if (dayOfWeek.getValue()==5){
            todayGC.add(Calendar.DATE, -11);
            return todayGC;
        }else if (dayOfWeek.getValue()==6){
            todayGC.add(Calendar.DATE, -12);
            return todayGC;
        }else if (dayOfWeek.getValue()==7){
            todayGC.add(Calendar.DATE, -13);
            return todayGC;
        }else if (dayOfWeek.getValue()==1){
            todayGC.add(Calendar.DATE, -7);
            return todayGC;
        }else{
            return todayGC;
        }
    }

//    TODO - write a method that will total all of Day of week's hours for all line entries in timesheet



}
