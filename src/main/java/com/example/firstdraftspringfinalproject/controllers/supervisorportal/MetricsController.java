package com.example.firstdraftspringfinalproject.controllers.supervisorportal;

import com.example.firstdraftspringfinalproject.data.*;
import com.example.firstdraftspringfinalproject.models.dao.Chart;
import com.example.firstdraftspringfinalproject.models.dao.metrics.MetricsChartBuilder;
import com.example.firstdraftspringfinalproject.models.dao.metrics.MetricsPayRate;
import com.example.firstdraftspringfinalproject.models.dao.metrics.PrimaryMetricChart;
import com.example.firstdraftspringfinalproject.models.dao.metrics.SecondaryMetricChart;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets.Timesheet;
import com.example.firstdraftspringfinalproject.models.dto.ChartRequest;
import com.example.firstdraftspringfinalproject.models.dto.CreateEmployeeDTO;
import com.example.firstdraftspringfinalproject.models.enums.MetricsCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("supervisor/metrics")
public class MetricsController {

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

    @GetMapping
    public String displayMetricsHome(Model model){

        List<String> xValueChoices = new ArrayList<>();
        xValueChoices.add("Employee");
        xValueChoices.add("Project");
        xValueChoices.add("WorkType");
        xValueChoices.add("PayRate");
        model.addAttribute("xValueChoices", xValueChoices);
        model.addAttribute("chartCategories", xValueChoices);

        model.addAttribute("employees", employeeRepository.findAll());
        //TODO -do we need all of the other columns of data - just need employee first name, last name, id - native sql query?
        //        model.addAttribute("employees", employeeRepository.findAllEmployeesFirstNameLastNameCombo());
        model.addAttribute("projects", projectRepository.findAll());
        model.addAttribute("workTypes", workTypeRepository.findAll());
        List<Timesheet> timesheets = timesheetRepository.findBySupervisorApprovalAndCompletionStatus(true, true);
        List<Integer> payRates = MetricsPayRate.loadListOfPayRates(timesheets);
        model.addAttribute("payRates", payRates);

        model.addAttribute(new ChartRequest());

        model.addAttribute("title", "Metrics Query");
        return "supervisor/metrics-query";
    }

    @PostMapping(params="total")
    public String processViewMetrics(@RequestParam String xValue, Model model,
                                     @ModelAttribute @Valid ChartRequest chartRequest
                                     ){


//        Chart newMetric = new PrimaryMetricChart(MetricsCategory.getMetricsCategoryEnumFromString(xValue), employeeRepository, lineEntryRepository);
//        newMetric.populateChartData();
        Chart newMetricChart = MetricsChartBuilder.createChartFromChartRequest(chartRequest);
        MetricsChartBuilder.populateChartData(newMetricChart);
        model.addAttribute("xyValues", newMetricChart.getXyValues());
        model.addAttribute("chartTitle", "Total Hours by "+ newMetricChart.getTitle());
        model.addAttribute("csvHeaders", ((PrimaryMetricChart) newMetricChart).getCsvHeaders());

        model.addAttribute("title", "Metrics");

        return "supervisor/metrics";
    }

    @PostMapping(params="category")
    public String processViewMetrics(@RequestParam String chartCategory,
                                     @RequestParam(required = false) String employee,
                                     @RequestParam(required = false) String project,
                                     @RequestParam(required = false) String workType,
                                     @RequestParam(required = false) Integer payRate,
                                     @RequestParam String xChoice, Model model){

        String primaryCategorySubject = "";
        switch (chartCategory) {
            case "Employee" -> primaryCategorySubject = employee;
            case "Project" -> primaryCategorySubject = project;
            case "WorkType" -> primaryCategorySubject = workType;
            case "PayRate" -> primaryCategorySubject = String.valueOf(payRate);
        }

        Chart newMetric = new SecondaryMetricChart(MetricsCategory.getMetricsCategoryEnumFromString(chartCategory), primaryCategorySubject, MetricsCategory.getMetricsCategoryEnumFromString(xChoice), employeeRepository, timesheetRepository, projectRepository, workTypeRepository);
        newMetric.populateChartData();
        model.addAttribute("xyValues", newMetric.getXyValues());
        model.addAttribute("chartTitle", newMetric.getTitle());
        model.addAttribute("csvHeaders", ((SecondaryMetricChart) newMetric).getCsvHeaders());
        model.addAttribute("primaryCategorySubject", ((SecondaryMetricChart) newMetric).getPrimaryCategorySubject());

        model.addAttribute("title", "Metrics");

        return "supervisor/metrics";
    }


}
