package com.example.firstdraftspringfinalproject.data;

import com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets.LineEntry;
import org.springframework.data.repository.CrudRepository;

public interface LineEntryRepository extends CrudRepository<LineEntry, Integer> {

//    LineEntry findByProjectWorkTypeComboAndTimesheet(ProjectWorkTypeSet projectWorkTypeSet, Timesheet timesheet);

}
