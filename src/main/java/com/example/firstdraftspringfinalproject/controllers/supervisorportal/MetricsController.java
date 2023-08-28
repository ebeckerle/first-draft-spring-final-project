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

        List<MetricsCategory> xValueChoices = new ArrayList<>();
        xValueChoices.add(MetricsCategory.EMPLOYEE);
        xValueChoices.add(MetricsCategory.PROJECT);
        xValueChoices.add(MetricsCategory.WORKTYPE);
        xValueChoices.add(MetricsCategory.PAYRATE);
        List<String> chartCategories = new ArrayList<>();
        chartCategories.add("Employee");
        chartCategories.add("Project");
        chartCategories.add("WorkType");
        chartCategories.add("PayRate");
        model.addAttribute("xValueChoices", xValueChoices);
        model.addAttribute("chartCategories", chartCategories);

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
    public String processViewMetrics(@RequestParam(required = false) MetricsCategory xValue, Model model,
                                     @ModelAttribute @Valid ChartRequest chartRequest
                                     ){

        chartRequest.setPrimaryCategory(xValue);
        chartRequest.setSecondaryCategory(MetricsCategory.NOSECONDARYCATEGORY);
        Chart newMetricChart = MetricsChartBuilder.createChartFromChartRequest(chartRequest, employeeRepository,
                lineEntryRepository, projectRepository, workTypeRepository);
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
                                     @RequestParam MetricsCategory xChoice, Model model,
                                     @ModelAttribute @Valid ChartRequest chartRequest
                                    ){

        String primaryCategoryTopic = "";
        switch (chartCategory) {
            case "Employee" -> primaryCategoryTopic = employee;
            case "Project" -> primaryCategoryTopic = project;
            case "WorkType" -> primaryCategoryTopic = workType;
            case "PayRate" -> primaryCategoryTopic = String.valueOf(payRate);
        }

        chartRequest.setPrimaryCategory(MetricsCategory.getMetricsCategoryEnumFromString(chartCategory));
        chartRequest.setSecondaryCategory(xChoice);
        chartRequest.setPrimaryCategoryTopic(primaryCategoryTopic);
        Chart newMetricChart = MetricsChartBuilder.createChartFromChartRequest(chartRequest, employeeRepository,
                lineEntryRepository, projectRepository, workTypeRepository);
        MetricsChartBuilder.populateChartData(newMetricChart);

        model.addAttribute("xyValues", newMetricChart.getXyValues());
        model.addAttribute("chartTitle", newMetricChart.getTitle());
        model.addAttribute("csvHeaders", ((SecondaryMetricChart) newMetricChart).getCsvHeaders());
        model.addAttribute("primaryCategorySubject", ((SecondaryMetricChart) newMetricChart).getPrimaryCategorySubject());

        model.addAttribute("title", "Metrics");

        return "supervisor/metrics";
    }


}
