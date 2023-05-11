package com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets;

import com.example.firstdraftspringfinalproject.models.domainentityclasses.Project;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.WorkType;
import com.example.firstdraftspringfinalproject.models.enums.DaysOfWeek;

import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Entity
public class LineEntry {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private Project project;

    @ManyToOne
    private WorkType workType;

    @Max(24)
    @Min(0)
    private Integer mondayHours = 0;
    @Max(24)
    @Min(0)
    private Integer tuesdayHours = 0;

    @Max(24)
    @Min(0)private Integer wednesdayHours = 0;

    @Max(24)
    @Min(0)private Integer thursdayHours = 0;

    @Max(24)
    @Min(0)private Integer fridayHours = 0;

    @Max(24)
    @Min(0)private Integer saturdayHours = 0;

    @ManyToOne
    @JoinColumn(name="timesheet_id")
    private Timesheet timesheet;

    private Integer totalHours = 0;

    public LineEntry(){}
    public LineEntry(Timesheet timesheet){
        this.timesheet = timesheet;
    }

    public LineEntry(Project project, WorkType workType, DaysOfWeek dayOfWeek, Integer hours, Timesheet timesheet){
        if(hours<=0 || hours>24){
            throw new RuntimeException("can not create a line entry with less than 1 hour or more than 24 hours for one day");
        }
        this.project = project;
        this.workType = workType;
        if(dayOfWeek.equals(DaysOfWeek.MONDAY)){
            this.mondayHours = hours;
        } else if (dayOfWeek.equals(DaysOfWeek.TUESDAY)) {
            this.tuesdayHours = hours;
        } else if (dayOfWeek.equals(DaysOfWeek.WEDNESDAY)) {
            this.wednesdayHours = hours;
        } else if (dayOfWeek.equals(DaysOfWeek.THURSDAY)) {
            this.thursdayHours = hours;
        } else if (dayOfWeek.equals(DaysOfWeek.FRIDAY)) {
            this.fridayHours = hours;
        } else if (dayOfWeek.equals(DaysOfWeek.SATURDAY)) {
            this.saturdayHours = hours;
        }
        this.totalHours = hours;
        this.timesheet = timesheet;
    }
    //GETTERS & SETTERS

    public Integer getId() {return id;}

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public WorkType getWorkType() {
        return workType;
    }

    public void setWorkType(WorkType workType) {
        this.workType = workType;
    }


    public Integer getMondayHours() {
        return mondayHours;
    }

    public void setMondayHours(Integer mondayHours) {
        this.mondayHours = mondayHours;
    }

    public Integer getTuesdayHours() {
        return tuesdayHours;
    }

    public void setTuesdayHours(Integer tuesdayHours) {
        this.tuesdayHours = tuesdayHours;
    }

    public Integer getWednesdayHours() {
        return wednesdayHours;
    }

    public void setWednesdayHours(Integer wednesdayHours) {
        this.wednesdayHours = wednesdayHours;
    }

    public Integer getThursdayHours() {
        return thursdayHours;
    }

    public void setThursdayHours(Integer thursdayHours) {
        this.thursdayHours = thursdayHours;
    }

    public Integer getFridayHours() {
        return fridayHours;
    }

    public void setFridayHours(Integer fridayHours) {
        this.fridayHours = fridayHours;
    }

    public Integer getSaturdayHours() {
        return saturdayHours;
    }

    public void setSaturdayHours(Integer saturdayHours) {
        this.saturdayHours = saturdayHours;
    }

    public Integer getTotalHours() {
        return totalHours;
    }

    public void updateTotalHours() {
        this.totalHours = this.mondayHours + this.tuesdayHours + this.wednesdayHours + this.thursdayHours + this.fridayHours + this.saturdayHours;
    }

    public Timesheet getTimesheet() {
        return timesheet;
    }

    public void setTimesheet(Timesheet timesheet) {
        this.timesheet = timesheet;
    }

    public boolean isLineEntryOnTimesheet(Timesheet currentTimesheet) {
        for (LineEntry lineEntry :
                currentTimesheet.getLineEntries()) {
            if(this.project.equals(lineEntry.getProject()) && this.workType.equals(lineEntry.getWorkType())){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineEntry lineEntry = (LineEntry) o;
        return project.equals(lineEntry.project) && workType.equals(lineEntry.workType) && timesheet.equals(lineEntry.timesheet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(project, workType, timesheet);
    }
}
