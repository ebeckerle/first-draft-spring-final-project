package com.example.firstdraftspringfinalproject.models;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Employee {

    private static Integer nextId = 1;
    private Integer employeeId;
    private String firstName;
    private String lastName;
    private String title;
    private String userName = "defaultUserName"; //--> TODO: code a random generator before the employee resets it.
    private String password = "password";
    private ArrayList<Timesheet> timesheets;
    private Boolean currentTimesheetCompletionStatus;
    private Boolean supervisorAccess;
    private float payRate;
    private GregorianCalendar firstDateOfWork;

    // Pay Rate?, eligible for benefits, remaining time off

    public Employee (String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeId = nextId;
        this.timesheets = new ArrayList<>();
        nextId++;
    }

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Timesheet> getTimesheets() {
        return timesheets;
    }

    public void setTimesheets(ArrayList<Timesheet> timesheets) {
        this.timesheets = timesheets;
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

    //getCurrentTimesheet method - here or in Employee Data class?

    //to String & Equals Methods

    public String toStringEmployeeFirstAndLastName(Employee aEmployee){
        return aEmployee.getFirstName() + " " + aEmployee.getLastName();
    }

    public String toStringEmployeeId(Employee aEmployee){
        String employeeIdAsString;
        employeeIdAsString = String.valueOf(aEmployee.getEmployeeId());
        return employeeIdAsString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return employeeId.equals(employee.employeeId);
    }

}
