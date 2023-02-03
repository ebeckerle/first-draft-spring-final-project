package com.example.firstdraftspringfinalproject.controllers.supervisorportal;

import com.example.firstdraftspringfinalproject.data.*;
import com.example.firstdraftspringfinalproject.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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
        model.addAttribute("projects", projectRepository.findAll());
        model.addAttribute("workTypes", workTypeRepository.findAll());
        List<Integer> payRates = new ArrayList<>();
        List<Timesheet> timesheets = timesheetRepository.findBySupervisorApprovalAndCompletionStatus(true, true);
        for (Timesheet timesheet:
             timesheets) {
            if (!payRates.contains(timesheet.getCurrentPayRate())){
                payRates.add(timesheet.getCurrentPayRate());
            }
        }
        model.addAttribute("payRates", payRates);

        model.addAttribute("title", "Metrics Query");
        return "supervisor/metrics-query";
    }

    @PostMapping(params="total")
    public String processViewMetrics(@RequestParam String xValue, Model model){

        Metrics newMetric = new Metrics(xValue, employeeRepository, timesheetRepository, projectRepository, workTypeRepository);
        newMetric.setXyValuesWhenThereIsNoSecondaryCategory();
        model.addAttribute("xyValues", newMetric.getXyValues());
        model.addAttribute("chartTitle", "Total Hours by "+newMetric.getChartTitle());
        model.addAttribute("csvHeaders", newMetric.getCsvHeaders());

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

        Metrics newMetric = new Metrics(chartCategory, primaryCategorySubject, xChoice, employeeRepository, timesheetRepository, projectRepository, workTypeRepository);
        newMetric.setXyValuesWhenThereIsASecondaryCategory();
        model.addAttribute("xyValues", newMetric.getXyValues());
        model.addAttribute("chartTitle", newMetric.getChartTitle());
        model.addAttribute("csvHeaders", newMetric.getCsvHeaders());
        model.addAttribute("primaryCategorySubject", newMetric.getPrimaryCategorySubject());

        model.addAttribute("title", "Metrics");

        return "supervisor/metrics";
    }


}
