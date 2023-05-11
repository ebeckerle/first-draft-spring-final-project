package com.example.firstdraftspringfinalproject.data;

import com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets.LineEntry;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LineEntryRepository extends CrudRepository<LineEntry, Integer> {

//    LineEntry findByProjectWorkTypeComboAndTimesheet(ProjectWorkTypeSet projectWorkTypeSet, Timesheet timesheet);

    @Query(value = "SELECT first_name_last_name_combo, total_hours_worked_to_date FROM employee",
            nativeQuery = true)
    List<String> findAllHoursByProject();

}
