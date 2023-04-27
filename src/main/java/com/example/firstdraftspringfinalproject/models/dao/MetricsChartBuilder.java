package com.example.firstdraftspringfinalproject.models.dao;

import com.example.firstdraftspringfinalproject.data.EmployeeRepository;
import com.example.firstdraftspringfinalproject.data.ProjectRepository;
import com.example.firstdraftspringfinalproject.data.TimesheetRepository;
import com.example.firstdraftspringfinalproject.data.WorkTypeRepository;
import com.example.firstdraftspringfinalproject.models.dto.ChartRequest;
import org.springframework.beans.factory.annotation.Autowired;

public class MetricsChartBuilder {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TimesheetRepository timesheetRepository;
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private WorkTypeRepository workTypeRepository;

    public static Chart createChartFromChartRequest(ChartRequest request){
        Chart newChart = new Chart();
        if(request.hasPrimaryMetricsCategoryOnly()){
            newChart = new PrimaryMetricChart(request.getPrimaryCategory());
        }
        if(request.hasSecondaryMetricsCategory()){
            newChart = new SecondaryMetricChart(request.getPrimaryCategory(), request.getPrimaryCategoryTopic(), request.getSecondaryCategory());
        }
        return newChart;
    }
}
