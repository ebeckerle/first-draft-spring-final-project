package com.example.firstdraftspringfinalproject.models.dao.metrics;

import com.example.firstdraftspringfinalproject.data.TimesheetRepository;
import com.example.firstdraftspringfinalproject.data.WorkTypeRepository;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.Project;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.WorkType;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets.LineEntry;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets.Timesheet;

import java.util.HashMap;
import java.util.List;

public interface MetricsWorkType {

    static HashMap<String, Integer> loadXyValuesForPrimaryCategoryWorkType(TimesheetRepository timesheetRepository, WorkTypeRepository workTypeRepository){
        HashMap<String, Integer> xyValues = new HashMap<>();
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
        return xyValues;
    }

    static HashMap<String, Integer> loadXyValuesForSecondaryCategoryWorkType(WorkTypeRepository workTypeRepository, List<Timesheet> employeesTimesheets){
        HashMap<String, Integer> xyValues = new HashMap<>();
        List<WorkType> workTypes = (List<WorkType>) workTypeRepository.findAll();
        Integer totalHoursForX = 0;
        for (WorkType aWorkType :
                workTypes) {
            totalHoursForX = 0;
            for (Timesheet timesheet : employeesTimesheets) {
                totalHoursForX += timesheet.getTotalHoursByWorkType(aWorkType);
                xyValues.put(aWorkType.toString(), totalHoursForX);
            }
        }
        return xyValues;
    }
    static HashMap<String, Integer> loadXyValuesForSecondaryCategoryWorkTypeWhenPrimaryCategoryIsProject(List<Timesheet> timesheets, Project project){
        HashMap<String, Integer> xyValues = new HashMap<>();
        for (Timesheet timesheet : timesheets) {
            List<LineEntry> lineEntries = timesheet.getLineEntries();
            for (LineEntry lineEntry :
                    lineEntries) {
                Integer totalHoursForX = 0;
               if (lineEntry.getProject().equals(project)) {
                    if (xyValues.containsKey(lineEntry.getWorkType().toString())) {
                        totalHoursForX += xyValues.get(lineEntry.getWorkType().toString());
                        totalHoursForX += lineEntry.getTotalHours();
                        xyValues.replace(lineEntry.getWorkType().toString(), totalHoursForX);
                    } else {
                        totalHoursForX += lineEntry.getTotalHours();
                        xyValues.put(lineEntry.getWorkType().toString(), totalHoursForX);
                    }
                }
            }
        }
        return xyValues;
    }

    static HashMap<String, Integer> loadXyValuesForSecondaryCategoryWorkTypeWhenPrimaryCategoryIsPayRate(TimesheetRepository timesheetRepository, String payRateString) {
        HashMap<String, Integer> xyValues = new HashMap<>();
        for (Timesheet timesheet :
                timesheetRepository.findBySupervisorApprovalAndCompletionStatus(true, true)) {
            for (LineEntry lineEntry :
                    timesheet.getLineEntries()) {
                if (payRateString.equals(timesheet.getCurrentPayRate().toString())) {
                    if (xyValues.containsKey(lineEntry.getWorkType().toString())) {
                        Integer existingHourTotal = xyValues.get(lineEntry.getWorkType().toString());
                        Integer newHourTotal = existingHourTotal + lineEntry.getTotalHours();
                        xyValues.put(lineEntry.getWorkType().toString(), newHourTotal);
                    } else {
                        xyValues.put(lineEntry.getWorkType().toString(), lineEntry.getTotalHours());
                    }
                }
            }
        }
        return xyValues;
    }
}
