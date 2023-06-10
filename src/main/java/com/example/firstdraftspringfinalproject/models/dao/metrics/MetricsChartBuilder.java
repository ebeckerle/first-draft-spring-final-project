package com.example.firstdraftspringfinalproject.models.dao.metrics;

import com.example.firstdraftspringfinalproject.data.EmployeeRepository;
import com.example.firstdraftspringfinalproject.data.ProjectRepository;
import com.example.firstdraftspringfinalproject.data.TimesheetRepository;
import com.example.firstdraftspringfinalproject.data.WorkTypeRepository;
import com.example.firstdraftspringfinalproject.models.dao.Chart;
import com.example.firstdraftspringfinalproject.models.dto.ChartRequest;
import com.example.firstdraftspringfinalproject.models.enums.MetricsCategory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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

    public void populateChartData(Chart chart){
        //TODO - do I even need the if statement?
        if(chart instanceof PrimaryMetricChart){
            chart.populateChartData();
        }

    }


}
