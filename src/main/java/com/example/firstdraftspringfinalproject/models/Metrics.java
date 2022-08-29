package com.example.firstdraftspringfinalproject.models;

import com.example.firstdraftspringfinalproject.data.EmployeeRepository;
import com.example.firstdraftspringfinalproject.data.ProjectRepository;
import com.example.firstdraftspringfinalproject.data.TimesheetRepository;
import com.example.firstdraftspringfinalproject.data.WorkTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Metrics {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TimesheetRepository timesheetRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private WorkTypeRepository workTypeRepository;

//    private MetricsCategory primaryCategory;
    private String primaryCategory;
    private Boolean containsSecondaryCategory;
    private String primaryCategorySubject;
//    private MetricsCategory secondaryCategory;
    private String secondaryCategory;
    private String chartTitle;
    private HashMap<String, Integer> xyValues;

//    public Metrics(MetricsCategory primaryCategory) {
//        this.primaryCategory = primaryCategory;
//        this.containsSecondaryCategory = false;
//    }
    public Metrics(String primaryCategory, EmployeeRepository employeeRepository, TimesheetRepository timesheetRepository, ProjectRepository projectRepository, WorkTypeRepository workTypeRepository) {
        this.primaryCategory = primaryCategory;
        this.containsSecondaryCategory = false;
        this.employeeRepository = employeeRepository;
        this.timesheetRepository = timesheetRepository;
        this.projectRepository = projectRepository;
        this.workTypeRepository = workTypeRepository;
    }

//    public Metrics(MetricsCategory primaryCategory, String primaryCategorySubject, MetricsCategory secondaryCategory) {
//        this.primaryCategory = primaryCategory;
//        this.containsSecondaryCategory = true;
//        this.primaryCategorySubject = primaryCategorySubject;
//        this.secondaryCategory = secondaryCategory;
//    }
    public Metrics(String primaryCategory, String primaryCategorySubject, String secondaryCategory) {
        this.primaryCategory = primaryCategory;
        this.containsSecondaryCategory = true;
        this.primaryCategorySubject = primaryCategorySubject;
        this.secondaryCategory = secondaryCategory;
    }

    //Getter & Setters

//    public MetricsCategory getPrimaryCategory() {
//        return primaryCategory;
//    }
//
//    public void setPrimaryCategory(MetricsCategory primaryCategory) {
//        this.primaryCategory = primaryCategory;
//    }

    public String getPrimaryCategory() {
        return primaryCategory;
    }

    public void setPrimaryCategory(String primaryCategory) {
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

//    public MetricsCategory getSecondaryCategory() {
//        return secondaryCategory;
//    }
//
//    public void setSecondaryCategory(MetricsCategory secondaryCategory) {
//        this.secondaryCategory = secondaryCategory;
//    }

    public String getSecondaryCategory() {
        return secondaryCategory;
    }

    public void setSecondaryCategory(String secondaryCategory) {
        this.secondaryCategory = secondaryCategory;
    }

    public String getChartTitle() {
        return chartTitle;
    }

    public void setChartTitle(String chartTitle) {
        this.chartTitle = chartTitle;
    }

    public HashMap<String, Integer> getXyValues() {
        return xyValues;
    }


    public void setXyValuesWhenThereIsNoSecondaryCategory(){
        HashMap<String, Integer> xyValues = new HashMap<>();
        if(this.primaryCategory.equals("Employee")){
            List<Employee> employees = (List<Employee>) employeeRepository.findAll();
            for (Employee employee:
                    employees) {
                xyValues.put(employee.getLastName(), employee.getTotalHoursWorkedToDate());
            }
        }
        if (this.primaryCategory.equals("Project")){
            List<Project>  projects = (List<Project>) projectRepository.findAll();
            for (Project project:
                    projects) {
                Project project1 = project;
                Integer totalHoursForProject1 = 0;
                for (Timesheet timesheet:
                        timesheetRepository.findBySupervisorApprovalAndCompletionStatus(true, true)) {
                    totalHoursForProject1 += timesheet.getTotalHoursByProject(project1);
                }
                xyValues.put(project.getProjectName(), totalHoursForProject1);
            }
        }
        if (this.primaryCategory.equals("WorkType")){
            List<WorkType> workTypes = (List<WorkType>) workTypeRepository.findAll();
            for (WorkType workType:
                    workTypes){
                Integer totalHoursForWorkType = 0;
                for (Timesheet timesheet:
                        timesheetRepository.findBySupervisorApprovalAndCompletionStatus(true, true)) {
                    totalHoursForWorkType += timesheet.getTotalHoursByWorkType(workType);
                }
                xyValues.put(workType.toStringWorkTypeCode(), totalHoursForWorkType);
            }
        }
        if (this.primaryCategory.equals("PayRate")){
            List<Integer> payRates = new ArrayList<>();
            for (Timesheet timesheet:
                    timesheetRepository.findBySupervisorApprovalAndCompletionStatus(true, true)) {
                //
                Integer payRate = timesheet.getCurrentPayRate();
                if(payRates.contains(payRate)){
                    Integer existingHourTotal = xyValues.get(String.valueOf(payRate));
                    Integer newHourTotal = existingHourTotal + timesheet.getTotalHours();
                    xyValues.replace(String.valueOf(payRate), newHourTotal);
                }else{
                    payRates.add(payRate);
                    xyValues.put(String.valueOf(payRate), timesheet.getTotalHours());
                }
            }
        }
        this.chartTitle = primaryCategory;
        this.xyValues = xyValues;
    }

    public void setXyValuesWhenThereIsASecondaryCategory(){

    }
}
