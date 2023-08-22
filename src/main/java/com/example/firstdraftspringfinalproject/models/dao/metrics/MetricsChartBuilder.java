package com.example.firstdraftspringfinalproject.models.dao.metrics;

import com.example.firstdraftspringfinalproject.data.*;
import com.example.firstdraftspringfinalproject.models.dao.Chart;
import com.example.firstdraftspringfinalproject.models.dto.ChartRequest;
import com.example.firstdraftspringfinalproject.models.enums.MetricsCategory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MetricsChartBuilder {

    public static Chart createChartFromChartRequest(ChartRequest request, EmployeeRepository employeeRepository, LineEntryRepository lineEntryRepository){
        Chart newChart = new Chart();
        if(request.hasPrimaryMetricsCategoryOnly()){
            newChart = new PrimaryMetricChart(request.getPrimaryCategory(), employeeRepository, lineEntryRepository);
        }
        if(request.hasSecondaryMetricsCategory()){
            newChart = new SecondaryMetricChart(request.getPrimaryCategory(), request.getPrimaryCategoryTopic(), request.getSecondaryCategory(), employeeRepository, lineEntryRepository);
        }
        return newChart;
    }

    public static void populateChartData(Chart chart){
        //TODO - do I even need the if statement?
        if(chart instanceof PrimaryMetricChart){
            chart.populateChartData();
        }
        if(chart instanceof SecondaryMetricChart){
            chart.populateChartData();
        }


    }


}
