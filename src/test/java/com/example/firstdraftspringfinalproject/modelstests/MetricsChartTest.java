package com.example.firstdraftspringfinalproject.modelstests;

import com.example.firstdraftspringfinalproject.data.EmployeeRepository;
import com.example.firstdraftspringfinalproject.data.ProjectRepository;
import com.example.firstdraftspringfinalproject.data.TimesheetRepository;
import com.example.firstdraftspringfinalproject.data.WorkTypeRepository;
import com.example.firstdraftspringfinalproject.models.dao.MetricsChart;
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

//    Metrics testMetricsObj1 = new Metrics("Employee", employeeRepository, timesheetRepository, projectRepository, workTypeRepository);

    MetricsChart testMetricsObj2 = new MetricsChart(MetricsCategory.PROJECT, "Notre Dame Cathedral", MetricsCategory.WORKTYPE, employeeRepository, timesheetRepository, projectRepository, workTypeRepository);

    @Test
    public void test(){

        assertEquals("101", "102");
    }

    @Test
    public void testSetXyValuesWhenThereIsNoSecondaryCategory(){

        MetricsChart testMetricsObj1 = new MetricsChart(MetricsCategory.EMPLOYEE, employeeRepository, timesheetRepository, projectRepository, workTypeRepository);
        testMetricsObj1.populateChartDataWhenThereIsNoSecondaryCategory();
        assertFalse(testMetricsObj1.getContainsSecondaryCategory());
        assertFalse(testMetricsObj1.getXyValues().isEmpty());

    }
}
