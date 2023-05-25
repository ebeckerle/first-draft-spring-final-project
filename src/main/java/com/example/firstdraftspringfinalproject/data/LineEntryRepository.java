package com.example.firstdraftspringfinalproject.data;

import com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets.LineEntry;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LineEntryRepository extends CrudRepository<LineEntry, Integer> {


    @Query(value = "SELECT a.project_name, b.total_hours FROM project a, line_entry b " +
            "WHERE b.timesheet_id = (SELECT id FROM timesheet WHERE supervisor_approval = true) AND b.project_id = a.id",
            nativeQuery = true)
    List<String> findAllHoursByProject();

    @Query(value = "SELECT a.work_description, b.total_hours FROM work_type a, line_entry b " +
            "WHERE b.timesheet_id = (SELECT id FROM timesheet WHERE supervisor_approval = true) AND b.work_type_id = a.id",
            nativeQuery = true)
    List<String> findAllHoursByWorkType();


}
