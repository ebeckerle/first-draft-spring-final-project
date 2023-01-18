package com.example.firstdraftspringfinalproject.models.dto;

import com.example.firstdraftspringfinalproject.models.PhoneNumber;
import com.example.firstdraftspringfinalproject.models.enums.ContactType;

import javax.validation.Valid;
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

    private List<String> email = new ArrayList<>();

    private List<@Valid PhoneNumber> phoneNumbers;

}
