package com.example.firstdraftspringfinalproject.models;

import com.example.firstdraftspringfinalproject.models.dto.NewContactDTO;
import com.example.firstdraftspringfinalproject.models.enums.ContactType;
import com.example.firstdraftspringfinalproject.models.interfaces.ContactConstants;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity
public class Contact implements ContactConstants {

    @Id
    @GeneratedValue
    private Integer id;

//    @NotNull(message = "Contact Type is required.")
    private ContactType contactType;

    private String firstName;
    private String lastName;
//    @NotBlank(message = "Company Name is required.")
//    @Size(max = 60, message = "Must be under 60 characters")
    private String companyName;
//    @Size(max = 80, message = "Must be under 80 characters")
    private String addressLineOne;
//    @Size(max = 60, message = "Must be under 60 characters")
    private String city;
//    @Size(max = 2, message = "Must be 2 characters")
    private String state;

//    @Size(max = 5, message = "ZipCode must be 5 characters")
    private String zipcode;

    @ElementCollection
//    @OneToMany(cascade = CascadeType.ALL)
    private List<String> email = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<@Valid PhoneNumber> phoneNumbers;

    public final static HashMap<String, String> ALLSTATESPOSTALCODES = ContactConstants.populateAllStatesHashMap();

    public Contact(NewContactDTO newContactDTO){
        this.contactType = newContactDTO.getContactType();
        this.companyName = newContactDTO.getCompanyName();

        if(newContactDTO.getLastName()!= null || newContactDTO.getLastName().equals("")){
            this.lastName = newContactDTO.getLastName();
        }
        if(newContactDTO.getFirstName()!= null || newContactDTO.getFirstName().equals("")){
            this.firstName = newContactDTO.getFirstName();
        }
        if(newContactDTO.getAddressLineOne()!= null || newContactDTO.getAddressLineOne().equals("")){
            this.addressLineOne = newContactDTO.getAddressLineOne();
        }
        if(newContactDTO.getCity()!= null || newContactDTO.getCity().equals("")){
            this.city = newContactDTO.getCity();
        }
        if(newContactDTO.getState()!= null || newContactDTO.getState().equals("")){
            this.state = newContactDTO.getState();
        }
        if(newContactDTO.getZipcode()!= null || newContactDTO.getZipcode().equals("")){
            this.zipcode = newContactDTO.getZipcode();
        }
        this.email = new ArrayList<>();
        if(newContactDTO.getEmail1()!= null || newContactDTO.getEmail1().equals("")){
            this.email.add(newContactDTO.getEmail1());
        }
        if(newContactDTO.getEmail2()!= null || newContactDTO.getEmail2().equals("")){
            this.email.add(newContactDTO.getEmail2());
        }

    }

    public Contact(ContactType contactType, String firstName, String lastName,
//                   @NotBlank(message = "Company Name is required.")
                   @Size(max = 60) String companyName,
                   String addressLineOne, String city, String state,
                   String zipcode,
                   @Email String email, String phoneNumber){
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
        this.companyName = "Company";
        this.contactType = contactType;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Contact(String companyName, ContactType contactType, String firstName, String lastName){
        this.companyName = companyName;
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
    public Contact(@NotBlank(message = "Company Name is required.")
                   @Size(max = 60) String companyName){
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

    public List<String> getEmail() {
        return email;
    }

    public void setAnEmail(@Email String email) {
        if (email.contains("@") && email.contains(".")){
            if(this.email == null){
                this.email = new ArrayList<>();
                this.email.add(email);
            }else{
                this.email.add(email);
            }
        }
    }

    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(ArrayList<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public void setAPhoneNumber(PhoneNumber phoneNumber){
        if(this.phoneNumbers == null){
            this.phoneNumbers = new ArrayList<>();
            this.phoneNumbers.add(phoneNumber);
        }else{
            this.phoneNumbers.add(phoneNumber);
        }
    }


    @Override
    public String toString() {
        return companyName;
    }
}
