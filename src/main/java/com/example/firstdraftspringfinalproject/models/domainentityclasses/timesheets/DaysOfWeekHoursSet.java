package com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

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

        if (dayOfWeek.equalsIgnoreCase("monday")){
            this.mondayHours = hours;
        } else if (dayOfWeek.equalsIgnoreCase("Tuesday")){
            this.tuesdayHours = hours;
        } else if (dayOfWeek.equalsIgnoreCase("Wednesday")){
            this.wednesdayHours = hours;
        } else if (dayOfWeek.equalsIgnoreCase("Thursday")){
            this.thursdayHours = hours;
        }else if (dayOfWeek.equalsIgnoreCase("Friday")){
            this.fridayHours = hours;
        }else if (dayOfWeek.equalsIgnoreCase("Saturday")){
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DaysOfWeekHoursSet that = (DaysOfWeekHoursSet) o;
        return Objects.equals(mondayHours, that.mondayHours) && Objects.equals(tuesdayHours, that.tuesdayHours) && Objects.equals(wednesdayHours, that.wednesdayHours) && Objects.equals(thursdayHours, that.thursdayHours) && Objects.equals(fridayHours, that.fridayHours) && Objects.equals(saturdayHours, that.saturdayHours);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mondayHours, tuesdayHours, wednesdayHours, thursdayHours, fridayHours, saturdayHours);
    }
}
