package com.example.firstdraftspringfinalproject.models.dao.metrics;

import com.example.firstdraftspringfinalproject.data.*;
import com.example.firstdraftspringfinalproject.models.dao.Chart;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.Employee;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.Project;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.WorkType;
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


    @Override
    public void populateChartData(){
        if(this.secondaryCategory == MetricsCategory.NOSECONDARYCATEGORY){
            throw new RuntimeException("Sorry, can not populate data for a chart with a secondary category " +
                    "when secondary category is null. Please consider populating a chart with only a primary category.");
        }
        if (employeeRepository.findByFirstNameLastNameCombo(this.primaryCategorySubject).isEmpty()) {
            throw new RuntimeException("Sorry, can not populate data for this topic, as it is not in the Database.");
        }

        this.csvHeaders = SecondaryMetricChart.loadCsvHeaders(this.primaryCategory, this.secondaryCategory, this.primaryCategorySubject);
        String xChoice = this.secondaryCategory.getDisplayName();
        switch (this.primaryCategory.getDisplayName()) {
            case "Employee" -> {
                this.setTitle(this.primaryCategorySubject + "'s Hours by " + this.secondaryCategory);
                Employee employee = employeeRepository.findByFirstNameLastNameCombo(this.primaryCategorySubject).get();
                switch (xChoice) {
                    case "Project" ->
                            this.setXyValues(Chart.populateChartDataFromList(lineEntryRepository.findAllApprovedHoursOfEmployeeBrokenOutByProject(employee.getId())));
                    case "WorkType" ->
                            this.setXyValues(Chart.populateChartDataFromList(lineEntryRepository.findAllApprovedHoursOfEmployeeBrokenOutByWorkType(employee.getId())));
                    case "PayRate" ->
                            this.setXyValues(Chart.populateChartDataFromList(lineEntryRepository.findAllApprovedHoursOfEmployeeBrokenOutByPayRate(employee.getId())));
                }
            }
            case "Project" -> {
                String projectName = this.primaryCategorySubject;
                this.setTitle("Hours worked on " + projectName + " by " + xChoice);
                Project project = projectRepository.findByProjectName(projectName);
                switch (xChoice) {
                    case "Employee" -> {
                        this.setXyValues(Chart.populateChartDataFromList(lineEntryRepository.findAllApprovedHoursOfProjectBrokenOutByEmployee(project.getId())));
                    }
                    case "WorkType" -> {
                        this.setXyValues(Chart.populateChartDataFromList(lineEntryRepository.findAllApprovedHoursOfProjectBrokenOutByWorkType(project.getId())));
                    }
                    case "PayRate" -> {
                        this.setXyValues(Chart.populateChartDataFromList(lineEntryRepository.findAllApprovedHoursOfProjectBrokenOutByPayRate(project.getId())));
                    }
                }
            }
            case "WorkType" -> {
                String workTypeName = this.primaryCategorySubject;
                this.setTitle("Hours worked in " + workTypeName + ", by " + xChoice);
                WorkType workType = workTypeRepository.findByWorkDescription(workTypeName);
                switch (xChoice) {
                    case "Employee" -> {
                        this.setXyValues(Chart.populateChartDataFromList(lineEntryRepository.findAllApprovedHoursOfWorkTypeBrokenOutByEmployee(workType.getId())));
                    }
                    case "Project" -> {
                        this.setXyValues(Chart.populateChartDataFromList(lineEntryRepository.findAllApprovedHoursOfWorkTypeBrokenOutByProject(workType.getId())));
                    }
                    case "PayRate" -> {
                        this.setXyValues(Chart.populateChartDataFromList(lineEntryRepository.findAllApprovedHoursOfWorkTypeBrokenOutByPayRate(workType.getId())));
                    }
                }
            }
            case "PayRate" -> {
                String payRateString = this.primaryCategorySubject;
                this.setTitle("Hours worked compensated at $" + payRateString + " / per hour by " + xChoice);
                switch (xChoice) {
                    case "Employee" -> {
                        this.setXyValues(Chart.populateChartDataFromList(lineEntryRepository.findAllApprovedHoursOfPayRateBrokenOutByEmployee(Integer.valueOf(payRateString))));
                    }
                    case "Project" -> {
                        this.setXyValues(Chart.populateChartDataFromList(lineEntryRepository.findAllApprovedHoursOfPayRateBrokenOutByProject(Integer.valueOf(payRateString))));
                    }
                    case "WorkType" -> {
                        this.setXyValues(Chart.populateChartDataFromList(lineEntryRepository.findAllApprovedHoursOfPayRateBrokenOutByWorkType(Integer.valueOf(payRateString))));
                    }
                }
            }
        }
    }


}
