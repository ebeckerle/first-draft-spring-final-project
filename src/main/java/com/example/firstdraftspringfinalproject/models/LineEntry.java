package com.example.firstdraftspringfinalproject.models;

import java.time.DayOfWeek;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.*;


@Entity
public class LineEntry {

    //    EVERYTHING BELOW IS CODE FOR A lINE ENTRY CLASS USING DayOfTheWeek AS STRING!!!

    @Id
    @GeneratedValue
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
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


    public DaysOfWeekHoursSet updateALineEntry(DaysOfWeekHoursSet dayHourCombo1, DaysOfWeekHoursSet dayHourCombo2){

        Integer newMondayTotal = dayHourCombo1.getMondayHours() + dayHourCombo2.getMondayHours();
        Integer newTuesdayTotal = dayHourCombo1.getTuesdayHours() + dayHourCombo2.getTuesdayHours();
        Integer newWednesdayTotal = dayHourCombo1.getWednesdayHours() + dayHourCombo2.getWednesdayHours();
        Integer newThursdayTotal = dayHourCombo1.getThursdayHours() + dayHourCombo2.getThursdayHours();
        Integer newFridayTotal = dayHourCombo1.getFridayHours() + dayHourCombo2.getFridayHours();
        Integer newSaturdayTotal = dayHourCombo1.getSaturdayHours() + dayHourCombo2.getSaturdayHours();

        DaysOfWeekHoursSet dayHourCombo3 = new DaysOfWeekHoursSet(newMondayTotal, newTuesdayTotal, newWednesdayTotal, newThursdayTotal, newFridayTotal, newSaturdayTotal);
        return dayHourCombo3;
    }

//    public Integer getTotalHoursInLineEntry(){
//        return totalHours;
//    }
//
//    public void setTotalHoursInLineEntry(){
//        totalHours = 0;
//        for (Integer hours : dayOfWeekAndHours.values()){
//            totalHours += hours;
//        }
//    }
//
//    public void setHashmapKeyValuePairIntoDayOfWeekAndHoursMap(String dayOfWeek, Integer hours){
//        if(this.dayOfWeekAndHours.containsKey(dayOfWeek)){
//            Integer originalValue = this.dayOfWeekAndHours.get(dayOfWeek);
//            Integer newTotal = hours + originalValue;
//            this.dayOfWeekAndHours.replace(dayOfWeek, newTotal);
//        }else{
//            this.dayOfWeekAndHours.put(dayOfWeek, hours);
//        }
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        LineEntry lineEntry = (LineEntry) o;
//        return project.equals(lineEntry.project) && workType.equals(lineEntry.workType);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(project, workType);
//    }

//    EVERYTHING BELOW IS CODE FOR A lINE ENTRY CLASS USING DayOfWeek ENUM!!!

//    private Project project;
//    private WorkType workType;
//    private HashMap<DayOfWeek, Integer> dayOfWeekAndHours;
//    private Date date;
//
//    private Integer totalHours;
//
//
//    public LineEntry(Project project, WorkType workType, DayOfWeek dayOfWeek, Integer hours){
//        this.project = project;
//        this.workType = workType;
//        HashMap<DayOfWeek, Integer> aDayOfWeekAndHours= new HashMap<>();
//        aDayOfWeekAndHours.put(dayOfWeek, hours);
//        this.dayOfWeekAndHours = aDayOfWeekAndHours;
//    }
//
//
//    public LineEntry(Project project, WorkType workType, HashMap<DayOfWeek, Integer> dayOfWeekAndHours){
//        this.project = project;
//        this.workType = workType;
//        this.dayOfWeekAndHours = dayOfWeekAndHours;
//    }
//
//    public Project getProject() {
//        return project;
//    }
//
//    public void setProject(Project project) {
//        this.project = project;
//    }
//
//    public WorkType getWorkType() {
//        return workType;
//    }
//
//    public void setWorkType(WorkType workType) {
//        this.workType = workType;
//    }
//
//    public HashMap<DayOfWeek, Integer> getDayOfWeekAndHours() {
//        return dayOfWeekAndHours;
//    }
//
//    public void setDayOfWeekAndHours(HashMap<DayOfWeek, Integer> dayOfWeekAndHours) {
//        this.dayOfWeekAndHours = dayOfWeekAndHours;
//    }
//
//    public Integer getTotalHoursInLineEntry(){
////        TODO -- ..test this???
//        for (Integer hours : dayOfWeekAndHours.values()){
//            totalHours += hours;
//        }
//        return totalHours;
//    }
//
//    public void setHashmapKeyValuePairIntoDayOfWeekAndHoursMap(DayOfWeek dayOfWeek, Integer hours){
//        //TODO -- ..test this???
//        if(this.dayOfWeekAndHours.containsKey(dayOfWeek)){
//            Integer originalValue = this.dayOfWeekAndHours.get(dayOfWeek);
//            Integer newTotal = hours + originalValue;
//            this.dayOfWeekAndHours.replace(dayOfWeek, newTotal);
//        }else{
//            this.dayOfWeekAndHours.put(dayOfWeek, hours);
//        }
//    }

}
