package com.example.firstdraftspringfinalproject.models.dao;

import com.example.firstdraftspringfinalproject.data.EmployeeRepository;
import com.example.firstdraftspringfinalproject.data.ProjectRepository;
import com.example.firstdraftspringfinalproject.data.TimesheetRepository;
import com.example.firstdraftspringfinalproject.data.WorkTypeRepository;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.*;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets.Timesheet;
import com.example.firstdraftspringfinalproject.models.enums.MetricsCategory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//I think this is a Data Access Object???

public class PrimaryMetricChart extends Chart implements MetricsPayRate, MetricsWorkType, MetricsProject, MetricsEmployee{

    @Autowired
    private EmployeeRepository employeeRepository;
    //TODO - just pull from database this  - List<Employee> employees...

    @Autowired
    private TimesheetRepository timesheetRepository;
    //TODO - just pull from database this  - List<Timesheet> timesheets  /* - just timesheets that are submitted and
    // approved by supervisor - */ - findBySupervisorApprovalAndCompletionStatus(true, true)

    @Autowired
    private ProjectRepository projectRepository;
    //TODO - just pull from database this  - List<Project> projects (these are maybe in the future just un retired
    // projects, or just projects in that have entered production phase? right now all projects

    @Autowired
    private WorkTypeRepository workTypeRepository;
    //TODO - maybe? - List<WorkType> I think we are actually always pulling all work types and I don't see why that would
    // change at this point...

    private final MetricsCategory primaryCategory;

    private List<String> csvHeaders;

    public PrimaryMetricChart(MetricsCategory primaryCategory, EmployeeRepository employeeRepository, TimesheetRepository timesheetRepository, ProjectRepository projectRepository, WorkTypeRepository workTypeRepository) {
        if(timesheetRepository.count() == 0){
            throw new RuntimeException("Fail, there are no timesheets");
        }
        this.primaryCategory = primaryCategory;
        this.employeeRepository = employeeRepository;
        this.timesheetRepository = timesheetRepository;
        this.projectRepository = projectRepository;
        this.workTypeRepository = workTypeRepository;
    }

    public PrimaryMetricChart(MetricsCategory primaryCategory) {
        this.primaryCategory = primaryCategory;
    }

    //Getter & Setters

    public List<String> getCsvHeaders() {
        return csvHeaders;
    }

    //METHODS


    public static ArrayList<String> loadCsvHeaders(MetricsCategory primaryCategory){
        ArrayList<String> csvHeaders = new ArrayList<>();
        csvHeaders.add(primaryCategory.getDisplayName());
        csvHeaders.add("Hours");
        return csvHeaders;
    }

    @Override
    public void populateChartData(){

        switch (this.primaryCategory.getDisplayName()) {
            case "Employee" ->
                    this.setXyValues(MetricsEmployee.loadXyValuesForPrimaryCategoryEmployee(employeeRepository));
            case "Project" ->
                    this.setXyValues(MetricsProject.loadXyValuesForPrimaryCategoryProject(timesheetRepository, projectRepository));
            case "WorkType" ->
                    this.setXyValues(MetricsWorkType.loadXyValuesForPrimaryCategoryWorkType(timesheetRepository, workTypeRepository));
            case "PayRate" ->
                    this.setXyValues(MetricsPayRate.loadXyValuesForPrimaryCategoryPayRate(timesheetRepository));
        }
        this.setTitle(primaryCategory.getDisplayName());
        this.csvHeaders = PrimaryMetricChart.loadCsvHeaders(this.primaryCategory);
    }



}
