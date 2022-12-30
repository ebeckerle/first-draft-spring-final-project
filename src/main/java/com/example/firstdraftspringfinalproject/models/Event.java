package com.example.firstdraftspringfinalproject.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Calendar;
import java.util.Date;

@Entity
public class Event {


    @GeneratedValue
    @Id
    Integer id;


    Date startDate;

    Date endDate;

    Calendar calStartDate;

    Calendar calEndDate;

    String name;

    public Event(Date startDate, Date endDate, String name) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.name = name;
    }

    public Event(Calendar calStartDate, Calendar calEndDate, String name) {
        this.calStartDate = calStartDate;
        this.calEndDate = calEndDate;
        this.name = name;
    }

    public Event() {
    }

    public Integer getId() {
        return id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Calendar getCalStartDate() {
        return calStartDate;
    }

    public void setCalStartDate(Calendar calStartDate) {
        this.calStartDate = calStartDate;
    }

    public Calendar getCalEndDate() {
        return calEndDate;
    }

    public void setCalEndDate(Calendar calEndDate) {
        this.calEndDate = calEndDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
