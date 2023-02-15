package com.example.firstdraftspringfinalproject.models.dto;


import com.example.firstdraftspringfinalproject.models.constraints.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class EditContactDetailsDTO {


    @Size(max = 80, message = "Must be under 80 characters")
    @NotBlank(message = "Must be left empty or include a non-whitespace character")
    private String addressLineOne;
    @Size(max = 60, message = "Must be under 60 characters")
    @NotBlank(message = "Must be left empty or include a non-whitespace character")
    private String city;
    @OptionalState
    @NotBlank(message = "Must be left empty or include a non-whitespace character")
    private String state;
    @OptionalZipcode
    @NotBlank(message = "Must be left empty or include a non-whitespace character")
    private String zipcode;

    @Email
    @NotBlank(message = "Must be left empty or include a non-whitespace character")
    private String email1;
    @Email
    @NotBlank(message = "Must be left empty or include a non-whitespace character")
    private String email2;

    @OptionalPhoneNumber
    @NotBlank(message = "Must be left empty or include a non-whitespace character")
    private String phoneNumber1;


    @OptionalPhoneNumber
    @NotBlank(message = "Must be left empty or include a non-whitespace character")
    private String phoneNumber2;


    @Size(max = 40, message = "Must be under 40 characters")
    @NotBlank(message = "Must be left empty or include a non-whitespace character")
    private String ecFirstName;

    @Size(max = 40, message = "Must be under 40 characters")
    @NotBlank(message = "Must be left empty or include a non-whitespace character")
    private String ecLastName;

    @OptionalPhoneNumber
    @NotBlank(message = "Must be left empty or include a non-whitespace character")
    private String ecPhoneNumber;

    @Size(max = 20, message = "Must be under 20 characters")
    @NotBlank(message = "Must be left empty or include a non-whitespace character")
    private String ecRelationship;

    public String getAddressLineOne() {
        return addressLineOne;
    }

    public void setAddressLineOne(String addressLineOne) {
        this.addressLineOne = addressLineOne;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getPhoneNumber1() {
        return phoneNumber1;
    }

    public void setPhoneNumber1(String phoneNumber1) {
        this.phoneNumber1 = phoneNumber1;
    }

    public String getPhoneNumber2() {
        return phoneNumber2;
    }

    public void setPhoneNumber2(String phoneNumber2) {
        this.phoneNumber2 = phoneNumber2;
    }

    public String getEcFirstName() {
        return ecFirstName;
    }

    public void setEcFirstName(String ecFirstName) {
        this.ecFirstName = ecFirstName;
    }

    public String getEcLastName() {
        return ecLastName;
    }

    public void setEcLastName(String ecLastName) {
        this.ecLastName = ecLastName;
    }

    public String getEcPhoneNumber() {
        return ecPhoneNumber;
    }

    public void setEcPhoneNumber(String ecPhoneNumber) {
        this.ecPhoneNumber = ecPhoneNumber;
    }

    public String getEcRelationship() {
        return ecRelationship;
    }

    public void setEcRelationship(String ecRelationship) {
        this.ecRelationship = ecRelationship;
    }

    //TODO - account for if a blank space is inputed?? - or do this in the validation annotations?
    public boolean areThereAnyValuesInTheEmergContactToUpdate(){
        if (this.ecLastName.equals("")&&this.ecFirstName.equals("")
                &&this.ecPhoneNumber.equals("")&&this.ecRelationship.equals("")){
            return false;
        }
        return true;
    }


}
