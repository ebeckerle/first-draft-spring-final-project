package com.example.firstdraftspringfinalproject.models;

import org.springframework.format.annotation.DateTimeFormat;
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

//    @NotBlank(message = "Employee must have a first name.")
    private String firstName;

//    @NotBlank(message = "Employee must have a last name.")
    private String lastName;
    private String title;
    private String username;

    private String pwHash;

    private String otpHash;

    @OneToMany(mappedBy = "employee")
    private List<Timesheet> timesheets = new ArrayList<>();

    private Boolean currentTimesheetCompletionStatus = true;
    private Boolean supervisorAccess = false;
    private Integer payRate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private GregorianCalendar firstDateOfWork;
    private Integer paidTimeOff;

    private String email;
    private String address;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // Pay Rate?, eligible for benefits, remaining time off

//    public Employee (String firstName, String lastName, String title, Boolean supervisorAccess, Integer payRate, GregorianCalendar firstDateOfWork, Integer paidTimeOff, String oneTimePassword) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.title = title;
//        this.supervisorAccess = supervisorAccess;
//        this.payRate = payRate;
//        this.firstDateOfWork = firstDateOfWork;
//        this.paidTimeOff = paidTimeOff;
//        this.pwHash = encoder.encode(oneTimePassword);
//    }
    public Employee (String firstName, String lastName, String title, Integer payRate, Integer paidTimeOff, String oneTimePassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.payRate = payRate;
        this.paidTimeOff = paidTimeOff;
        this.pwHash = encoder.encode(oneTimePassword);
    }
//
//    public Employee (String firstName, String lastName, String username, String password) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.username = username;
//        this.pwHash = encoder.encode(password);
//    }

    public Employee (String username, String password) {
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

    public Integer getPaidTimeOff() {
        return paidTimeOff;
    }

    public void setPaidTimeOff(Integer paidTimeOff) {
        this.paidTimeOff = paidTimeOff;
    }

    public String getOtpHash() {
        return otpHash;
    }

    public void setPwHash(String password){
        this.pwHash = encoder.encode(password);
    }

    public Integer getTotalHoursWorkedToDate(){
        List<Timesheet> timesheets = this.timesheets;
        Integer totalHoursWorkedToDate = 0;
        for (Timesheet timesheet:
             timesheets) {
            if(timesheet.getSupervisorApproval()){
                totalHoursWorkedToDate += timesheet.getTotalHours();
            }
        }
        return totalHoursWorkedToDate;
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
