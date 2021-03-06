package com.example.firstdraftspringfinalproject.models.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegisterFormDTO extends LoginFormDTO {

    @NotNull
    @NotBlank
    private String firstName;

    @NotNull
    @NotBlank
    private String lastName;

    @Size(min = 5, max = 5, message = "Your first time passcode should be 5 characters long")
    private String firstTimePassword;

    private  String verifyPassword;

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
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

    public String getFirstTimePassword() {
        return firstTimePassword;
    }

    public void setFirstTimePassword(String firstTimePassword) {
        this.firstTimePassword = firstTimePassword;
    }

}
