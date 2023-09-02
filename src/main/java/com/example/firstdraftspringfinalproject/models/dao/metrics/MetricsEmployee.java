package com.example.firstdraftspringfinalproject.models.dao.metrics;

import com.example.firstdraftspringfinalproject.data.EmployeeRepository;
import com.example.firstdraftspringfinalproject.data.TimesheetRepository;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.Employee;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.Project;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets.LineEntry;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets.Timesheet;

import java.util.HashMap;
import java.util.List;

//public interface MetricsEmployee {
//
//
////    static HashMap<String, Integer> loadXyValuesForPrimaryCategoryEmployee(EmployeeRepository employeeRepository) {
////        HashMap<String, Integer> xyValues = new HashMap<>();
////        List<Employee> employees = (List<Employee>) employeeRepository.findAll();//TODO - native sql method
////        for (Employee employee:
////                employees) {
////            xyValues.put(employee.getLastName(), employee.getTotalApprovedHoursWorkedToDate());
////        }
////        return xyValues;
////    }
////
////
////    static HashMap<String, Integer> loadXyValuesForSecondaryCategoryEmployeeWhenPrimaryCategoryIsProject(EmployeeRepository employeeRepository,
////                                                                              TimesheetRepository timesheetRepository,
////                                                                              Project project){
////        HashMap<String, Integer> xyValues = new HashMap<>();
////        List<Employee> employees = (List<Employee>) employeeRepository.findAll();
////        for (Employee aEmployee : employees) {
////            List<Timesheet> employeesTimesheets = timesheetRepository.findByEmployeeIdAndCompletionStatusAndSupervisorApproval(aEmployee.getId(), true, true);
////            Integer totalHoursForX = 0;
////            for (Timesheet timesheet : employeesTimesheets) {
////                totalHoursForX += timesheet.getTotalHoursByProject(project);
////                xyValues.put(aEmployee.toString(), totalHoursForX);
////            }
////        }
////        return xyValues;
////    }
////
////
////
////    static HashMap<String, Integer> loadXyValuesForSecondaryCategoryEmployeeWhenPrimaryCategoryIsWorkType(TimesheetRepository timesheetRepository, String workTypeName) {
////        HashMap<String, Integer> xyValues = new HashMap<>();
////        for (Timesheet timesheet :
////                timesheetRepository.findBySupervisorApprovalAndCompletionStatus(true, true)) {
////            for (LineEntry lineEntry :
////                    timesheet.getLineEntries()) {
////                String workTypeString = lineEntry.getWorkType().toString();
////                if (workTypeName.equals(workTypeString)) {
////                    if (xyValues.containsKey(timesheet.getEmployee().toString())) {
////                        Integer existingHourTotal = xyValues.get(timesheet.getEmployee().toString());
////                        Integer newHourTotal = existingHourTotal + lineEntry.getTotalHours();
////                        xyValues.replace(timesheet.getEmployee().toString(), newHourTotal);
////                    } else {
////                        xyValues.put(timesheet.getEmployee().toString(), lineEntry.getTotalHours());
////                    }
////                }
////            }
////        }
////        return xyValues;
////    }
////
////    static HashMap<String, Integer> loadXyValuesForSecondaryCategoryEmployeeWhenPrimaryCategoryIsPayRate(TimesheetRepository timesheetRepository, String payRateString) {
////        HashMap<String, Integer> xyValues = new HashMap<>();
////        for (Timesheet timesheet :
////                timesheetRepository.findBySupervisorApprovalAndCompletionStatus(true, true)) {
////            if (payRateString.equals(timesheet.getCurrentPayRate().toString())) {
////                if (xyValues.containsKey(timesheet.getEmployee().toString())) {
////                    Integer existingHourTotal = xyValues.get(timesheet.getEmployee().toString());
////                    Integer newHourTotal = existingHourTotal + timesheet.getTotalHours();
////                    xyValues.put(timesheet.getEmployee().toString(), newHourTotal);
////                } else {
////                    xyValues.put(timesheet.getEmployee().toString(), timesheet.getTotalHours());
////                }
////            }
////        }
////        return xyValues;
////    }
//
//
//
//}
