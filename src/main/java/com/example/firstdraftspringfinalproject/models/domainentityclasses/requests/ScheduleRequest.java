package com.example.firstdraftspringfinalproject.models.domainentityclasses.requests;


import com.example.firstdraftspringfinalproject.models.domainentityclasses.Employee;
import com.example.firstdraftspringfinalproject.models.dto.TimeOffScheduleRequestDTO;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ScheduleRequest {

    @Id
    @GeneratedValue
    private Integer id;

    @Temporal(TemporalType.DATE)
    private Date startDateTime;
    @Temporal(TemporalType.DATE)
    private Date endDateTime;

    private String noteOrReasonForRequest;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public ScheduleRequest() {
    }

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

    @Override
    public String toString() {
        return "ScheduleRequest{" +
                "startDateTime=" + startDateTime +
                ", endDateTime=" + endDateTime +
                ", noteOrReasonForRequest='" + noteOrReasonForRequest + '\'' +
                ", employee=" + employee +
                '}';
    }
}
