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

    @Autowired
    ProjectWorkTypeSetRepository projectWorkTypeSetRepository;

    @GetMapping
    public String displayMetricsHome(Model model){

        List<String> xValueChoices = new ArrayList<>();
        xValueChoices.add("Employee");
        xValueChoices.add("Project");
        xValueChoices.add("WorkType");
        xValueChoices.add("PayRate");
        model.addAttribute("xValueChoices", xValueChoices);

        List<String> chartCategories = new ArrayList<>();
        chartCategories.add("Employee");
        chartCategories.add("Project");
        chartCategories.add("WorkType");
        chartCategories.add("PayRate");
        model.addAttribute("chartCategories", chartCategories);

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

        HashMap<String, Integer> xyValues = new HashMap<>();
        String chartTitle = "Chart Title";

        if (chartCategory.equals("Employee")){
            model.addAttribute("chartCategory", chartCategory);
            model.addAttribute("chartTopic", employee);
            model.addAttribute("xValue", xChoice);
            chartTitle = employee + "'s Hours by "+xChoice;
            Employee employee1;
            if (employeeRepository.findByFirstNameLastNameCombo(employee).isPresent()){
                employee1 = employeeRepository.findByFirstNameLastNameCombo(employee).get();
                List<Timesheet> employeesTimesheets = timesheetRepository.findByEmployeeEmployeeIdAndCompletionStatusAndSupervisorApproval(employee1.getEmployeeId(), true, true);
                if(xChoice.equals("Project")){
                    List<Project> projects = (List<Project>) projectRepository.findAll();
                    Integer totalHoursForX = 0;
                    for (Project aProject:
                            projects) {
                        totalHoursForX = 0;
                        for (Timesheet timesheet : employeesTimesheets){
                            totalHoursForX += timesheet.getTotalHoursByProject(aProject);
                            xyValues.put(aProject.toString(), totalHoursForX);
                        }
                    }
                }
                if(xChoice.equals("WorkType")){
                    List<WorkType> workTypes = (List<WorkType>) workTypeRepository.findAll();
                    Integer totalHoursForX = 0;
                    for (WorkType aWorkType      :
                            workTypes) {
                        totalHoursForX = 0;
                        for (Timesheet timesheet : employeesTimesheets){
                            totalHoursForX += timesheet.getTotalHoursByWorkType(aWorkType);
                            xyValues.put(aWorkType.toString(), totalHoursForX);
                        }
                    }
                }
            }

        } else if (chartCategory.equals("Project")){
            model.addAttribute("chartCategory", chartCategory);
            model.addAttribute("chartTopic", project);
            model.addAttribute("xValue", xChoice);
            chartTitle = "Hours worked on " + project + " by "+xChoice;

            Project project1 = projectRepository.findByProjectName(project);
            List<Timesheet> timesheets = timesheetRepository.findBySupervisorApprovalAndCompletionStatus(true, true);
            if(xChoice.equals("Employee")){
                List<Employee> employees = (List<Employee>) employeeRepository.findAll();
                for (Employee aEmployee: employees) {
                    List<Timesheet> employeesTimesheets = timesheetRepository.findByEmployeeEmployeeIdAndCompletionStatusAndSupervisorApproval(aEmployee.getEmployeeId(), true, true);
                    Integer totalHoursForX = 0;
                    for (Timesheet timesheet : employeesTimesheets){
                        totalHoursForX += timesheet.getTotalHoursByProject(project1);
                        xyValues.put(aEmployee.toString(), totalHoursForX);
                        System.out.println(xyValues);
                    }
                }
            }
            if(xChoice.equals("WorkType")) {
                for (Timesheet timesheet : timesheets) {
                    List<LineEntry> lineEntries = timesheet.getLineEntries();
                    for (LineEntry lineEntry :
                            lineEntries) {
                        Integer totalHoursForX = 0;
                        ProjectWorkTypeSet projectWorkTypeSet = lineEntry.getProjectWorkTypeCombo();
                        if (projectWorkTypeSet.getProject().equals(project1)) {
                            if (xyValues.containsKey(projectWorkTypeSet.getWorkType().toString())) {
                                totalHoursForX += xyValues.get(projectWorkTypeSet.getWorkType().toString());
                                totalHoursForX += lineEntry.getTotalHours();
                                xyValues.replace(projectWorkTypeSet.getWorkType().toString(), totalHoursForX);
                            } else {
                                totalHoursForX += lineEntry.getTotalHours();
                                xyValues.put(projectWorkTypeSet.getWorkType().toString(), totalHoursForX);
                            }
                        }
                    }
                }
            }
            if(xChoice.equals("PayRate")){
                List<String> payRates = new ArrayList<>();
                for (Timesheet timesheet:
                        timesheets) {
                    List<LineEntry> lineEntries = timesheet.getLineEntries();
                    for (LineEntry lineEntry :
                            lineEntries) {
                        ProjectWorkTypeSet projectWorkTypeSet = lineEntry.getProjectWorkTypeCombo();
                        if (projectWorkTypeSet.getProject().equals(project1)) {
                            String payRate1 = "$"+timesheet.getCurrentPayRate()+" per hour";
                            if (payRates.contains(payRate1)) {
                                Integer existingHourTotal = xyValues.get(payRate1);
                                Integer newHourTotal = existingHourTotal + lineEntry.getTotalHours();
                                xyValues.replace(payRate1, newHourTotal);
                            } else {
                                payRates.add(payRate1);
                                xyValues.put(payRate1, lineEntry.getTotalHours());
                            }
                        }
                    }
                }
            }

        } else if (chartCategory.equals("WorkType")){
            model.addAttribute("chartCategory", chartCategory);
            model.addAttribute("chartTopic", workType);
            model.addAttribute("xValue", xChoice);
            chartTitle = "Hours worked in " + workType + ", by "+xChoice;
            if(xChoice.equals("Employee")){
                for (Timesheet timesheet:
                     timesheetRepository.findBySupervisorApprovalAndCompletionStatus(true, true)) {
                    for (LineEntry lineEntry:
                         timesheet.getLineEntries()) {
                        String workType1 = lineEntry.getProjectWorkTypeCombo().getWorkType().toString();
                        if (workType.equals(workType1)){
                            if (xyValues.containsKey(timesheet.getEmployee().toString())){
                                Integer existingHourTotal = xyValues.get(timesheet.getEmployee().toString());
                                Integer newHourTotal = existingHourTotal + lineEntry.getTotalHours();
                                xyValues.replace(timesheet.getEmployee().toString(), newHourTotal);
                            }else{
                                xyValues.put(timesheet.getEmployee().toString(), lineEntry.getTotalHours());
                            }
                        }
                    }
                }
            }
            if(xChoice.equals("Project")){
                for (Timesheet timesheet:
                        timesheetRepository.findBySupervisorApprovalAndCompletionStatus(true, true)) {
                    for (LineEntry lineEntry:
                         timesheet.getLineEntries()) {
                        String workType1 = lineEntry.getProjectWorkTypeCombo().getWorkType().toString();
                        if(workType.equals(workType1)){
                            if (xyValues.containsKey(lineEntry.getProjectWorkTypeCombo().getProject().toString())){
                                Integer existingHourTotal = xyValues.get(lineEntry.getProjectWorkTypeCombo().getProject().toString());
                                Integer newHourTotal = existingHourTotal + lineEntry.getTotalHours();
                                xyValues.replace(lineEntry.getProjectWorkTypeCombo().getProject().toString(), newHourTotal);
                            }else{
                                xyValues.put(lineEntry.getProjectWorkTypeCombo().getProject().toString(), lineEntry.getTotalHours());
                            }
                        }
                    }
                }
            }
            if(xChoice.equals("PayRate")){
                for (Timesheet timesheet:
                     timesheetRepository.findBySupervisorApprovalAndCompletionStatus(true, true)) {
                    for (LineEntry lineEntry:
                            timesheet.getLineEntries()) {
                        String workType1 = lineEntry.getProjectWorkTypeCombo().getWorkType().toString();
                        if(workType.equals(workType1)){
                            String payRate1 = "$"+timesheet.getCurrentPayRate()+" per hour";
                            if (xyValues.containsKey(payRate1)) {
                                Integer existingHourTotal = xyValues.get(payRate1);
                                Integer newHourTotal = existingHourTotal + lineEntry.getTotalHours();
                                xyValues.replace(payRate1, newHourTotal);
                            } else {
                                xyValues.put(payRate1, lineEntry.getTotalHours());
                            }
                        }
                    }
                }
            }
        } else if (chartCategory.equals("PayRate")){
            model.addAttribute("chartCategory", chartCategory);
            model.addAttribute("chartTopic", payRate);
            model.addAttribute("xValue", xChoice);
            chartTitle = "Hours worked compensated at $" + payRate + " / per hour by "+xChoice;
            if(xChoice.equals("Employee")){
                for (Timesheet timesheet:
                     timesheetRepository.findBySupervisorApprovalAndCompletionStatus(true, true)) {
                    if(payRate.equals(timesheet.getCurrentPayRate())){
                        if(xyValues.containsKey(timesheet.getEmployee().toString())){
                            Integer existingHourTotal = xyValues.get(timesheet.getEmployee().toString());
                            Integer newHourTotal = existingHourTotal + timesheet.getTotalHours();
                            xyValues.put(timesheet.getEmployee().toString(), newHourTotal);
                        }else{
                            xyValues.put(timesheet.getEmployee().toString(), timesheet.getTotalHours());
                        }
                    }
                }
            }
            if(xChoice.equals("Project")){
                for (Timesheet timesheet:
                     timesheetRepository.findBySupervisorApprovalAndCompletionStatus(true, true)) {
                    for (LineEntry lineEntry:
                         timesheet.getLineEntries()) {
                        if(payRate.equals(timesheet.getCurrentPayRate())){
                            if(xyValues.containsKey(lineEntry.getProjectWorkTypeCombo().getProject().toString())){
                                Integer existingHourTotal = xyValues.get(lineEntry.getProjectWorkTypeCombo().getProject().toString());
                                Integer newHourTotal = existingHourTotal + lineEntry.getTotalHours();
                                xyValues.put(lineEntry.getProjectWorkTypeCombo().getProject().toString(), newHourTotal);
                            }else{
                                xyValues.put(lineEntry.getProjectWorkTypeCombo().getProject().toString(), lineEntry.getTotalHours());
                            }
                        }
                    }
                }
            }
            if(xChoice.equals("WorkType")){
                for (Timesheet timesheet:
                        timesheetRepository.findBySupervisorApprovalAndCompletionStatus(true, true)) {
                    for (LineEntry lineEntry:
                            timesheet.getLineEntries()) {
                        if(payRate.equals(timesheet.getCurrentPayRate())){
                            if(xyValues.containsKey(lineEntry.getProjectWorkTypeCombo().getWorkType().toString())){
                                Integer existingHourTotal = xyValues.get(lineEntry.getProjectWorkTypeCombo().getWorkType().toString());
                                Integer newHourTotal = existingHourTotal + lineEntry.getTotalHours();
                                xyValues.put(lineEntry.getProjectWorkTypeCombo().getWorkType().toString(), newHourTotal);
                            }else{
                                xyValues.put(lineEntry.getProjectWorkTypeCombo().getWorkType().toString(), lineEntry.getTotalHours());
                            }
                        }
                    }
                }
            }
        }

        model.addAttribute("xyValues", xyValues);
        model.addAttribute("chartTitle", chartTitle);
        model.addAttribute("title", "Metrics");

        return "supervisor/metrics";
    }


}
