package com.example.firstdraftspringfinalproject.models.dto;

import com.example.firstdraftspringfinalproject.models.domainentityclasses.Employee;

import java.util.Date;

public class TimeOffScheduleRequestDTO {


    private Date startDate;
    private Date endDate;

    private String noteOrReasonForRequest;

    private Employee employee;


    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
