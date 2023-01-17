package com.example.firstdraftspringfinalproject.models.dto;

import com.example.firstdraftspringfinalproject.models.Contact;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.Date;
import java.util.GregorianCalendar;


public class CreateEmployeeDTO {

    @NotBlank(message = "Employee must have a first name.")
    private String firstName;

    @NotBlank(message = "Employee must have a last name.")
    private String lastName;
    private String title;

    private String oneTimePassword;

    private Boolean supervisorAccess = false;

    @Positive(message = "Please enter an eligible pay rate (dollars per hour). The the pay rate must be a positive number")
    private Integer payRate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private GregorianCalendar firstDateOfWork;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date firstDateOfWorkDateClass;

    @Positive
    private Integer paidTimeOff;

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

    public String getOneTimePassword() {
        return oneTimePassword;
    }

    public void setOneTimePassword(String oneTimePassword) {
        this.oneTimePassword = oneTimePassword;
    }

    public Integer getPayRate() {
        return payRate;
    }

    public void setPayRate(Integer payRate) {
        this.payRate = payRate;
    }

    public Integer getPaidTimeOff() {
        return paidTimeOff;
    }

    public void setPaidTimeOff(Integer paidTimeOff) {
        this.paidTimeOff = paidTimeOff;
    }

    public Boolean getSupervisorAccess() {
        return supervisorAccess;
    }

    public void setSupervisorAccess(Boolean supervisorAccess) {
        this.supervisorAccess = supervisorAccess;
    }
}
