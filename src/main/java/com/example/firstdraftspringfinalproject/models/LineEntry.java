package com.example.firstdraftspringfinalproject.models;

import java.time.DayOfWeek;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LineEntry {

    //    EVERYTHING BELOW IS CODE FOR A lINE ENTRY CLASS USING DayOfTheWeek AS STRING!!!

    private Project project;
    private WorkType workType;
    private HashMap<String, Integer> dayOfWeekAndHours;
    private Date date;

    private Integer totalHours = 0;


    public LineEntry(Project project, WorkType workType, String dayOfWeek, Integer hours){
        this.project = project;
        this.workType = workType;
        HashMap<String, Integer> aDayOfWeekAndHours= new HashMap<>();
        aDayOfWeekAndHours.put(dayOfWeek, hours);
        this.dayOfWeekAndHours = aDayOfWeekAndHours;
    }


    public LineEntry(Project project, WorkType workType, HashMap<String, Integer> dayOfWeekAndHours){
        this.project = project;
        this.workType = workType;
        this.dayOfWeekAndHours = dayOfWeekAndHours;
    }


    //GETTERS & SETTERS
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

    public HashMap<String, Integer> getDayOfWeekAndHours() {
        return dayOfWeekAndHours;
    }

    public void setDayOfWeekAndHours(HashMap<String, Integer> dayOfWeekAndHours) {
        this.dayOfWeekAndHours = dayOfWeekAndHours;
    }

    public Integer getTotalHoursInLineEntry(){
        return totalHours;
    }

    public void setTotalHoursInLineEntry(){
        totalHours = 0;
        for (Integer hours : dayOfWeekAndHours.values()){
            totalHours += hours;
        }
    }

    public void setHashmapKeyValuePairIntoDayOfWeekAndHoursMap(String dayOfWeek, Integer hours){
        if(this.dayOfWeekAndHours.containsKey(dayOfWeek)){
            Integer originalValue = this.dayOfWeekAndHours.get(dayOfWeek);
            Integer newTotal = hours + originalValue;
            this.dayOfWeekAndHours.replace(dayOfWeek, newTotal);
        }else{
            this.dayOfWeekAndHours.put(dayOfWeek, hours);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineEntry lineEntry = (LineEntry) o;
        return project.equals(lineEntry.project) && workType.equals(lineEntry.workType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(project, workType);
    }
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
