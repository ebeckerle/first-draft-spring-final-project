package com.example.firstdraftspringfinalproject.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class DaysOfWeekHoursSet {

    @Id
    @GeneratedValue
    private Integer id;

    private Integer mondayHours = 0;
    private Integer tuesdayHours = 0;
    private Integer wednesdayHours = 0;
    private Integer thursdayHours = 0;
    private Integer fridayHours = 0;
    private Integer saturdayHours = 0;

    public DaysOfWeekHoursSet(Integer mondayHours, Integer tuesdayHours, Integer wednesdayHours, Integer thursdayHours, Integer fridayHours, Integer saturdayHours){
        this.mondayHours = mondayHours;
        this.tuesdayHours = tuesdayHours;
        this.wednesdayHours = wednesdayHours;
        this.thursdayHours = thursdayHours;
        this.fridayHours = fridayHours;
        this.saturdayHours = saturdayHours;
    }

    public DaysOfWeekHoursSet(String dayOfWeek, Integer hours){
        if (dayOfWeek.equals("Monday")){
            this.mondayHours = hours;
        } else if (dayOfWeek.equals("Tuesday")){
            this.tuesdayHours = hours;
        } else if (dayOfWeek.equals("Wednesday")){
            this.wednesdayHours = hours;
        } else if (dayOfWeek.equals("Thursday")){
            this.thursdayHours = hours;
        }else if (dayOfWeek.equals("Friday")){
            this.fridayHours = hours;
        }else if (dayOfWeek.equals("Saturday")){
            this.saturdayHours = hours;
        }

    }

    public DaysOfWeekHoursSet(){}

    public Integer getId() {
        return id;
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

    public Integer getTotalHours(){
        return this.mondayHours + this.tuesdayHours + this.wednesdayHours + this.thursdayHours + this.fridayHours + this.saturdayHours;
    }
}
