package com.example.firstdraftspringfinalproject.models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

@Entity
public class Employee {

    @Id
    @GeneratedValue
    private Integer employeeId;

    @NotBlank(message = "Employee must have a first name.")
    private String firstName;

    @NotBlank(message = "Employee must have a last name.")
    private String lastName;
    private String title;
    private String username;

    @NotNull
    private String pwHash;

    @OneToMany(mappedBy = "employee")
    private List<Timesheet> timesheets = new ArrayList<>();

    private Boolean currentTimesheetCompletionStatus = true;
    private Boolean supervisorAccess;
    private Integer payRate;
    private GregorianCalendar firstDateOfWork;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // Pay Rate?, eligible for benefits, remaining time off

    public Employee (String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Employee (String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.pwHash = encoder.encode(password);
    }

    public Employee () {}


    //  GETTERS & SETTERS


    public Integer getEmployeeId() {
        return employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }


    public List<Timesheet> getTimesheets() {
        return timesheets;
    }

    public Timesheet getCurrentTimesheet(){
        Timesheet returnedTimesheet = null;
        for (Timesheet timesheet:
             this.timesheets) {
            if (!timesheet.getCompletionStatus()){
                returnedTimesheet = timesheet;
            }
        }
        return returnedTimesheet;
    }

    public Boolean getCurrentTimesheetCompletionStatus() {
        return currentTimesheetCompletionStatus;
    }

    public void setCurrentTimesheetCompletionStatus(Boolean currentTimesheetCompletionStatus) {
        this.currentTimesheetCompletionStatus = currentTimesheetCompletionStatus;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Boolean getSupervisorAccess() {
        return supervisorAccess;
    }

    public void setSupervisorAccess(Boolean supervisorAccess) {
        this.supervisorAccess = supervisorAccess;
    }

    public Integer getPayRate() {
        return payRate;
    }

    public void setPayRate(Integer payRate) {
        this.payRate = payRate;
    }

    public GregorianCalendar getFirstDateOfWork() {
        return firstDateOfWork;
    }

    public void setFirstDateOfWork(GregorianCalendar firstDateOfWork) {
        this.firstDateOfWork = firstDateOfWork;
    }


//to String & Equals Methods


    @Override
    public String toString() {
        return firstName + ' ' + lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return employeeId.equals(employee.employeeId);
    }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }

}
