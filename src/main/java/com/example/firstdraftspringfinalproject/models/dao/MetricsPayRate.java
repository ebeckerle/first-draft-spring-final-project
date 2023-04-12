package com.example.firstdraftspringfinalproject.models.dao;

import com.example.firstdraftspringfinalproject.data.TimesheetRepository;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.Project;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets.LineEntry;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets.ProjectWorkTypeSet;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets.Timesheet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface MetricsPayRate {

    static HashMap<String, Integer> loadXyValuesForPrimaryCategoryPayRate(TimesheetRepository timesheetRepository){
        HashMap<String, Integer> xyValues = new HashMap<>();
        List<Integer> payRates = new ArrayList<>();
        for (Timesheet timesheet:
                timesheetRepository.findBySupervisorApprovalAndCompletionStatus(true, true)) {
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
        return xyValues;
    }

    static HashMap<String, Integer> loadXyValuesForSecondaryCategoryPayRateWhenPrimaryCategoryIsProject(List<Timesheet> timesheets,
                                                                                                                Project project) {
        HashMap<String, Integer> xyValues = new HashMap<>();
        List<String> payRates = new ArrayList<>();
        for (Timesheet timesheet :
                timesheets) {
            List<LineEntry> lineEntries = timesheet.getLineEntries();
            for (LineEntry lineEntry :
                    lineEntries) {
                ProjectWorkTypeSet projectWorkTypeSet = lineEntry.getProjectWorkTypeCombo();
                if (projectWorkTypeSet.getProject().equals(project)) {
                    String payRate = "$" + timesheet.getCurrentPayRate() + " per hour";
                    if (payRates.contains(payRate)) {
                        Integer existingHourTotal = xyValues.get(payRate);
                        Integer newHourTotal = existingHourTotal + lineEntry.getTotalHours();
                        xyValues.replace(payRate, newHourTotal);
                    } else {
                        payRates.add(payRate);
                        xyValues.put(payRate, lineEntry.getTotalHours());
                    }
                }
            }
        }
        return xyValues;
    }

    static HashMap<String, Integer> loadXyValuesForSecondaryCategoryPayRateWhenPrimaryCategoryIsWorkType(TimesheetRepository timesheetRepository, String workTypeName) {
        HashMap<String, Integer> xyValues = new HashMap<>();
        for (Timesheet timesheet :
                timesheetRepository.findBySupervisorApprovalAndCompletionStatus(true, true)) {
            for (LineEntry lineEntry :
                    timesheet.getLineEntries()) {
                String workType1 = lineEntry.getProjectWorkTypeCombo().getWorkType().toString();
                if (workTypeName.equals(workType1)) {
                    String payRate1 = "$" + timesheet.getCurrentPayRate() + " per hour";
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
        return xyValues;
    }

    static List<Integer> loadListOfPayRates(List<Timesheet> timesheets){
        ArrayList<Integer> payRates = new ArrayList<>();
        for (Timesheet timesheet:
                timesheets) {
            if (!payRates.contains(timesheet.getCurrentPayRate())){
                payRates.add(timesheet.getCurrentPayRate());
            }
        }
        return payRates;
    }


}
