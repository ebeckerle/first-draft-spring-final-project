package com.example.firstdraftspringfinalproject.models.dao;

import com.example.firstdraftspringfinalproject.models.Event;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Calendar;
public class EventDAO {


    private String name;

    private Calendar startDate;
    private Calendar endDate;

    private String colorCode;

    public EventDAO(Event event, String colorCode){
        this.name = event.getName();
        this.startDate = event.getCalStartDate();
        this.endDate = event.getCalEndDate();
        this.colorCode = colorCode;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }
}
