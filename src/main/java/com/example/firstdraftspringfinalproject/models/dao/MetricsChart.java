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

public class MetricsChart {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TimesheetRepository timesheetRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private WorkTypeRepository workTypeRepository;

    private MetricsCategory primaryCategory;
//    private String primaryCategory;

    private String primaryCategorySubject;
    private Boolean containsSecondaryCategory;
    private MetricsCategory secondaryCategory;

//    private String secondaryCategory;
    private String chartTitle;
    private HashMap<String, Integer> xyValues;
    private List<String> csvHeaders;

    public MetricsChart(MetricsCategory primaryCategory) {
        this.primaryCategory = primaryCategory;
        this.containsSecondaryCategory = false;
    }
    public MetricsChart(MetricsCategory primaryCategory, EmployeeRepository employeeRepository, TimesheetRepository timesheetRepository, ProjectRepository projectRepository, WorkTypeRepository workTypeRepository) {
        this.primaryCategory = primaryCategory;
        this.containsSecondaryCategory = false;
        this.employeeRepository = employeeRepository;
        this.timesheetRepository = timesheetRepository;
        this.projectRepository = projectRepository;
        this.workTypeRepository = workTypeRepository;
    }

    public MetricsChart(MetricsCategory primaryCategory, String primaryCategorySubject, MetricsCategory secondaryCategory) {
        this.primaryCategory = primaryCategory;
        this.containsSecondaryCategory = true;
        this.primaryCategorySubject = primaryCategorySubject;
        this.secondaryCategory = secondaryCategory;
    }
    public MetricsChart(MetricsCategory primaryCategory, String primaryCategorySubject, MetricsCategory secondaryCategory, EmployeeRepository employeeRepository, TimesheetRepository timesheetRepository, ProjectRepository projectRepository, WorkTypeRepository workTypeRepository) {
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

    public MetricsCategory getPrimaryCategory() {
        return primaryCategory;
    }

    public void setPrimaryCategory(MetricsCategory primaryCategory) {
        this.primaryCategory = primaryCategory;
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

    public MetricsCategory getSecondaryCategory() {
        return secondaryCategory;
    }

    public void setSecondaryCategory(MetricsCategory secondaryCategory) {
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

    public List<String> getCsvHeaders() {
        return csvHeaders;
    }

    public void populateChartDataWhenThereIsNoSecondaryCategory(){
        HashMap<String, Integer> xyValues = new HashMap<>();
        ArrayList<String> csvHeaders = new ArrayList<>();
        csvHeaders.add(this.primaryCategory);
        csvHeaders.add("Hours");
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
                Integer totalHoursForProject = 0;
                for (Timesheet timesheet:
                        timesheetRepository.findBySupervisorApprovalAndCompletionStatus(true, true)) {
                    totalHoursForProject += timesheet.getTotalHoursByProject(project);
                }
                xyValues.put(project.getProjectName(), totalHoursForProject);
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
        this.csvHeaders = csvHeaders;
    }

    public void setXyValuesWhenThereIsASecondaryCategory(){
        HashMap<String, Integer> xyValues = new HashMap<>();
        String chartTitle = "Chart Title";
        ArrayList<String> csvHeaders = new ArrayList<>();
        csvHeaders.add(this.primaryCategory);
        csvHeaders.add(this.secondaryCategory);
        csvHeaders.add("Hours");
        if (this.primaryCategory.equals("Employee")){
            String employee = this.primaryCategorySubject;
            String xChoice = this.secondaryCategory;
            chartTitle = this.primaryCategorySubject + "'s Hours by "+this.secondaryCategory;
            Employee employee1;
            if (employeeRepository.findByFirstNameLastNameCombo(employee).isPresent()){
                employee1 = employeeRepository.findByFirstNameLastNameCombo(employee).get();
                List<Timesheet> employeesTimesheets = timesheetRepository.findByEmployeeEmployeeIdAndCompletionStatusAndSupervisorApproval(employee1.getEmployeeId(), true, true);
                if(xChoice.equals("Project")){
                    List<Project> projects = (List<Project>) projectRepository.findAll();
                    Integer totalHoursForX = 0;
                    for (Project aProject:
                            projects) {
                        totalHoursForX = 0;
                        for (Timesheet timesheet : employeesTimesheets){
                            totalHoursForX += timesheet.getTotalHoursByProject(aProject);
                            xyValues.put(aProject.toString(), totalHoursForX);
                        }
                    }
                }
                if(xChoice.equals("WorkType")){
                    List<WorkType> workTypes = (List<WorkType>) workTypeRepository.findAll();
                    Integer totalHoursForX = 0;
                    for (WorkType aWorkType      :
                            workTypes) {
                        totalHoursForX = 0;
                        for (Timesheet timesheet : employeesTimesheets){
                            totalHoursForX += timesheet.getTotalHoursByWorkType(aWorkType);
                            xyValues.put(aWorkType.toString(), totalHoursForX);
                        }
                    }
                }
            }

        } else if (this.primaryCategory.equals("Project")){
            String project = this.primaryCategorySubject;
            String xChoice = this.secondaryCategory;
            chartTitle = "Hours worked on " + project + " by "+xChoice;
            Project project1 = projectRepository.findByProjectName(project);
            List<Timesheet> timesheets = timesheetRepository.findBySupervisorApprovalAndCompletionStatus(true, true);
            if(xChoice.equals("Employee")){
                List<Employee> employees = (List<Employee>) employeeRepository.findAll();
                for (Employee aEmployee: employees) {
                    List<Timesheet> employeesTimesheets = timesheetRepository.findByEmployeeEmployeeIdAndCompletionStatusAndSupervisorApproval(aEmployee.getEmployeeId(), true, true);
                    Integer totalHoursForX = 0;
                    for (Timesheet timesheet : employeesTimesheets){
                        totalHoursForX += timesheet.getTotalHoursByProject(project1);
                        xyValues.put(aEmployee.toString(), totalHoursForX);
                    }
                }
            }
            if(xChoice.equals("WorkType")) {
                for (Timesheet timesheet : timesheets) {
                    List<LineEntry> lineEntries = timesheet.getLineEntries();
                    for (LineEntry lineEntry :
                            lineEntries) {
                        Integer totalHoursForX = 0;
                        ProjectWorkTypeSet projectWorkTypeSet = lineEntry.getProjectWorkTypeCombo();
                        if (projectWorkTypeSet.getProject().equals(project1)) {
                            if (xyValues.containsKey(projectWorkTypeSet.getWorkType().toString())) {
                                totalHoursForX += xyValues.get(projectWorkTypeSet.getWorkType().toString());
                                totalHoursForX += lineEntry.getTotalHours();
                                xyValues.replace(projectWorkTypeSet.getWorkType().toString(), totalHoursForX);
                            } else {
                                totalHoursForX += lineEntry.getTotalHours();
                                xyValues.put(projectWorkTypeSet.getWorkType().toString(), totalHoursForX);
                            }
                        }
                    }
                }
            }
            if(xChoice.equals("PayRate")){
                List<String> payRates = new ArrayList<>();
                for (Timesheet timesheet:
                        timesheets) {
                    List<LineEntry> lineEntries = timesheet.getLineEntries();
                    for (LineEntry lineEntry :
                            lineEntries) {
                        ProjectWorkTypeSet projectWorkTypeSet = lineEntry.getProjectWorkTypeCombo();
                        if (projectWorkTypeSet.getProject().equals(project1)) {
                            String payRate1 = "$"+timesheet.getCurrentPayRate()+" per hour";
                            if (payRates.contains(payRate1)) {
                                Integer existingHourTotal = xyValues.get(payRate1);
                                Integer newHourTotal = existingHourTotal + lineEntry.getTotalHours();
                                xyValues.replace(payRate1, newHourTotal);
                            } else {
                                payRates.add(payRate1);
                                xyValues.put(payRate1, lineEntry.getTotalHours());
                            }
                        }
                    }
                }
            }

        } else if (this.primaryCategory.equals("WorkType")){

            String workType = this.primaryCategorySubject;
            String xChoice = this.secondaryCategory;
            chartTitle = "Hours worked in " + workType + ", by "+xChoice;
            if(xChoice.equals("Employee")){
                for (Timesheet timesheet:
                        timesheetRepository.findBySupervisorApprovalAndCompletionStatus(true, true)) {
                    for (LineEntry lineEntry:
                            timesheet.getLineEntries()) {
                        String workType1 = lineEntry.getProjectWorkTypeCombo().getWorkType().toString();
                        if (workType.equals(workType1)){
                            if (xyValues.containsKey(timesheet.getEmployee().toString())){
                                Integer existingHourTotal = xyValues.get(timesheet.getEmployee().toString());
                                Integer newHourTotal = existingHourTotal + lineEntry.getTotalHours();
                                xyValues.replace(timesheet.getEmployee().toString(), newHourTotal);
                            }else{
                                xyValues.put(timesheet.getEmployee().toString(), lineEntry.getTotalHours());
                            }
                        }
                    }
                }
            }
            if(xChoice.equals("Project")){
                for (Timesheet timesheet:
                        timesheetRepository.findBySupervisorApprovalAndCompletionStatus(true, true)) {
                    for (LineEntry lineEntry:
                            timesheet.getLineEntries()) {
                        String workType1 = lineEntry.getProjectWorkTypeCombo().getWorkType().toString();
                        if(workType.equals(workType1)){
                            if (xyValues.containsKey(lineEntry.getProjectWorkTypeCombo().getProject().toString())){
                                Integer existingHourTotal = xyValues.get(lineEntry.getProjectWorkTypeCombo().getProject().toString());
                                Integer newHourTotal = existingHourTotal + lineEntry.getTotalHours();
                                xyValues.replace(lineEntry.getProjectWorkTypeCombo().getProject().toString(), newHourTotal);
                            }else{
                                xyValues.put(lineEntry.getProjectWorkTypeCombo().getProject().toString(), lineEntry.getTotalHours());
                            }
                        }
                    }
                }
            }
            if(xChoice.equals("PayRate")){
                for (Timesheet timesheet:
                        timesheetRepository.findBySupervisorApprovalAndCompletionStatus(true, true)) {
                    for (LineEntry lineEntry:
                            timesheet.getLineEntries()) {
                        String workType1 = lineEntry.getProjectWorkTypeCombo().getWorkType().toString();
                        if(workType.equals(workType1)){
                            String payRate1 = "$"+timesheet.getCurrentPayRate()+" per hour";
                            if (xyValues.containsKey(payRate1)) {
                                Integer existingHourTotal = xyValues.get(payRate1);
                                Integer newHourTotal = existingHourTotal + lineEntry.getTotalHours();
                                xyValues.replace(payRate1, newHourTotal);
                            } else {
                                xyValues.put(payRate1, lineEntry.getTotalHours());
                            }
                        }
                    }
                }
            }
        } else if (this.primaryCategory.equals("PayRate")){
            String payRate = this.primaryCategorySubject;
            String xChoice = this.secondaryCategory;
            chartTitle = "Hours worked compensated at $" + payRate + " / per hour by "+xChoice;
            if(xChoice.equals("Employee")){
                for (Timesheet timesheet:
                        timesheetRepository.findBySupervisorApprovalAndCompletionStatus(true, true)) {
                    if(payRate.equals(timesheet.getCurrentPayRate().toString())){
                        if(xyValues.containsKey(timesheet.getEmployee().toString())){
                            Integer existingHourTotal = xyValues.get(timesheet.getEmployee().toString());
                            Integer newHourTotal = existingHourTotal + timesheet.getTotalHours();
                            xyValues.put(timesheet.getEmployee().toString(), newHourTotal);
                        }else{
                            xyValues.put(timesheet.getEmployee().toString(), timesheet.getTotalHours());
                        }
                    }
                }
            }
            if(xChoice.equals("Project")){
                for (Timesheet timesheet:
                        timesheetRepository.findBySupervisorApprovalAndCompletionStatus(true, true)) {
                    for (LineEntry lineEntry:
                            timesheet.getLineEntries()) {
                        if(payRate.equals(timesheet.getCurrentPayRate().toString())){
                            if(xyValues.containsKey(lineEntry.getProjectWorkTypeCombo().getProject().toString())){
                                Integer existingHourTotal = xyValues.get(lineEntry.getProjectWorkTypeCombo().getProject().toString());
                                Integer newHourTotal = existingHourTotal + lineEntry.getTotalHours();
                                xyValues.put(lineEntry.getProjectWorkTypeCombo().getProject().toString(), newHourTotal);
                            }else{
                                xyValues.put(lineEntry.getProjectWorkTypeCombo().getProject().toString(), lineEntry.getTotalHours());
                            }
                        }
                    }
                }
            }
            if(xChoice.equals("WorkType")){
                for (Timesheet timesheet:
                        timesheetRepository.findBySupervisorApprovalAndCompletionStatus(true, true)) {
                    for (LineEntry lineEntry:
                            timesheet.getLineEntries()) {
                        if(payRate.equals(timesheet.getCurrentPayRate().toString())){
                            if(xyValues.containsKey(lineEntry.getProjectWorkTypeCombo().getWorkType().toString())){
                                Integer existingHourTotal = xyValues.get(lineEntry.getProjectWorkTypeCombo().getWorkType().toString());
                                Integer newHourTotal = existingHourTotal + lineEntry.getTotalHours();
                                xyValues.put(lineEntry.getProjectWorkTypeCombo().getWorkType().toString(), newHourTotal);
                            }else{
                                xyValues.put(lineEntry.getProjectWorkTypeCombo().getWorkType().toString(), lineEntry.getTotalHours());
                            }
                        }
                    }
                }
            }
        }

        this.chartTitle = chartTitle;
        this.xyValues = xyValues;
        this.csvHeaders = csvHeaders;
    }
}
