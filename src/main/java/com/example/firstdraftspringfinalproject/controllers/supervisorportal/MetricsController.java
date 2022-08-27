package com.example.firstdraftspringfinalproject.controllers.supervisorportal;

import com.example.firstdraftspringfinalproject.data.EmployeeRepository;
import com.example.firstdraftspringfinalproject.data.ProjectRepository;
import com.example.firstdraftspringfinalproject.data.TimesheetRepository;
import com.example.firstdraftspringfinalproject.data.WorkTypeRepository;
import com.example.firstdraftspringfinalproject.models.Employee;
import com.example.firstdraftspringfinalproject.models.Project;
import com.example.firstdraftspringfinalproject.models.Timesheet;
import com.example.firstdraftspringfinalproject.models.WorkType;
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
            payRates.add(timesheet.getCurrentPayRate());
        }
        model.addAttribute("payRates", payRates);

        //THIS ALL NEEDS TO BE REFACTORED / CLEANED UP - METRICS INTERFACE FOR SOME OF THESE METHODS?

        HashMap<String, Integer> xyValues = new HashMap<>();

        Project project1 = projectRepository.findByProjectName("Notre Dame Cathedral");
        Integer totalHoursForProject1 = 0;
        for (Timesheet timesheet:
            timesheetRepository.findAll()) {
            totalHoursForProject1 += timesheet.getTotalHoursByProject(project1);
        }
        xyValues.put(project1.toString(), totalHoursForProject1);

        Project project2 = projectRepository.findByProjectName("Iowa State Capitol Building");
        Integer totalHoursForProject2 = 0;
        for (Timesheet timesheet:
                timesheetRepository.findAll()) {
            totalHoursForProject2 += timesheet.getTotalHoursByProject(project2);
        }
        xyValues.put(project2.toString(), totalHoursForProject2);

        Project project3 = projectRepository.findByProjectName("Kansas City Union Station");
        Integer totalHoursForProject3 = 0;
        for (Timesheet timesheet:
                timesheetRepository.findAll()) {
            totalHoursForProject3 += timesheet.getTotalHoursByProject(project3);
        }
        xyValues.put(project3.toString(), totalHoursForProject3);

        Project project4 = projectRepository.findByProjectName("Nelson Atkins Museum");
        Integer totalHoursForProject4 = 0;
        for (Timesheet timesheet:
                timesheetRepository.findAll()) {
            totalHoursForProject4 += timesheet.getTotalHoursByProject(project4);
        }
        xyValues.put(project4.toString(), totalHoursForProject4);

        model.addAttribute("xyValues", xyValues);

        return "supervisor/metrics-query";
    }

    @PostMapping(params="total")
    public String processViewMetrics(@RequestParam String xValue, Model model){
        HashMap<String, Integer> xyValues = new HashMap<>();
        if (xValue.equals("Employee")){
            List<Employee>  employees = (List<Employee>) employeeRepository.findAll();
            for (Employee employee:
                 employees) {
                xyValues.put(employee.getLastName(), employee.getTotalHoursWorkedToDate());
            }
            model.addAttribute("title", xValue);
        }
        if (xValue.equals("Project")){
            List<Project>  projects = (List<Project>) projectRepository.findAll();
            for (Project project:
                    projects) {
                Project project1 = project;
                Integer totalHoursForProject1 = 0;
                for (Timesheet timesheet:
                        timesheetRepository.findBySupervisorApprovalAndCompletionStatus(true, true)) {
                    totalHoursForProject1 += timesheet.getTotalHoursByProject(project1);
                }
                xyValues.put(project.getProjectName(), totalHoursForProject1);
            }
            model.addAttribute("title", xValue);
        }
        if (xValue.equals("WorkType")){
            List<WorkType> workTypes = (List<WorkType>) workTypeRepository.findAll();
            for (WorkType workType:
                    workTypes){
                Integer totalHoursForWorkType = 0;
                for (Timesheet timesheet:
                     timesheetRepository.findBySupervisorApprovalAndCompletionStatus(true, true)) {
                    totalHoursForWorkType += timesheet.getTotalHoursByWorkType(workType);
                }
                xyValues.put(workType.toStringWorkTypeCode(), totalHoursForWorkType);
            }
        }
        if (xValue.equals("PayRate")){
            List<Integer> payRates = new ArrayList<>();
            for (Timesheet timesheet:
                 timesheetRepository.findBySupervisorApprovalAndCompletionStatus(true, true)) {
                //
                Integer payRate = timesheet.getCurrentPayRate();
                if(payRates.contains(payRate)){
                    Integer existingHourTotal = xyValues.get(String.valueOf(payRate));
                    Integer newHourTotal = existingHourTotal + timesheet.getTotalHours();
                    xyValues.replace(String.valueOf(payRate), newHourTotal);
                }else{
                    payRates.add(payRate);
                    xyValues.put(String.valueOf(payRate), timesheet.getTotalHours());
                }
            }
        }
        model.addAttribute("xyValues", xyValues);

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

        if (chartCategory.equals("Employee")){
            model.addAttribute("chartCategory", chartCategory);
            model.addAttribute("chartTopic", employee);
            model.addAttribute("xValue", xChoice);
            Employee employee1;
            if (employeeRepository.findByFirstNameLastNameCombo(employee).isPresent()){
                employee1 = employeeRepository.findByFirstNameLastNameCombo(employee).get();
                //find Ari's timesheets that are approved
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
            Project project1 = projectRepository.findByProjectName(project);
            //
            List<Timesheet> timesheets = timesheetRepository.findBySupervisorApprovalAndCompletionStatus(true, true);
            if(xChoice.equals("Employee")){
                List<Employee> employees = (List<Employee>) employeeRepository.findAll();
                Integer totalHoursForX = 0;
                for (Employee aEmployee: employees) {
                    totalHoursForX = 0;
                    for (Timesheet timesheet : timesheets){
                        totalHoursForX += timesheet.getTotalHoursByProject(project1);
                        xyValues.put(aEmployee.toString(), totalHoursForX);
                    }
                }
            }
            if(xChoice.equals("WorkType")){
                List<WorkType> workTypes = (List<WorkType>) workTypeRepository.findAll();
                Integer totalHoursForX = 0;
                for (WorkType aWorkType : workTypes) {
                    totalHoursForX = 0;
                    for (Timesheet timesheet : timesheets){
                        totalHoursForX += timesheet.getTotalHoursByWorkType(aWorkType);
                        xyValues.put(aWorkType.toString(), totalHoursForX);
                    }
                }
            }


        } else if (chartCategory.equals("WorkType")){
            model.addAttribute("chartCategory", chartCategory);
            model.addAttribute("chartTopic", workType);
            model.addAttribute("xValue", xChoice);
        } else if (chartCategory.equals("PayRate")){
            model.addAttribute("chartCategory", chartCategory);
            model.addAttribute("chartTopic", payRate);
            model.addAttribute("xValue", xChoice);
        }

        model.addAttribute("xyValues", xyValues);

        return "supervisor/metrics";
    }


}
