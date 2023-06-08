package com.example.firstdraftspringfinalproject.models.dao.metrics;

import com.example.firstdraftspringfinalproject.data.*;
import com.example.firstdraftspringfinalproject.models.dao.Chart;
import com.example.firstdraftspringfinalproject.models.enums.MetricsCategory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

//I think this is a Data Access Object???

public class PrimaryMetricChart extends Chart implements MetricsPayRate, MetricsWorkType, MetricsProject, MetricsEmployee {

    @Autowired
    private EmployeeRepository employeeRepository;
    //TODO - just pull from database this  - List<Employee> employees...
//
//    @Autowired
//    private TimesheetRepository timesheetRepository;
//    //TODO - just pull from database this  - List<Timesheet> timesheets  /* - just timesheets that are submitted and
//    // approved by supervisor - */ - findBySupervisorApprovalAndCompletionStatus(true, true)
//
//    @Autowired
//    private ProjectRepository projectRepository;
//    //TODO - just pull from database this  - List<Project> projects (these are maybe in the future just un retired
//    // projects, or just projects in that have entered production phase? right now all projects
//
//    @Autowired
//    private WorkTypeRepository workTypeRepository;
//    //TODO - maybe? - List<WorkType> I think we are actually always pulling all work types and I don't see why that would
//    // change at this point...

    @Autowired
    private LineEntryRepository lineEntryRepository;

    private final MetricsCategory primaryCategory;

    private List<String> csvHeaders;

    public PrimaryMetricChart(MetricsCategory primaryCategory, EmployeeRepository employeeRepository, LineEntryRepository lineEntryRepository) {
        if(lineEntryRepository.count() == 0){
            throw new RuntimeException("Fail, there are no line entries on the timesheets");
        }
        this.primaryCategory = primaryCategory;
        this.employeeRepository = employeeRepository;
//        this.timesheetRepository = timesheetRepository;
//        this.projectRepository = projectRepository;
//        this.workTypeRepository = workTypeRepository;
        this.lineEntryRepository = lineEntryRepository;
    }

    public PrimaryMetricChart(MetricsCategory primaryCategory) {
        this.primaryCategory = primaryCategory;
    }

    //Getter & Setters


    public MetricsCategory getPrimaryCategory() {
        return primaryCategory;
    }

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
//                    this.setXyValues(MetricsEmployee.loadXyValuesForPrimaryCategoryEmployee(employeeRepository));
                    this.setXyValues(Chart.populateChartDataFromList(employeeRepository.findAllEmployeesFirstNameLastNameComboAndTotalHoursWorkedToDate()));
            case "Project" ->
//                    this.setXyValues(MetricsProject.loadXyValuesForPrimaryCategoryProject(timesheetRepository, projectRepository));
                    this.setXyValues(Chart.populateChartDataFromList(lineEntryRepository.findAllApprovedHoursByProject()));
            case "WorkType" ->
//                    this.setXyValues(MetricsWorkType.loadXyValuesForPrimaryCategoryWorkType(timesheetRepository, workTypeRepository));
                    this.setXyValues(Chart.populateChartDataFromList(lineEntryRepository.findAllApprovedHoursByWorkType()));
            case "PayRate" ->
//                    this.setXyValues(MetricsPayRate.loadXyValuesForPrimaryCategoryPayRate(timesheetRepository));
                    this.setXyValues(Chart.populateChartDataFromList(lineEntryRepository.findAllApprovedHoursByPayRate()));
        }
        this.setTitle(primaryCategory.getDisplayName());
        this.csvHeaders = PrimaryMetricChart.loadCsvHeaders(this.primaryCategory);
    }



}
