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

    public static void populateChartData(Chart chart){
        //TODO - do I even need the if statement?
        if(chart instanceof PrimaryMetricChart){
            chart.populateChartData();
        }

    }


}
