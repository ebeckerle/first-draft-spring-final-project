package com.example.firstdraftspringfinalproject.models.dao;

import com.example.firstdraftspringfinalproject.data.ProjectRepository;
import com.example.firstdraftspringfinalproject.data.TimesheetRepository;
import com.example.firstdraftspringfinalproject.data.WorkTypeRepository;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.Project;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets.LineEntry;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets.Timesheet;

import java.util.HashMap;
import java.util.List;

public interface MetricsProject {


    static HashMap<String, Integer> loadXyValuesForPrimaryCategoryProject(TimesheetRepository timesheetRepository, ProjectRepository projectRepository) {
        HashMap<String, Integer> xyValues = new HashMap<>();
        List<Project> projects = (List<Project>) projectRepository.findAll();
        for (Project project :
                projects) {
            Integer totalHoursForProject = 0;
            for (Timesheet timesheet :
                    timesheetRepository.findBySupervisorApprovalAndCompletionStatus(true, true)) {
                totalHoursForProject += timesheet.getTotalHoursByProject(project);
            }
            xyValues.put(project.getProjectName(), totalHoursForProject);
        }
        return xyValues;
    }

    static HashMap<String, Integer> loadXyValuesForSecondaryCategoryProject(ProjectRepository projectRepository,
                                                                             List<Timesheet> employeesTimesheets){
        HashMap<String, Integer> xyValues = new HashMap<>();
        List<Project> projects = (List<Project>) projectRepository.findAll();
        Integer totalHoursForX;
        for(Project aProject :
            projects) {
                totalHoursForX = 0;
                for (Timesheet timesheet : employeesTimesheets) {
                    totalHoursForX += timesheet.getTotalHoursByProject(aProject);
                    xyValues.put(aProject.toString(), totalHoursForX);
                }
        }
        return xyValues;
    }

    static HashMap<String, Integer> loadXyValuesForSecondaryCategoryProjectWhenPrimaryCategoryIsWorkType(TimesheetRepository timesheetRepository, String workTypeName) {
        HashMap<String, Integer> xyValues = new HashMap<>();
        for (Timesheet timesheet :
                timesheetRepository.findBySupervisorApprovalAndCompletionStatus(true, true)) {
            for (LineEntry lineEntry :
                    timesheet.getLineEntries()) {
                String workTypeString = lineEntry.getWorkType().toString();
                if (workTypeName.equals(workTypeString)) {
                    if (xyValues.containsKey(lineEntry.getProject().toString())) {
                        Integer existingHourTotal = xyValues.get(lineEntry.getProject().toString());
                        Integer newHourTotal = existingHourTotal + lineEntry.getTotalHours();
                        xyValues.replace(lineEntry.getProject().toString(), newHourTotal);
                    } else {
                        xyValues.put(lineEntry.getProject().toString(), lineEntry.getTotalHours());
                    }
                }
            }
        }
        return xyValues;
    }


    static HashMap<String, Integer> getXyValuesForSecondaryCategoryProjectWhenPrimaryCategoryIsPayRate(TimesheetRepository timesheetRepository, String payRateString) {
        HashMap<String, Integer> xyValues = new HashMap<>();
        for (Timesheet timesheet :
                timesheetRepository.findBySupervisorApprovalAndCompletionStatus(true, true)) {
            for (LineEntry lineEntry :
                    timesheet.getLineEntries()) {
                if (payRateString.equals(timesheet.getCurrentPayRate().toString())) {
                    if (xyValues.containsKey(lineEntry.getProject().toString())) {
                        Integer existingHourTotal = xyValues.get(lineEntry.getProject().toString());
                        Integer newHourTotal = existingHourTotal + lineEntry.getTotalHours();
                        xyValues.put(lineEntry.getProject().toString(), newHourTotal);
                    } else {
                        xyValues.put(lineEntry.getProject().toString(), lineEntry.getTotalHours());
                    }
                }
            }
        }
        return xyValues;
    }
}
