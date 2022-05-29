package com.example.firstdraftspringfinalproject.models;

import java.time.DayOfWeek;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LineEntry {

    private Project project;
    private WorkType workType;
    private HashMap<DayOfWeek, Integer> dayOfWeekAndHours;
    private Date date;

    private Integer totalHours;


    public LineEntry(Project project, WorkType workType, DayOfWeek dayOfWeek, Integer hours){
        this.project = project;
        this.workType = workType;
        HashMap<DayOfWeek, Integer> aDayOfWeekAndHours= new HashMap<>();
        aDayOfWeekAndHours.put(dayOfWeek, hours);
        this.dayOfWeekAndHours = aDayOfWeekAndHours;
    }


    public LineEntry(Project project, WorkType workType, HashMap<DayOfWeek, Integer> dayOfWeekAndHours){
        this.project = project;
        this.workType = workType;
        this.dayOfWeekAndHours = dayOfWeekAndHours;
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

    public HashMap<DayOfWeek, Integer> getDayOfWeekAndHours() {
        return dayOfWeekAndHours;
    }

    public void setDayOfWeekAndHours(HashMap<DayOfWeek, Integer> dayOfWeekAndHours) {
        this.dayOfWeekAndHours = dayOfWeekAndHours;
    }

    public Integer getTotalHoursInLineEntry(){
//        TODO -- ..test this???
        for (Integer hours : dayOfWeekAndHours.values()){
            totalHours += hours;
        }
        return totalHours;
    }

    public void setHashmapKeyValuePairIntoDayOfWeekAndHoursMap(DayOfWeek dayOfWeek, Integer hours){
        //TODO -- ..test this???
        if(this.dayOfWeekAndHours.containsKey(dayOfWeek)){
            Integer originalValue = this.dayOfWeekAndHours.get(dayOfWeek);
            Integer newTotal = hours + originalValue;
            this.dayOfWeekAndHours.replace(dayOfWeek, newTotal);
        }else{
            this.dayOfWeekAndHours.put(dayOfWeek, hours);
        }
    }

}
