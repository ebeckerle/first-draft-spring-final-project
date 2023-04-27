package com.example.firstdraftspringfinalproject.models.dao;

import com.example.firstdraftspringfinalproject.models.dto.ChartRequest;

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
}
