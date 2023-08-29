package com.example.firstdraftspringfinalproject.models.dao.metrics;

import com.example.firstdraftspringfinalproject.data.*;
import com.example.firstdraftspringfinalproject.models.dao.Chart;
import com.example.firstdraftspringfinalproject.models.enums.MetricsCategory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public class PrimaryMetricChart extends Chart {

    @Autowired
    private EmployeeRepository employeeRepository;

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
        this.lineEntryRepository = lineEntryRepository;
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
                    this.setXyValues(Chart.populateChartDataFromList(employeeRepository.findAllEmployeesFirstNameLastNameComboAndTotalHoursWorkedToDate()));
            case "Project" ->
                    this.setXyValues(Chart.populateChartDataFromList(lineEntryRepository.findAllApprovedHoursByProject()));
            case "WorkType" ->
                    this.setXyValues(Chart.populateChartDataFromList(lineEntryRepository.findAllApprovedHoursByWorkType()));
            case "PayRate" ->
                    this.setXyValues(Chart.populateChartDataFromList(lineEntryRepository.findAllApprovedHoursByPayRate()));
        }
        this.setTitle(primaryCategory.getDisplayName());
        this.csvHeaders = PrimaryMetricChart.loadCsvHeaders(this.primaryCategory);
    }



}
