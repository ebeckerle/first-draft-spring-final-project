package com.example.firstdraftspringfinalproject.modelstests;

import com.example.firstdraftspringfinalproject.data.EmployeeRepository;
import com.example.firstdraftspringfinalproject.data.ProjectRepository;
import com.example.firstdraftspringfinalproject.data.TimesheetRepository;
import com.example.firstdraftspringfinalproject.data.WorkTypeRepository;
import com.example.firstdraftspringfinalproject.models.dao.Metrics;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

public class MetricsTest {


    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    TimesheetRepository timesheetRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    WorkTypeRepository workTypeRepository;

//    Metrics testMetricsObj1 = new Metrics("Employee", employeeRepository, timesheetRepository, projectRepository, workTypeRepository);

    Metrics testMetricsObj2 = new Metrics("WorkType", "Project", "Notre Dame Cathedral", employeeRepository, timesheetRepository, projectRepository, workTypeRepository);

    @Test
    public void test(){

        assertEquals("101", "102");
    }

    @Test
    public void testSetXyValuesWhenThereIsNoSecondaryCategory(){

        Metrics testMetricsObj1 = new Metrics("Employee", employeeRepository, timesheetRepository, projectRepository, workTypeRepository);
        testMetricsObj1.setXyValuesWhenThereIsNoSecondaryCategory();
        assertFalse(testMetricsObj1.getContainsSecondaryCategory());
        assertFalse(testMetricsObj1.getXyValues().isEmpty());
//        assertEquals("101", "102");

//        public void setXyValuesWhenThereIsNoSecondaryCategory(){
//            HashMap<String, Integer> xyValues = new HashMap<>();
//            if(this.primaryCategory.equals("Employee")){
//                List<Employee> employees = (List<Employee>) employeeRepository.findAll();
//                for (Employee employee:
//                        employees) {
//                    xyValues.put(employee.getLastName(), employee.getTotalHoursWorkedToDate());
//                }
//            }
//            if (this.primaryCategory.equals("Project")){
//                List<Project>  projects = (List<Project>) projectRepository.findAll();
//                for (Project project:
//                        projects) {
//                    Integer totalHoursForProject = 0;
//                    for (Timesheet timesheet:
//                            timesheetRepository.findBySupervisorApprovalAndCompletionStatus(true, true)) {
//                        totalHoursForProject += timesheet.getTotalHoursByProject(project);
//                    }
//                    xyValues.put(project.getProjectName(), totalHoursForProject);
//                }
//            }
//            if (this.primaryCategory.equals("WorkType")){
//                List<WorkType> workTypes = (List<WorkType>) workTypeRepository.findAll();
//                for (WorkType workType:
//                        workTypes){
//                    Integer totalHoursForWorkType = 0;
//                    for (Timesheet timesheet:
//                            timesheetRepository.findBySupervisorApprovalAndCompletionStatus(true, true)) {
//                        totalHoursForWorkType += timesheet.getTotalHoursByWorkType(workType);
//                    }
//                    xyValues.put(workType.toStringWorkTypeCode(), totalHoursForWorkType);
//                }
//            }
//            if (this.primaryCategory.equals("PayRate")){
//                List<Integer> payRates = new ArrayList<>();
//                for (Timesheet timesheet:
//                        timesheetRepository.findBySupervisorApprovalAndCompletionStatus(true, true)) {
//                    //
//                    Integer payRate = timesheet.getCurrentPayRate();
//                    if(payRates.contains(payRate)){
//                        Integer existingHourTotal = xyValues.get(String.valueOf(payRate));
//                        Integer newHourTotal = existingHourTotal + timesheet.getTotalHours();
//                        xyValues.replace(String.valueOf(payRate), newHourTotal);
//                    }else{
//                        payRates.add(payRate);
//                        xyValues.put(String.valueOf(payRate), timesheet.getTotalHours());
//                    }
//                }
//            }
//            this.chartTitle = primaryCategory;
//            this.xyValues = xyValues;
//        }
    }
}
