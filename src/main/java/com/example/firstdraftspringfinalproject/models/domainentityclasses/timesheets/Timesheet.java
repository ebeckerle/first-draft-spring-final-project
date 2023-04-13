package com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets;

import com.example.firstdraftspringfinalproject.models.domainentityclasses.*;
import com.example.firstdraftspringfinalproject.models.interfaces.TimesheetCalculateDates;
import com.example.firstdraftspringfinalproject.models.interfaces.TimesheetTotalsHours;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

//TODO: add a explanatory file (READ.ME?) to this timesheets package - maybe a pdf/jpg that 's an image graph
// of how the timesheet is translated with line entries being added to a timesheet, etc etc
// image of timesheet existing with callouts, uml diagram as well.

@Entity
public class Timesheet implements TimesheetTotalsHours, TimesheetCalculateDates {

    @ManyToOne
    private Employee employee;

    @Id
    @GeneratedValue
    private Integer timesheetId;

    //Start Dates are Mondays, due dates are the following Mondays, and payDays are the Fridays following the due date.
    private GregorianCalendar startDate;
    private GregorianCalendar dueDate;
    private GregorianCalendar payDay;
    private Boolean completionStatus;
    private Boolean supervisorApproval;

    @OneToMany(mappedBy = "timesheet", cascade = CascadeType.ALL)
    private List<LineEntry> lineEntries = new ArrayList<>();

    private Integer totalMondayHours;
    private Integer totalTuesdayHours;
    private Integer totalWednesdayHours;
    private Integer totalThursdayHours;
    private Integer totalFridayHours;
    private Integer totalSaturdayHours;

    private Integer totalHours;

    private Integer totalPay;
    private Integer currentPayRate;



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

    //TODO  - ? - rewrite this method with DayOfWeek enum as argument?, it will only return 0 with a messed up input but...
    public Integer totalDayOfWeekHours(String dayOfWeek){
        Integer totalHours = 0;
        switch (dayOfWeek) {
            case "Monday" -> {
                for (LineEntry lineEntry :
                        this.lineEntries) {
                    totalHours += lineEntry.getDaysOfWeekHoursCombo().getMondayHours();
                }
            }
            case "Tuesday" -> {
                for (LineEntry lineEntry :
                        this.lineEntries) {
                    totalHours += lineEntry.getDaysOfWeekHoursCombo().getTuesdayHours();
                }
            }
            case "Wednesday" -> {
                for (LineEntry lineEntry :
                        this.lineEntries) {
                    totalHours += lineEntry.getDaysOfWeekHoursCombo().getWednesdayHours();
                }
            }
            case "Thursday" -> {
                for (LineEntry lineEntry :
                        this.lineEntries) {
                    totalHours += lineEntry.getDaysOfWeekHoursCombo().getThursdayHours();
                }
            }
            case "Friday" -> {
                for (LineEntry lineEntry :
                        this.lineEntries) {
                    totalHours += lineEntry.getDaysOfWeekHoursCombo().getFridayHours();
                }
            }
            case "Saturday" -> {
                for (LineEntry lineEntry :
                        this.lineEntries) {
                    totalHours += lineEntry.getDaysOfWeekHoursCombo().getSaturdayHours();
                }
            }
        }
        return totalHours;
    }



    public Integer getTotalHoursByProject(Project project){
        List<LineEntry> lineEntries = this.lineEntries;
        //iterate thru an arraylist of line entries
        Integer totalHoursForProject = 0;
        for (LineEntry lineEntry : lineEntries){
            Integer lineEntryTotal = 0;
            if (lineEntry.getProjectWorkTypeCombo().getProject().equals(project)){
                lineEntryTotal = lineEntry.getTotalHours();
            }
            totalHoursForProject +=lineEntryTotal;
        }
        return totalHoursForProject;
    }

    public Integer getTotalHoursByWorkType(WorkType workType){
        List<LineEntry> lineEntries = this.lineEntries;
        //iterate thru an arraylist of line entries
        Integer totalHoursForWorkType = 0;
        for (LineEntry lineEntry : lineEntries){
            Integer lineEntryTotal = 0;
            if (lineEntry.getProjectWorkTypeCombo().getWorkType().equals(workType)){
                lineEntryTotal = lineEntry.getTotalHours();
            }
            totalHoursForWorkType +=lineEntryTotal;
        }
        return totalHoursForWorkType;
    }


}
