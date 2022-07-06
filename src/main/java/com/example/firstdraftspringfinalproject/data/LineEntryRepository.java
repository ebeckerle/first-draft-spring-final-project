package com.example.firstdraftspringfinalproject.data;

import com.example.firstdraftspringfinalproject.models.LineEntry;
import com.example.firstdraftspringfinalproject.models.ProjectWorkTypeSet;
import com.example.firstdraftspringfinalproject.models.Timesheet;
import org.springframework.data.repository.CrudRepository;

public interface LineEntryRepository extends CrudRepository<LineEntry, Integer> {

    LineEntry findByProjectWorkTypeComboAndTimesheet(ProjectWorkTypeSet projectWorkTypeSet, Timesheet timesheet);

}
