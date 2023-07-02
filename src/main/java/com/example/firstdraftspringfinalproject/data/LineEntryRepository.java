package com.example.firstdraftspringfinalproject.data;

import com.example.firstdraftspringfinalproject.models.domainentityclasses.Employee;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets.LineEntry;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Calendar;
import java.util.List;

public interface LineEntryRepository extends CrudRepository<LineEntry, Integer> {

    @Query(value = "SELECT a.first_last_name_combo, b.total_hours FROM employee a, line_entry b " +
            "WHERE b.timesheet_id = (SELECT id FROM timesheet WHERE supervisor_approval = true) AND b.timesheet_id = (SELECTa.id)",
            nativeQuery = true)
    List<String> findAllApprovedHoursByEmployee();


    @Query(value = "SELECT a.project_name, b.total_hours FROM project a, line_entry b " +
            "WHERE b.timesheet_id = (SELECT id FROM timesheet WHERE supervisor_approval = true) AND b.project_id = a.id",
            nativeQuery = true)
    List<String> findAllApprovedHoursByProject();

    @Query(value = "SELECT a.work_description, b.total_hours FROM work_type a, line_entry b " +
            "WHERE b.timesheet_id = (SELECT id FROM timesheet WHERE supervisor_approval = true) AND b.work_type_id = a.id",
            nativeQuery = true)
    List<String> findAllApprovedHoursByWorkType();

    @Query(value = "SELECT current_pay_rate, total_hours FROM timesheet WHERE supervisor_approval = true",
            nativeQuery = true)
    List<String> findAllApprovedHoursByPayRate();

    @Query(value = "SELECT a.work_type , b.total_hours FROM work_type a, line_entry b WHERE " +
            "b.timesheet_id = (SELECT id FROM timesheet WHERE supervisor_approval = true AND employee_id = :employeeId) " +
            "AND b.work_type_id = a.id",
            nativeQuery = true)
    List<String> findAllApprovedHoursOfEmployeeBrokenOutByProject(@Param("employeeId") Integer employeeId);

    @Query(value = "SELECT a.work_type , b.total_hours FROM work_type a, line_entry b WHERE " +
            "b.timesheet_id = (SELECT id FROM timesheet WHERE supervisor_approval = true AND employee_id = :employeeId) " +
            "AND b.work_type_id = a.id",
            nativeQuery = true)
    List<String> findAllApprovedHoursOfEmployeeBrokenOutByWorkType(@Param("employeeId") Integer employeeId);

    @Query(value = "SELECT a.pay_rate, b.total_hours FROM timesheet a, line_entry b WHERE " +
            "b.timesheet_id = (SELECT id FROM timesheet WHERE supervisor_approval = true AND employee_id = :employeeId)",
            nativeQuery = true)
    List<String> findAllApprovedHoursOfEmployeeBrokenOutByPayRate(@Param("employeeId") Integer employeeId);

    @Query(value = "",
            nativeQuery = true)
    List<String> findAllApprovedHoursOfProjectBrokenOutByEmployee(@Param("projectId") Integer projectId);

    @Query(value = "",
            nativeQuery = true)
    List<String> findAllApprovedHoursOfProjectBrokenOutByWorkType(@Param("projectId") Integer projectId);

    @Query(value = "",
            nativeQuery = true)
    List<String> findAllApprovedHoursOfProjectBrokenOutByPayRate(@Param("projectId") Integer projectId);

    @Query(value = "",
            nativeQuery = true)
    List<String> findAllApprovedHoursOfWorkTypeBrokenOutByEmployee(@Param("workTypeId") Integer workTypeId);

    @Query(value = "",
            nativeQuery = true)
    List<String> findAllApprovedHoursOfWorkTypeBrokenOutByProject(@Param("workTypeId") Integer workTypeId);

    @Query(value = "",
            nativeQuery = true)
    List<String> findAllApprovedHoursOfWorkTypeBrokenOutByPayRate(@Param("workTypeId") Integer workTypeId);

    @Query(value = "",
            nativeQuery = true)
    List<String> findAllApprovedHoursOfPayRateBrokenOutByEmployee(@Param("payRate") Integer payRate);

    @Query(value = "",
            nativeQuery = true)
    List<String> findAllApprovedHoursOfPayRateBrokenOutByProject(@Param("payRate") Integer payRate);

    @Query(value = "",
            nativeQuery = true)
    List<String> findAllApprovedHoursOfPayRateBrokenOutByWorkType(@Param("payRate") Integer payRate);

}
