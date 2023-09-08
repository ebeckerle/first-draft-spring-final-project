package com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets;

import com.example.firstdraftspringfinalproject.models.domainentityclasses.*;
import com.example.firstdraftspringfinalproject.models.enums.DaysOfWeek;
import com.example.firstdraftspringfinalproject.models.interfaces.TimesheetCalculateDates;
import com.example.firstdraftspringfinalproject.models.interfaces.TimesheetTotalsHours;

import javax.persistence.*;
import java.util.*;

@Entity
public class Timesheet implements TimesheetTotalsHours, TimesheetCalculateDates {

    @ManyToOne
    private Employee employee;

    @Id
    @GeneratedValue
    private Integer id;

    //Start Dates are Mondays, due dates are the following Mondays, and payDays are the Fridays following the due date.
    private GregorianCalendar startDate;
    private GregorianCalendar dueDate;
    private GregorianCalendar payDay;
    private Boolean completionStatus;
    private Boolean supervisorApproval;

    @OneToMany(mappedBy = "timesheet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LineEntry> lineEntries = new ArrayList<>();

    private Integer totalMondayHours = 0;
    private Integer totalTuesdayHours = 0;
    private Integer totalWednesdayHours = 0;
    private Integer totalThursdayHours = 0;
    private Integer totalFridayHours = 0;
    private Integer totalSaturdayHours = 0;

    private Integer totalHours = 0;
    private Integer currentPayRate;



    public Timesheet (Employee employee){
        if(!employee.getCurrentTimesheetCompletionStatus()){
            throw new RuntimeException("cant create a new timesheet until the current " +
                    "is submitted for approval - Timesheet Class Constructor");
        }
        this.employee = employee;
        this.completionStatus = false;
        this.supervisorApproval = false;

    }

    public Timesheet (Employee employee, GregorianCalendar startDate){
        if(!employee.getCurrentTimesheetCompletionStatus()){
            throw new RuntimeException("cant create a new timesheet until the current " +
                    "is submitted for approval - Timesheet Class Constructor");
        }
        this.employee=employee;
        this.startDate=startDate;
        this.completionStatus = false;
        this.supervisorApproval = false;
    }


    public Timesheet () {}

    // GETTERS & SETTERS

    public Integer getId() {
        return id;
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
        if(!this.completionStatus){
            this.supervisorApproval = false;
        }
    }

    public void setSupervisorApproval(Boolean supervisorApproval) {
        this.supervisorApproval = supervisorApproval;
    }


    public Integer getTotalMondayHours() {
        return totalMondayHours;
    }

    public Integer getTotalTuesdayHours() {
        return totalTuesdayHours;
    }

    public Integer getTotalWednesdayHours() {
        return totalWednesdayHours;
    }

    public Integer getTotalThursdayHours() {
        return totalThursdayHours;
    }

    public Integer getTotalFridayHours() {
        return totalFridayHours;
    }

    public Integer getTotalSaturdayHours() {
        return totalSaturdayHours;
    }

    public void setTotalMondayHours(Integer totalMondayHours) {
        this.totalMondayHours = totalMondayHours;
    }


    public void setTotalTuesdayHours(Integer totalTuesdayHours) {
        this.totalTuesdayHours = totalTuesdayHours;
    }


    public void setTotalWednesdayHours(Integer totalWednesdayHours) {
        this.totalWednesdayHours = totalWednesdayHours;
    }


    public void setTotalThursdayHours(Integer totalThursdayHours) {
        this.totalThursdayHours = totalThursdayHours;
    }

    public void setTotalFridayHours(Integer totalFridayHours) {
        this.totalFridayHours = totalFridayHours;
    }


    public void setTotalSaturdayHours(Integer totalSaturdayHours) {
        this.totalSaturdayHours = totalSaturdayHours;
    }
    public void updateEachDayOfWeekTotalHours() {
        Integer currentTotalMonday = 0;
        Integer currentTotalTuesday = 0;
        Integer currentTotalWednesday = 0;
        Integer currentTotalThursday = 0;
        Integer currentTotalFriday = 0;
        Integer currentTotalSaturday = 0;
        for (LineEntry lineEntry :
                this.lineEntries) {
            currentTotalMonday += lineEntry.getMondayHours();
            currentTotalTuesday += lineEntry.getTuesdayHours();
            currentTotalWednesday += lineEntry.getWednesdayHours();
            currentTotalThursday += lineEntry.getThursdayHours();
            currentTotalFriday += lineEntry.getFridayHours();
            currentTotalSaturday += lineEntry.getSaturdayHours();
        }
        this.totalMondayHours = currentTotalMonday;
        this.totalTuesdayHours = currentTotalTuesday;
        this.totalWednesdayHours = currentTotalWednesday;
        this.totalThursdayHours = currentTotalThursday;
        this.totalFridayHours = currentTotalFriday;
        this.totalSaturdayHours = currentTotalSaturday;
    }

    public Integer getTotalHours() {
        return totalHours;
    }

    public void setTotalHours() {
        Integer totalHours = 0;
        for (LineEntry lineEntry:
                this.lineEntries) {
            totalHours += lineEntry.getTotalHours();
        }
        this.totalHours = totalHours;
    }

    public Integer getCurrentPayRate() {
        return currentPayRate;
    }

    public void setCurrentPayRate() {
        Employee employee = this.employee;
        this.currentPayRate = employee.getPayRate();
    }


    public Integer totalDayOfWeekHours(DaysOfWeek dayOfWeek){
        Integer totalHours = 0;
        switch (dayOfWeek) {
            case MONDAY -> {
                for (LineEntry lineEntry :
                        this.lineEntries) {
                    totalHours += lineEntry.getMondayHours();
                }
            }
            case TUESDAY -> {
                for (LineEntry lineEntry :
                        this.lineEntries) {
                    totalHours += lineEntry.getTuesdayHours();
                }
            }
            case WEDNESDAY -> {
                for (LineEntry lineEntry :
                        this.lineEntries) {
                    totalHours += lineEntry.getWednesdayHours();
                }
            }
            case THURSDAY -> {
                for (LineEntry lineEntry :
                        this.lineEntries) {
                    totalHours += lineEntry.getThursdayHours();
                }
            }
            case FRIDAY -> {
                for (LineEntry lineEntry :
                        this.lineEntries) {
                    totalHours += lineEntry.getFridayHours();
                }
            }
            case SATURDAY -> {
                for (LineEntry lineEntry :
                        this.lineEntries) {
                    totalHours += lineEntry.getSaturdayHours();
                }
            }
        }
        return totalHours;
    }

    public Integer getTotalHoursByProject(Project project){
        List<LineEntry> lineEntries = this.lineEntries;
        Integer totalHoursForProject = 0;
        for (LineEntry lineEntry : lineEntries){
            Integer lineEntryTotal = 0;
            if (lineEntry.getProject().equals(project)){
                lineEntryTotal = lineEntry.getTotalHours();
            }
            totalHoursForProject +=lineEntryTotal;
        }
        return totalHoursForProject;
    }

    public Integer getTotalHoursByWorkType(WorkType workType){
        List<LineEntry> lineEntries = this.lineEntries;
        Integer totalHoursForWorkType = 0;
        for (LineEntry lineEntry : lineEntries){
            Integer lineEntryTotal = 0;
            if (lineEntry.getWorkType().equals(workType)){
                lineEntryTotal = lineEntry.getTotalHours();
            }
            totalHoursForWorkType +=lineEntryTotal;
        }
        return totalHoursForWorkType;
    }


    public LineEntry findMatchingLineEntry(LineEntry theNewLineEntry) {
        if (!theNewLineEntry.isLineEntryOnTimesheet(this)){
            throw new RuntimeException("cannot return a matching line entry, " +
                    "matching line entry to arg does not exist on this timesheet." +
                    " Matching Line Entries must be equal in project and work type.");
        }
        LineEntry existingLineEntry = new LineEntry();
        for (LineEntry lineEntry:
                this.getLineEntries()) {
            if(lineEntry.equals(theNewLineEntry)){
                existingLineEntry = lineEntry;
            }
        }
        return existingLineEntry;
    }

    public void updateLineEntry(LineEntry existingLineEntry, LineEntry theNewLineEntry) {
        if(!existingLineEntry.equals(theNewLineEntry)){
            throw new RuntimeException("these line entries do not match, (are not equal in project and work type), failed to update existing.");
        }
        Integer totalMondayHours = existingLineEntry.getMondayHours() + theNewLineEntry.getMondayHours();
        Integer totalTuesdayHours = existingLineEntry.getTuesdayHours() + theNewLineEntry.getTuesdayHours();
        Integer totalWednesdayHours = existingLineEntry.getWednesdayHours() + theNewLineEntry.getWednesdayHours();
        Integer totalThursdayHours = existingLineEntry.getThursdayHours() + theNewLineEntry.getThursdayHours();
        Integer totalFridayHours = existingLineEntry.getFridayHours() + theNewLineEntry.getFridayHours();
        Integer totalSaturdayHours = existingLineEntry.getSaturdayHours() + theNewLineEntry.getSaturdayHours();
        theNewLineEntry.setMondayHours(totalMondayHours);
        theNewLineEntry.setTuesdayHours(totalTuesdayHours);
        theNewLineEntry.setWednesdayHours(totalWednesdayHours);
        theNewLineEntry.setThursdayHours(totalThursdayHours);
        theNewLineEntry.setFridayHours(totalFridayHours);
        theNewLineEntry.setSaturdayHours(totalSaturdayHours);

        theNewLineEntry.updateTotalHours();

        this.lineEntries.remove(existingLineEntry);
        this.lineEntries.add(theNewLineEntry);
    }

    public void replaceLineEntry(LineEntry existingLineEntry, LineEntry editedLineEntry) {
        editedLineEntry.setProject(existingLineEntry.getProject());
        editedLineEntry.setWorkType(existingLineEntry.getWorkType());
        editedLineEntry.setTimesheet(existingLineEntry.getTimesheet());
        editedLineEntry.updateTotalHours();
        this.lineEntries.remove(existingLineEntry);
        this.lineEntries.add(editedLineEntry);
    }
}
