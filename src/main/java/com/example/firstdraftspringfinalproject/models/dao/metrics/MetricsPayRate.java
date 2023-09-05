package com.example.firstdraftspringfinalproject.models.dao.metrics;

import com.example.firstdraftspringfinalproject.data.TimesheetRepository;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.Project;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets.LineEntry;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets.Timesheet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface MetricsPayRate {

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
