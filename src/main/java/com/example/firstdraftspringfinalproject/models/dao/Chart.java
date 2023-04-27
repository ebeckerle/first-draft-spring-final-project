package com.example.firstdraftspringfinalproject.models.dao;

import java.util.HashMap;

public class Chart {

    private HashMap<String, Integer> xyValues;

    private String title;

    public HashMap<String, Integer> getXyValues() {
        return xyValues;
    }

    public void setXyValues(HashMap<String, Integer> xyValues) {
        this.xyValues = xyValues;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void populateChartData(){

    }


}
