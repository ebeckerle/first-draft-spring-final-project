package com.example.firstdraftspringfinalproject.models.dto;

import com.example.firstdraftspringfinalproject.models.domainentityclasses.Employee;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

public class TimeOffScheduleRequestDTO {

    @Future
    private Date startDate;
    @Future
    private Date endDate;

    @NotBlank
    @Size(min = 2, max = 80)
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
