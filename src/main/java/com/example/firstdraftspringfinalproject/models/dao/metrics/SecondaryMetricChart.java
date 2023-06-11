package com.example.firstdraftspringfinalproject.models.dao.metrics;

import com.example.firstdraftspringfinalproject.data.*;
import com.example.firstdraftspringfinalproject.models.dao.Chart;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.Employee;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.Project;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets.Timesheet;
import com.example.firstdraftspringfinalproject.models.enums.MetricsCategory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SecondaryMetricChart extends Chart implements MetricsPayRate, MetricsWorkType, MetricsProject, MetricsEmployee {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TimesheetRepository timesheetRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private LineEntryRepository lineEntryRepository;

    @Autowired
    private WorkTypeRepository workTypeRepository;
    private final MetricsCategory primaryCategory;
    private final String primaryCategorySubject;
    private final Boolean containsSecondaryCategory;
    private final MetricsCategory secondaryCategory;

    private List<String> csvHeaders;


    public SecondaryMetricChart(MetricsCategory primaryCategory, String primaryCategorySubject, MetricsCategory secondaryCategory, EmployeeRepository employeeRepository, TimesheetRepository timesheetRepository, ProjectRepository projectRepository, WorkTypeRepository workTypeRepository) {
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

    public SecondaryMetricChart(MetricsCategory primaryCategory, String primaryCategorySubject, MetricsCategory secondaryCategory) {
        if(primaryCategory == secondaryCategory){
            throw new RuntimeException("Fail");
        }
        this.primaryCategory = primaryCategory;
        this.containsSecondaryCategory = true;
        this.primaryCategorySubject = primaryCategorySubject;
        this.secondaryCategory = secondaryCategory;
    }

    //Getter & Setters
    public String getPrimaryCategorySubject() {
        return primaryCategorySubject;
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




//    @Override
//    public void populateChartData(){
//
//        if(this.secondaryCategory == MetricsCategory.NOSECONDARYCATEGORY){
//            throw new RuntimeException("Sorry, can not populate data for a chart with a secondary category " +
//                    "when secondary category is null. Please consider populating a chart with only a primary category.");
//        }
//        if (employeeRepository.findByFirstNameLastNameCombo(this.primaryCategorySubject).isEmpty()) {
//            throw new RuntimeException("Sorry, can not populate data for this topic, as it is not in the Database.");
//        }
//
//        HashMap<String, Integer> xyValues = new HashMap<>();
//        this.csvHeaders = SecondaryMetricChart.loadCsvHeaders(this.primaryCategory, this.secondaryCategory, this.primaryCategorySubject);
//        String xChoice = this.secondaryCategory.getDisplayName();
//        switch (this.primaryCategory.getDisplayName()) {
//            case "Employee" -> {
//                this.setTitle(this.primaryCategorySubject + "'s Hours by " + this.secondaryCategory);
//                Employee employee = employeeRepository.findByFirstNameLastNameCombo(this.primaryCategorySubject).get();
//                List<Timesheet> employeesTimesheets = timesheetRepository.findByEmployeeIdAndCompletionStatusAndSupervisorApproval(employee.getId(), true, true);
//                if (xChoice.equals("Project")) {
//                    xyValues = MetricsProject.loadXyValuesForSecondaryCategoryProject(projectRepository, employeesTimesheets);
//                }else if (xChoice.equals("WorkType")) {
//                    xyValues = MetricsWorkType.loadXyValuesForSecondaryCategoryWorkType(workTypeRepository, employeesTimesheets);
//                }
//                this.setXyValues(xyValues);
//                //TODO if xChoice.equals("PayRate") !!!
//            }
//            case "Project" -> {
//                String projectName = this.primaryCategorySubject;
//                this.setTitle("Hours worked on " + projectName + " by " + xChoice);
//                Project project = projectRepository.findByProjectName(projectName);
//                List<Timesheet> timesheets = timesheetRepository.findBySupervisorApprovalAndCompletionStatus(true, true);
//                switch (xChoice) {
//                    case "Employee" -> {
//                        this.setXyValues(MetricsEmployee.loadXyValuesForSecondaryCategoryEmployeeWhenPrimaryCategoryIsProject(employeeRepository, timesheetRepository, project));
//                    }
//                    case "WorkType" -> {
//                        this.setXyValues(MetricsWorkType.loadXyValuesForSecondaryCategoryWorkTypeWhenPrimaryCategoryIsProject(timesheets, project));
//                    }
//                    case "PayRate" -> {
//                        this.setXyValues(MetricsPayRate.loadXyValuesForSecondaryCategoryPayRateWhenPrimaryCategoryIsProject(timesheets, project));
//                    }
//                }
//            }
//            case "WorkType" -> {
//                String workTypeName = this.primaryCategorySubject;
//                this.setTitle("Hours worked in " + workTypeName + ", by " + xChoice);
//                switch (xChoice) {
//                    case "Employee" -> {
//                        this.setXyValues(MetricsEmployee.loadXyValuesForSecondaryCategoryEmployeeWhenPrimaryCategoryIsWorkType(timesheetRepository, workTypeName));
//                    }
//                    case "Project" -> {
//                        this.setXyValues(MetricsProject.loadXyValuesForSecondaryCategoryProjectWhenPrimaryCategoryIsWorkType(timesheetRepository, workTypeName));
//                    }
//                    case "PayRate" -> {
//                        this.setXyValues(MetricsPayRate.loadXyValuesForSecondaryCategoryPayRateWhenPrimaryCategoryIsWorkType(timesheetRepository, workTypeName));
//                    }
//                }
//            }
//            case "PayRate" -> {
//                String payRateString = this.primaryCategorySubject;
//                this.setTitle("Hours worked compensated at $" + payRateString + " / per hour by " + xChoice);
//                switch (xChoice) {
//                    case "Employee" -> {
//                        this.setXyValues(MetricsEmployee.loadXyValuesForSecondaryCategoryEmployeeWhenPrimaryCategoryIsPayRate(timesheetRepository, payRateString));
//                    }
//                    case "Project" -> {
//                        this.setXyValues(MetricsProject.getXyValuesForSecondaryCategoryProjectWhenPrimaryCategoryIsPayRate(timesheetRepository, payRateString));
//                    }
//                    case "WorkType" -> {
//                        this.setXyValues(MetricsWorkType.loadXyValuesForSecondaryCategoryWorkTypeWhenPrimaryCategoryIsPayRate(timesheetRepository, payRateString));
//                    }
//                }
//            }
//        }
//    }


    @Override
    public void populateChartData(){
        if(this.secondaryCategory == MetricsCategory.NOSECONDARYCATEGORY){
            throw new RuntimeException("Sorry, can not populate data for a chart with a secondary category " +
                    "when secondary category is null. Please consider populating a chart with only a primary category.");
        }
        if (employeeRepository.findByFirstNameLastNameCombo(this.primaryCategorySubject).isEmpty()) {
            throw new RuntimeException("Sorry, can not populate data for this topic, as it is not in the Database.");
        }

        HashMap<String, Integer> xyValues = new HashMap<>();
        this.csvHeaders = SecondaryMetricChart.loadCsvHeaders(this.primaryCategory, this.secondaryCategory, this.primaryCategorySubject);
        String xChoice = this.secondaryCategory.getDisplayName();
        switch (this.primaryCategory.getDisplayName()) {
            case "Employee" -> {
                this.setTitle(this.primaryCategorySubject + "'s Hours by " + this.secondaryCategory);
                Employee employee = employeeRepository.findByFirstNameLastNameCombo(this.primaryCategorySubject).get();
                List<Timesheet> employeesTimesheets = timesheetRepository.findByEmployeeIdAndCompletionStatusAndSupervisorApproval(employee.getId(), true, true);
                if (xChoice.equals("Project")) {
//                    xyValues = MetricsProject.loadXyValuesForSecondaryCategoryProject(projectRepository, employeesTimesheets);
                    this.setXyValues(Chart.populateChartDataFromList(lineEntryRepository.findAllApprovedHoursOfEmployeeBrokenOutByProject()));
                }else if (xChoice.equals("WorkType")) {
//                    xyValues = MetricsWorkType.loadXyValuesForSecondaryCategoryWorkType(workTypeRepository, employeesTimesheets);
                    this.setXyValues(Chart.populateChartDataFromList(lineEntryRepository.findAllApprovedHoursOfEmployeeBrokenOutByWorktype()));
                }
                this.setXyValues(xyValues);
                //TODO if xChoice.equals("PayRate") !!!
            }
            case "Project" -> {
                String projectName = this.primaryCategorySubject;
                this.setTitle("Hours worked on " + projectName + " by " + xChoice);
                Project project = projectRepository.findByProjectName(projectName);
                List<Timesheet> timesheets = timesheetRepository.findBySupervisorApprovalAndCompletionStatus(true, true);
                switch (xChoice) {
                    case "Employee" -> {
                        this.setXyValues(MetricsEmployee.loadXyValuesForSecondaryCategoryEmployeeWhenPrimaryCategoryIsProject(employeeRepository, timesheetRepository, project));
                    }
                    case "WorkType" -> {
                        this.setXyValues(MetricsWorkType.loadXyValuesForSecondaryCategoryWorkTypeWhenPrimaryCategoryIsProject(timesheets, project));
                    }
                    case "PayRate" -> {
                        this.setXyValues(MetricsPayRate.loadXyValuesForSecondaryCategoryPayRateWhenPrimaryCategoryIsProject(timesheets, project));
                    }
                }
            }
            case "WorkType" -> {
                String workTypeName = this.primaryCategorySubject;
                this.setTitle("Hours worked in " + workTypeName + ", by " + xChoice);
                switch (xChoice) {
                    case "Employee" -> {
                        this.setXyValues(MetricsEmployee.loadXyValuesForSecondaryCategoryEmployeeWhenPrimaryCategoryIsWorkType(timesheetRepository, workTypeName));
                    }
                    case "Project" -> {
                        this.setXyValues(MetricsProject.loadXyValuesForSecondaryCategoryProjectWhenPrimaryCategoryIsWorkType(timesheetRepository, workTypeName));
                    }
                    case "PayRate" -> {
                        this.setXyValues(MetricsPayRate.loadXyValuesForSecondaryCategoryPayRateWhenPrimaryCategoryIsWorkType(timesheetRepository, workTypeName));
                    }
                }
            }
            case "PayRate" -> {
                String payRateString = this.primaryCategorySubject;
                this.setTitle("Hours worked compensated at $" + payRateString + " / per hour by " + xChoice);
                switch (xChoice) {
                    case "Employee" -> {
                        this.setXyValues(MetricsEmployee.loadXyValuesForSecondaryCategoryEmployeeWhenPrimaryCategoryIsPayRate(timesheetRepository, payRateString));
                    }
                    case "Project" -> {
                        this.setXyValues(MetricsProject.getXyValuesForSecondaryCategoryProjectWhenPrimaryCategoryIsPayRate(timesheetRepository, payRateString));
                    }
                    case "WorkType" -> {
                        this.setXyValues(MetricsWorkType.loadXyValuesForSecondaryCategoryWorkTypeWhenPrimaryCategoryIsPayRate(timesheetRepository, payRateString));
                    }
                }
            }
        }
    }

//    @Override
//    public void populateChartData(){
//
//        switch (this.primaryCategory.getDisplayName()) {
//            case "Employee" ->
//                    this.setXyValues(Chart.populateChartDataFromList(employeeRepository.findAllEmployeesFirstNameLastNameComboAndTotalHoursWorkedToDate()));
//            case "Project" ->
//                    this.setXyValues(Chart.populateChartDataFromList(lineEntryRepository.findAllApprovedHoursByProject()));
//            case "WorkType" ->
//                    this.setXyValues(Chart.populateChartDataFromList(lineEntryRepository.findAllApprovedHoursByWorkType()));
//            case "PayRate" ->
//                    this.setXyValues(Chart.populateChartDataFromList(lineEntryRepository.findAllApprovedHoursByPayRate()));
//        }
//        this.setTitle(primaryCategory.getDisplayName());
//        this.csvHeaders = PrimaryMetricChart.loadCsvHeaders(this.primaryCategory);
//    }
}
