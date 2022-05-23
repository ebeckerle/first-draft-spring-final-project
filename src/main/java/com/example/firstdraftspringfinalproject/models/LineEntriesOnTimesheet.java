package com.example.firstdraftspringfinalproject.models;

import java.util.Date;
import java.util.Objects;

public class LineEntriesOnTimesheet {

    private Project project;
    private WorkType workType;
    private Integer hours;
//    private DaysOfWeek dayOfTheWeek;
    private String dayOfTheWeek;
    private Date date;

    public LineEntriesOnTimesheet(Project project, WorkType workType, String dayOfTheWeek, Integer hours){
        this.project = project;
        this.workType = workType;
        this.hours = hours;
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public LineEntriesOnTimesheet(Project project, WorkType workType, String dayOfTheWeek, Integer hours, Date date){
        this.project = project;
        this.workType = workType;
        this.hours = hours;
        this.dayOfTheWeek = dayOfTheWeek;
        this.date = date;
    }

    public LineEntriesOnTimesheet(Project project, WorkType workType, Integer hours){
        this.project = project;
        this.workType = workType;
        this.hours = hours;
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

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public String getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public void setDayOfTheWeek(String dayOfTheWeek) {
        if (dayOfTheWeek.equals("Monday")  || dayOfTheWeek.equals("Tuesday") || dayOfTheWeek.equals("Wednesday") || dayOfTheWeek.equals("Thursday")|| dayOfTheWeek.equals("Friday") || dayOfTheWeek.equals("Saturday")) {
            this.dayOfTheWeek = dayOfTheWeek;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineEntriesOnTimesheet that = (LineEntriesOnTimesheet) o;
        return project.equals(that.project) && workType.equals(that.workType) && hours.equals(that.hours) && dayOfTheWeek.equals(that.dayOfTheWeek) && date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(project, workType, hours, dayOfTheWeek, date);
    }
}
