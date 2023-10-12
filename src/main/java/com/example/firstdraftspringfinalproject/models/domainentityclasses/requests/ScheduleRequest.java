package com.example.firstdraftspringfinalproject.models.domainentityclasses.requests;


import com.example.firstdraftspringfinalproject.models.domainentityclasses.Employee;
import com.example.firstdraftspringfinalproject.models.dto.TimeOffScheduleRequestDTO;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class ScheduleRequest {

    @Temporal(TemporalType.DATE)
    private Date startDateTime;
    @Temporal(TemporalType.DATE)
    private Date endDateTime;

    private String noteOrReasonForRequest;
    private Employee employee;

    public ScheduleRequest(TimeOffScheduleRequestDTO timeOffScheduleRequestDTO) {
        this.startDateTime = timeOffScheduleRequestDTO.getStartDate();
        this.endDateTime = timeOffScheduleRequestDTO.getEndDate();
        this.noteOrReasonForRequest = timeOffScheduleRequestDTO.getNoteOrReasonForRequest();
        this.employee = timeOffScheduleRequestDTO.getEmployee();
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getNoteOrReasonForRequest() {
        return noteOrReasonForRequest;
    }

    public void setNoteOrReasonForRequest(String noteOrReasonForRequest) {
        this.noteOrReasonForRequest = noteOrReasonForRequest;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}