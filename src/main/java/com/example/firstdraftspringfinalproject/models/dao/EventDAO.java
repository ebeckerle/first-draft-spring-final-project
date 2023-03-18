package com.example.firstdraftspringfinalproject.models.dao;

import com.example.firstdraftspringfinalproject.models.Event;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Calendar;
public class EventDAO {


    private String name;

    private Calendar startDate;
    private Calendar endDate;

    private Integer colorCode;

    public EventDAO(Event event, Integer colorCode){
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

    public Integer getColorCode() {
        return colorCode;
    }

    public void setColorCode(Integer colorCode) {
        this.colorCode = colorCode;
    }

    //TODO - I would like to spiffy up these two toString methods, so they are as dry as can be and return
    // this format "mm/dd/yyyy"; also need to consider if the date fields are saved when the calendar fields
    // are saved... still unsure best practices on Date Class over Calendar class...
    public String toStringStartDate(){
        if(this.startDate == null){
            return "No Start Date Listed";
        }
        String stringToReturn = this.startDate.toString();

//        return this.calStartDate.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.US) + ' '
//                + this.calStartDate.getDisplayName(Calendar.DAY_OF_MONTH, Calendar.SHORT, Locale.US)+ ' '
//                + this.calStartDate.getDisplayName(Calendar.YEAR, Calendar.LONG, Locale.US);

        return stringToReturn;
    }

    public String toStringEndDate(){
        if(this.endDate == null){
            return "No End Date Listed";
        }
        return this.endDate.toString();
    }
}
