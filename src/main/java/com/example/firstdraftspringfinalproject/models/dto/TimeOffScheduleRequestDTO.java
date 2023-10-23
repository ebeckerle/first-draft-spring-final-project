package com.example.firstdraftspringfinalproject.models.dto;

import com.example.firstdraftspringfinalproject.models.domainentityclasses.Employee;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

public class TimeOffScheduleRequestDTO {

    @Future(message = "this must be a future date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @Future(message = "this must be a future date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @NotBlank(message = "must not leave blank")
    @Size(min = 2, max = 80, message = "Reason listed must be greater than 2 characters and less than 80")
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
