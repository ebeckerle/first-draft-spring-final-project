package com.example.firstdraftspringfinalproject.unittests;

import com.example.firstdraftspringfinalproject.data.*;
import com.example.firstdraftspringfinalproject.models.dao.Chart;
import com.example.firstdraftspringfinalproject.models.dao.metrics.PrimaryMetricChart;
import com.example.firstdraftspringfinalproject.models.dao.metrics.SecondaryMetricChart;
import com.example.firstdraftspringfinalproject.models.enums.MetricsCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

public class MetricsChartTest {


    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    TimesheetRepository timesheetRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    WorkTypeRepository workTypeRepository;

    @Autowired
    LineEntryRepository lineEntryRepository;

//    Metrics testMetricsObj1 = new Metrics("Employee", employeeRepository, timesheetRepository, projectRepository, workTypeRepository);

    Chart testMetricsObj2 = new SecondaryMetricChart(MetricsCategory.PROJECT, "Notre Dame Cathedral", MetricsCategory.WORKTYPE, employeeRepository, timesheetRepository, projectRepository, workTypeRepository);

    @Test
    public void test(){

        assertEquals("101", "102");
    }

    @Test
    public void testSetXyValuesWhenThereIsNoSecondaryCategory(){

//        Chart testMetricsObj1 = new PrimaryMetricChart(MetricsCategory.EMPLOYEE, employeeRepository, timesheetRepository, projectRepository, workTypeRepository);
        Chart testMetricsObj1 = new PrimaryMetricChart(MetricsCategory.EMPLOYEE, employeeRepository, lineEntryRepository);
        testMetricsObj1.populateChartData();

        assertFalse(testMetricsObj1.getXyValues().isEmpty());

    }
}
