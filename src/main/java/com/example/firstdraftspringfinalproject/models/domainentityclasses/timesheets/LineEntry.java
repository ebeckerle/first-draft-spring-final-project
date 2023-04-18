package com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets;

import com.example.firstdraftspringfinalproject.models.domainentityclasses.Project;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.WorkType;

import java.util.Objects;
import javax.persistence.*;


@Entity
public class LineEntry {

    @Id
    @GeneratedValue
    private Integer id;

    @OneToOne(cascade = CascadeType.PERSIST)
    private ProjectWorkTypeSet projectWorkTypeCombo;

    @ManyToOne
    private Project project;

    @ManyToOne
    private WorkType workType;

    @OneToOne(cascade = CascadeType.ALL)
    private DaysOfWeekHoursSet daysOfWeekHoursCombo;

    @ManyToOne
    @JoinColumn(name="timesheet_id")
    private Timesheet timesheet;

    private Integer totalHours = 0;


    public LineEntry(ProjectWorkTypeSet projectWorkTypeCombo, DaysOfWeekHoursSet daysOfWeekHoursCombo, Timesheet timesheet){
        this.projectWorkTypeCombo = projectWorkTypeCombo;
        this.project = projectWorkTypeCombo.getProject();
        this.workType = projectWorkTypeCombo.getWorkType();
        this.daysOfWeekHoursCombo = daysOfWeekHoursCombo;
        this.totalHours = daysOfWeekHoursCombo.getTotalHours();
        this.timesheet = timesheet;
    }

    public LineEntry(Project project, WorkType workType, DaysOfWeekHoursSet daysOfWeekHoursCombo, Timesheet timesheet){
        this.project = project;
        this.workType = workType;
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

    public DaysOfWeekHoursSet getDaysOfWeekHoursCombo() {
        return daysOfWeekHoursCombo;
    }

    public void setDaysOfWeekHoursCombo(DaysOfWeekHoursSet daysOfWeekHoursCombo) {
        this.daysOfWeekHoursCombo = daysOfWeekHoursCombo;
    }

    public Integer getTotalHours() {
        return  daysOfWeekHoursCombo.getTotalHours();
    }

    public Timesheet getTimesheet() {
        return timesheet;
    }

    public void setTimesheet(Timesheet timesheet) {
        this.timesheet = timesheet;
    }


    public static DaysOfWeekHoursSet updateHoursOnLineEntry(DaysOfWeekHoursSet dayHourCombo1, DaysOfWeekHoursSet dayHourCombo2){

        Integer newMondayTotal = dayHourCombo1.getMondayHours() + dayHourCombo2.getMondayHours();
        Integer newTuesdayTotal = dayHourCombo1.getTuesdayHours() + dayHourCombo2.getTuesdayHours();
        Integer newWednesdayTotal = dayHourCombo1.getWednesdayHours() + dayHourCombo2.getWednesdayHours();
        Integer newThursdayTotal = dayHourCombo1.getThursdayHours() + dayHourCombo2.getThursdayHours();
        Integer newFridayTotal = dayHourCombo1.getFridayHours() + dayHourCombo2.getFridayHours();
        Integer newSaturdayTotal = dayHourCombo1.getSaturdayHours() + dayHourCombo2.getSaturdayHours();

        DaysOfWeekHoursSet dayHourCombo3 = new DaysOfWeekHoursSet(newMondayTotal, newTuesdayTotal, newWednesdayTotal, newThursdayTotal, newFridayTotal, newSaturdayTotal);
        return dayHourCombo3;
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
