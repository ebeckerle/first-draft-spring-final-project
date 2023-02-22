package com.example.firstdraftspringfinalproject.models;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@Entity
public class Event {


    @GeneratedValue
    @Id
    Integer id;

    @Temporal(TemporalType.DATE)
    Date startDate;
    @Temporal(TemporalType.DATE)
    Date endDate;
    @Temporal(TemporalType.DATE)
    Calendar calStartDate;

    String calStartDateMonth;
    @Temporal(TemporalType.DATE)
    Calendar calEndDate;

    String calEndDateMonth;

    @Size(max = 80)
    String name;

    //TODO - create an Event Type Enum with : GENERAL, Employee Birthday, Holiday, shipment-OUTgoing, Shipment-Incoming...?
    // some way to address which events are connected with shipments, and if so incoming or outgoing?
    //    EventType type;

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

    public String getCalStartDateToString(){
        String calStartDate = this.calStartDate.getDisplayName(Calendar.DAY_OF_MONTH, Calendar.SHORT_STANDALONE, Locale.US);
        System.out.println(this.calStartDate);
//        return this.calStartDate.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.US) + ' '
//                + this.calStartDate.getDisplayName(Calendar.DAY_OF_MONTH, Calendar.SHORT, Locale.US)+ ' '
//                + this.calStartDate.getDisplayName(Calendar.YEAR, Calendar.LONG, Locale.US);
        return "Start Date " + calStartDate;
    }

    public String getCalEndDateToString(){
        return this.calEndDate.getDisplayName(1,1,Locale.US);
    }
}
