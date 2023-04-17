package com.example.firstdraftspringfinalproject.models.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public interface PopulateMetricsChartDataPairs {

    //
    HashMap<String, Integer> loadXyValues(List<Object> timesheets, Object xAxisValue, Object yAxisValue);

    HashMap<String, Integer> loadXyValuesOnTopic(List<Object> timesheets, Object xAxisValue, Object yAxisValue, Object chartTopic);
}
