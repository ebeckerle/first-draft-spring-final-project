package com.example.firstdraftspringfinalproject.models.dao;

import com.example.firstdraftspringfinalproject.data.EmployeeRepository;
import com.example.firstdraftspringfinalproject.data.ProjectRepository;
import com.example.firstdraftspringfinalproject.data.TimesheetRepository;
import com.example.firstdraftspringfinalproject.data.WorkTypeRepository;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.*;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets.LineEntry;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets.ProjectWorkTypeSet;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets.Timesheet;
import com.example.firstdraftspringfinalproject.models.enums.MetricsCategory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//I think this is a Data Access Object???

public class MetricsChart implements MetricsEmployeePayRate, MetricsWorkType, MetricsProject, MetricsEmployee{

    @Autowired
    private final EmployeeRepository employeeRepository;

    @Autowired
    private final TimesheetRepository timesheetRepository;

    @Autowired
    private final ProjectRepository projectRepository;

    @Autowired
    private final WorkTypeRepository workTypeRepository;

    private final MetricsCategory primaryCategory;
    private final String primaryCategorySubject;
    private final Boolean containsSecondaryCategory;
    private final MetricsCategory secondaryCategory;

    private String chartTitle;
    private HashMap<String, Integer> xyValues;
    private List<String> csvHeaders;

    public MetricsChart(MetricsCategory primaryCategory, EmployeeRepository employeeRepository, TimesheetRepository timesheetRepository, ProjectRepository projectRepository, WorkTypeRepository workTypeRepository) {
        if(timesheetRepository.count() == 0){
            throw new RuntimeException("Fail, there are no timesheets");
        }
        this.primaryCategory = primaryCategory;
        this.primaryCategorySubject = "There is no secondary category, so there is no primary category subject.";
        this.containsSecondaryCategory = false;
        this.secondaryCategory = MetricsCategory.NOSECONDARYCATEGORY;
        this.employeeRepository = employeeRepository;
        this.timesheetRepository = timesheetRepository;
        this.projectRepository = projectRepository;
        this.workTypeRepository = workTypeRepository;
    }

    public MetricsChart(MetricsCategory primaryCategory, String primaryCategorySubject, MetricsCategory secondaryCategory, EmployeeRepository employeeRepository, TimesheetRepository timesheetRepository, ProjectRepository projectRepository, WorkTypeRepository workTypeRepository) {
        if(timesheetRepository.count() == 0){
            throw new RuntimeException("Fail, there are no timesheets");
        }
        if(primaryCategory == secondaryCategory){
            throw new RuntimeException("Fail");
        }
        this.primaryCategory = primaryCategory;
        this.containsSecondaryCategory = true;
        this.primaryCategorySubject = primaryCategorySubject;
        this.secondaryCategory = secondaryCategory;
        this.employeeRepository = employeeRepository;
        this.timesheetRepository = timesheetRepository;
        this.projectRepository = projectRepository;
        this.workTypeRepository = workTypeRepository;
    }

    //Getter & Setters

    public Boolean getContainsSecondaryCategory() {
        return containsSecondaryCategory;
    }


    public String getPrimaryCategorySubject() {
        return primaryCategorySubject;
    }

    public String getChartTitle() {
        return chartTitle;
    }

    public HashMap<String, Integer> getXyValues() {
        return xyValues;
    }

    public List<String> getCsvHeaders() {
        return csvHeaders;
    }

    //METHODS

    public static ArrayList<String> loadCsvHeaders(MetricsCategory primaryCategory, MetricsCategory secondaryCategory, String primaryCategorySubject){
        ArrayList<String> csvHeaders = new ArrayList<>();
        if(secondaryCategory == MetricsCategory.NOSECONDARYCATEGORY){
            csvHeaders.add(primaryCategory.getDisplayName());
        } else {
            csvHeaders.add(primaryCategory.getDisplayName());
            csvHeaders.add(secondaryCategory.getDisplayName());
        }
        csvHeaders.add("Hours");
        return csvHeaders;
    }

    public void populateChartDataWhenThereIsNoSecondaryCategory(){

        switch (this.primaryCategory.getDisplayName()) {
            case "Employee" ->
                    this.xyValues = MetricsEmployee.loadXyValuesForPrimaryCategoryEmployee(timesheetRepository, employeeRepository);
            case "Project" ->
                    this.xyValues = MetricsProject.loadXyValuesForPrimaryCategoryProject(timesheetRepository, projectRepository);
            case "WorkType" ->
                    this.xyValues = MetricsWorkType.loadXyValuesForPrimaryCategoryWorkType(timesheetRepository, workTypeRepository);
            case "PayRate" ->
                    this.xyValues = MetricsEmployeePayRate.loadXyValuesForEmployeePayRate(timesheetRepository);
        }
        this.chartTitle = primaryCategory.getDisplayName();
        this.csvHeaders = MetricsChart.loadCsvHeaders(this.primaryCategory, this.secondaryCategory, this.primaryCategorySubject);
    }


    public void populateChartDataWhenThereIsASecondaryCategory(){

        if(this.secondaryCategory == MetricsCategory.NOSECONDARYCATEGORY){
            throw new RuntimeException("Sorry, can not populate data for a chart with a secondary category " +
                    "when secondary category is null. Please consider populating a chart with only a primary category.");
        }
        if (employeeRepository.findByFirstNameLastNameCombo(this.primaryCategorySubject).isEmpty()) {
            throw new RuntimeException("Sorry, can not populate data for this topic, as it is not in the Database.");
        }

        HashMap<String, Integer> xyValues = new HashMap<>();
        this.csvHeaders = MetricsChart.loadCsvHeaders(this.primaryCategory, this.secondaryCategory, this.primaryCategorySubject);
        String xChoice = this.secondaryCategory.getDisplayName();
        switch (this.primaryCategory.getDisplayName()) {
            case "Employee" -> {
                this.chartTitle  = this.primaryCategorySubject + "'s Hours by " + this.secondaryCategory;
                Employee employee = employeeRepository.findByFirstNameLastNameCombo(this.primaryCategorySubject).get();
                List<Timesheet> employeesTimesheets = timesheetRepository.findByEmployeeEmployeeIdAndCompletionStatusAndSupervisorApproval(employee.getEmployeeId(), true, true);
                if (xChoice.equals("Project")) {
                    xyValues = MetricsProject.loadXyValuesForSecondaryCategoryProject(projectRepository, employeesTimesheets);
                }else if (xChoice.equals("WorkType")) {
                    xyValues = MetricsWorkType.loadXyValuesForSecondaryCategoryWorkType(workTypeRepository, employeesTimesheets);
                }
                this.xyValues = xyValues;
                //TODO if xChoice.equals("PayRate") !!!
            }
            case "Project" -> {
                String projectName = this.primaryCategorySubject;
                this.chartTitle = "Hours worked on " + projectName + " by " + xChoice;
                Project project = projectRepository.findByProjectName(projectName);
                List<Timesheet> timesheets = timesheetRepository.findBySupervisorApprovalAndCompletionStatus(true, true);
                switch (xChoice) {
                    case "Employee" -> {
                        this.xyValues = MetricsEmployee.loadXyValuesForSecondaryCategoryEmployeeWhenPrimaryCategoryIsProject(employeeRepository, timesheetRepository, project);
                    }
                    case "WorkType" -> {
                        this.xyValues = MetricsWorkType.loadXyValuesForSecondaryCategoryWorkTypeWhenPrimaryCategoryIsProject(timesheets, project);
                    }
                    case "PayRate" -> {
                        this.xyValues = MetricsEmployeePayRate.loadXyValuesForSecondaryCategoryPayRateWhenPrimaryCategoryIsProject(timesheets, project);
                    }
                }
            }
            case "WorkType" -> {
                String workTypeName = this.primaryCategorySubject;
                this.chartTitle = "Hours worked in " + workTypeName + ", by " + xChoice;
                switch (xChoice) {
                    case "Employee" -> {
                        this.xyValues = MetricsEmployee.loadXyValuesForSecondaryCategoryEmployeeWhenPrimaryCategoryIsWorkType(timesheetRepository, workTypeName);
                    }
                    case "Project" -> {
                        this.xyValues = MetricsProject.loadXyValuesForSecondaryCategoryProjectWhenPrimaryCategoryIsWorkType(timesheetRepository, workTypeName);
                    }
                    case "PayRate" -> {
                        this.xyValues = MetricsEmployeePayRate.loadXyValuesForSecondaryCategoryPayRateWhenPrimaryCategoryIsWorkType(timesheetRepository, workTypeName);
                    }
                }
            }
            case "PayRate" -> {
                String payRateString = this.primaryCategorySubject;
                chartTitle = "Hours worked compensated at $" + payRateString + " / per hour by " + xChoice;
                switch (xChoice) {
                    case "Employee" -> {
                        this.xyValues = MetricsEmployee.loadXyValuesForSecondaryCategoryEmployeeWhenPrimaryCategoryIsPayRate(timesheetRepository, payRateString);
                    }
                    case "Project" -> {
                        this.xyValues = MetricsProject.getXyValuesForSecondaryCategoryProjectWhenPrimaryCategoryIsPayRate(timesheetRepository, payRateString);
                    }
                    case "WorkType" -> {
                        this.xyValues = MetricsWorkType.loadXyValuesForSecondaryCategoryWorkTypeWhenPrimaryCategoryIsPayRate(timesheetRepository, payRateString);
                    }
                }
            }
        }
    }
}
