package com.example.firstdraftspringfinalproject.models.dto;

import com.example.firstdraftspringfinalproject.models.PhoneNumber;
import com.example.firstdraftspringfinalproject.models.enums.ContactType;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class NewContactDTO {


    //TODO??? -
    @NotNull(message = "Contact Type is required.")
    private ContactType contactType;

    @Size(max = 40, message = "Must be under 40 characters")
    private String firstName;
    @Size(max = 40, message = "Must be under 40 characters")
    private String lastName;
    @NotBlank(message = "Company Name is required.")
    @Size(max = 60, message = "Must be under 60 characters")
    private String companyName;
    @Size(max = 80, message = "Must be under 80 characters")
    private String addressLineOne;
    @Size(max = 60, message = "Must be under 60 characters")
    private String city;
    @Size(max = 2, message = "Must be 2 characters")
    private String state;

    @Size(max = 5, message = "ZipCode must be 5 characters")
    private String zipcode;

    @Email
    private String email1;
    @Email
    private String email2;

    @Valid
    private PhoneNumber phoneNumber1;
    @Valid
    private PhoneNumber phoneNumber2;



    public NewContactDTO(ContactType contactType, String firstName, String lastName,
                         String companyName, String addressLineOne, String city,
                         String state, String zipcode) {
        this.contactType = contactType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.companyName = companyName;
        this.addressLineOne = addressLineOne;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
    }

    public NewContactDTO(ContactType contactType, String companyName) {
        this.contactType = contactType;
        this.companyName = companyName;
    }

    public NewContactDTO(ContactType contactType, String firstName, String lastName,
                         String companyName, String addressLineOne, String city, String state,
                         String zipcode, String email1, String email2,
                         PhoneNumber phoneNumber1, PhoneNumber phoneNumber2) {
        this.contactType = contactType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.companyName = companyName;
        this.addressLineOne = addressLineOne;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.email1 = email1;
        this.email2 = email2;
        this.phoneNumber1 = phoneNumber1;
        this.phoneNumber2 = phoneNumber2;
    }

    public NewContactDTO(ContactType contactType, String firstName, String lastName,
                         String companyName, String addressLineOne, String city, String state,
                         String zipcode, String email1, PhoneNumber phoneNumber1) {
        this.contactType = contactType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.companyName = companyName;
        this.addressLineOne = addressLineOne;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.email1 = email1;
        this.phoneNumber1 = phoneNumber1;
    }
}
