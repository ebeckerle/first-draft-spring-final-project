package com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets;

import java.util.Objects;
import javax.persistence.*;


@Entity
public class LineEntry {

    @Id
    @GeneratedValue
    private Integer id;

    @OneToOne(cascade = CascadeType.PERSIST)
    private ProjectWorkTypeSet projectWorkTypeCombo;

    @OneToOne(cascade = CascadeType.ALL)
    private DaysOfWeekHoursSet daysOfWeekHoursCombo;

    @ManyToOne
    @JoinColumn(name="timesheet_id")
    private Timesheet timesheet;

    private Integer totalHours = 0;


    public LineEntry(ProjectWorkTypeSet projectWorkTypeCombo, DaysOfWeekHoursSet daysOfWeekHoursCombo, Timesheet timesheet){
        this.projectWorkTypeCombo = projectWorkTypeCombo;
        this.daysOfWeekHoursCombo = daysOfWeekHoursCombo;
        this.totalHours = daysOfWeekHoursCombo.getTotalHours();
        this.timesheet = timesheet;
    }


    public LineEntry(){}


    //GETTERS & SETTERS

    public Integer getId() {
        return id;
    }

    public ProjectWorkTypeSet getProjectWorkTypeCombo() {
        return projectWorkTypeCombo;
    }

    public void setProjectWorkTypeCombo(ProjectWorkTypeSet projectWorkTypeCombo) {
        this.projectWorkTypeCombo = projectWorkTypeCombo;
    }

    public DaysOfWeekHoursSet getDaysOfWeekHoursCombo() {
        return daysOfWeekHoursCombo;
    }

    public void setDaysOfWeekHoursCombo(DaysOfWeekHoursSet daysOfWeekHoursCombo) {
        this.daysOfWeekHoursCombo = daysOfWeekHoursCombo;
    }

    public Integer getTotalHours() {
        return  daysOfWeekHoursCombo.getTotalHours();
    }

    public void setTotalHours(Integer totalHours) {
        this.totalHours = totalHours;
    }

    public Timesheet getTimesheet() {
        return timesheet;
    }

    public void setTimesheet(Timesheet timesheet) {
        this.timesheet = timesheet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineEntry lineEntry = (LineEntry) o;
        return projectWorkTypeCombo.equals(lineEntry.projectWorkTypeCombo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectWorkTypeCombo);
    }


    public static DaysOfWeekHoursSet updateALineEntry(DaysOfWeekHoursSet dayHourCombo1, DaysOfWeekHoursSet dayHourCombo2){

        Integer newMondayTotal = dayHourCombo1.getMondayHours() + dayHourCombo2.getMondayHours();
        Integer newTuesdayTotal = dayHourCombo1.getTuesdayHours() + dayHourCombo2.getTuesdayHours();
        Integer newWednesdayTotal = dayHourCombo1.getWednesdayHours() + dayHourCombo2.getWednesdayHours();
        Integer newThursdayTotal = dayHourCombo1.getThursdayHours() + dayHourCombo2.getThursdayHours();
        Integer newFridayTotal = dayHourCombo1.getFridayHours() + dayHourCombo2.getFridayHours();
        Integer newSaturdayTotal = dayHourCombo1.getSaturdayHours() + dayHourCombo2.getSaturdayHours();

        DaysOfWeekHoursSet dayHourCombo3 = new DaysOfWeekHoursSet(newMondayTotal, newTuesdayTotal, newWednesdayTotal, newThursdayTotal, newFridayTotal, newSaturdayTotal);
        return dayHourCombo3;
    }


}
