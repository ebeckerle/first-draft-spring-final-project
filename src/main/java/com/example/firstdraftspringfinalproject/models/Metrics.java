package com.example.firstdraftspringfinalproject.models;

import com.example.firstdraftspringfinalproject.data.EmployeeRepository;
import com.example.firstdraftspringfinalproject.data.ProjectRepository;
import com.example.firstdraftspringfinalproject.data.TimesheetRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

public class Metrics {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TimesheetRepository timesheetRepository;

    @Autowired
    private ProjectRepository projectRepository;

    private MetricsCategory primaryCategory;
    private Boolean containsSecondaryCategory;
    private String primaryCategorySubject;
    private MetricsCategory secondaryCategory;
    private HashMap<String, Integer> xyValues;

    public Metrics(MetricsCategory primaryCategory) {
        this.primaryCategory = primaryCategory;
    }

    public Metrics(MetricsCategory primaryCategory, Boolean containsSecondaryCategory, String primaryCategorySubject, MetricsCategory secondaryCategory) {
        this.primaryCategory = primaryCategory;
        this.containsSecondaryCategory = containsSecondaryCategory;
        this.primaryCategorySubject = primaryCategorySubject;
        this.secondaryCategory = secondaryCategory;
    }

    public MetricsCategory getPrimaryCategory() {
        return primaryCategory;
    }

    public void setPrimaryCategory(MetricsCategory primaryCategory) {
        this.primaryCategory = primaryCategory;
    }

    public Boolean getContainsSecondaryCategory() {
        return containsSecondaryCategory;
    }

    public void setContainsSecondaryCategory(Boolean containsSecondaryCategory) {
        this.containsSecondaryCategory = containsSecondaryCategory;
    }

    public String getPrimaryCategorySubject() {
        return primaryCategorySubject;
    }

    public void setPrimaryCategorySubject(String primaryCategorySubject) {
        this.primaryCategorySubject = primaryCategorySubject;
    }

    public MetricsCategory getSecondaryCategory() {
        return secondaryCategory;
    }

    public void setSecondaryCategory(MetricsCategory secondaryCategory) {
        this.secondaryCategory = secondaryCategory;
    }

    public HashMap<String, Integer> getXyValues() {
        return xyValues;
    }

    public void setXyValues(HashMap<String, Integer> xyValues) {
        this.xyValues = xyValues;
    }

    public void setXyValuesWhenThereIsNoSecondaryCategory(){
        String primaryCategory = this.primaryCategory.values();
        if(this.primaryCategory.)
    }

    public void setXyValuesWhenThereIsASecondaryCategory(){

    }
}
