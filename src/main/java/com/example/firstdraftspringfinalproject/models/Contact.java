package com.example.firstdraftspringfinalproject.models;

import com.example.firstdraftspringfinalproject.models.enums.ContactType;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;

@Entity
public class Contact {

    @Id
    @GeneratedValue
    private Integer id;

    private ContactType contactType;

//    @NotNull
//    @NotBlank
    private String firstName;
    private String lastName;
    private String companyName;
    private String addressLineOne;
    private String city;
    private String state;

    @Size(min = 5, max = 5, message = "ZipCode must be 5 characters")
    private String zipcode;

//    @OneToMany
    private ArrayList<String> email = new ArrayList<>();

//    @OneToMany
    private ArrayList<PhoneNumber> phoneNumbers;

    public Contact(ContactType contactType, String firstName, String lastName, String companyName, String addressLineOne, String city, String state, String zipcode, String email, String phoneNumber){
        this.contactType = contactType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.companyName = companyName;
        this.addressLineOne = addressLineOne;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.email.add(email);
        this.phoneNumbers = new ArrayList<>();
        this.phoneNumbers.add(new PhoneNumber(phoneNumber));
    }

    //Constructor for New Employees
    public Contact(ContactType contactType, String firstName, String lastName){
        this.contactType = contactType;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    //Constructor for Emergency Contacts
    public Contact(ContactType contactType, String firstName, String lastName, String phoneNumber){
        this.contactType = contactType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumbers = new ArrayList<>();
        this.phoneNumbers.add(new PhoneNumber(phoneNumber));
    }

    //Constructor for Carriers
    public Contact(String companyName){
        this.companyName = companyName;
    }

    public Contact() {
    }

    public Integer getId() {
        return id;
    }

    public ContactType getContactType() {
        return contactType;
    }

    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddressLineOne() {
        return addressLineOne;
    }

    public void setAddressLineOne(String addressLineOne) {
        this.addressLineOne = addressLineOne;
    }



    public ArrayList<String> getEmail() {
        return email;
    }

    public void setAnEmail(String email) {
        if(this.email == null){
            this.email = new ArrayList<>();
            this.email.add(email);
        }else{
            this.email.add(email);
        }
    }

    public ArrayList<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(ArrayList<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }


    @Override
    public String toString() {
        return companyName;
    }
}
