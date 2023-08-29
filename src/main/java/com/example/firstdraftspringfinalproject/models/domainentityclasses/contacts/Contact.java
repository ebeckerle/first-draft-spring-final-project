package com.example.firstdraftspringfinalproject.models.domainentityclasses.contacts;

import com.example.firstdraftspringfinalproject.models.dto.EditContactDetailsDTO;
import com.example.firstdraftspringfinalproject.models.dto.NewContactDTO;
import com.example.firstdraftspringfinalproject.models.enums.ContactType;
import com.example.firstdraftspringfinalproject.models.interfaces.ContactConstants;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="d_contact_type",
        discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue("0")
public class Contact implements ContactConstants {

    @Id
    @GeneratedValue
    protected Integer id;

//    @NotNull(message = "Contact Type is required.")
    protected ContactType contactType;

    protected String firstName;
    protected String lastName;
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
    protected List<@Valid PhoneNumber> phoneNumbers;

    public final static HashMap<String, String> ALLSTATESPOSTALCODES = ContactConstants.populateAllStatesHashMap();

    public Contact(NewContactDTO newContactDTO){
        this.contactType = newContactDTO.getContactType();
        this.companyName = newContactDTO.getCompanyName();

        if(newContactDTO.getLastName()!= null){
            if(!newContactDTO.getLastName().equals("")){
                this.lastName = newContactDTO.getLastName();
            }
        }
        if(newContactDTO.getFirstName()!= null){
            if (!newContactDTO.getFirstName().equals("")){
                this.firstName = newContactDTO.getFirstName();
            }
        }
        if(newContactDTO.getAddressLineOne()!= null){
            if(!newContactDTO.getAddressLineOne().equals("")){
                this.addressLineOne = newContactDTO.getAddressLineOne();
            }
        }
        if(newContactDTO.getCity()!= null){
            if(!newContactDTO.getCity().equals("")){
                this.city = newContactDTO.getCity();
            }
        }
        if(newContactDTO.getState()!= null){
            if(!newContactDTO.getState().equals("")){
                this.state = newContactDTO.getState();
            }
        }
        if(newContactDTO.getZipcode()!= null){
            if(!newContactDTO.getZipcode().equals("")){
                this.zipcode = newContactDTO.getZipcode();
            }
        }
        this.email = new ArrayList<>();
        if(newContactDTO.getEmail1()!= null){
            if(!newContactDTO.getEmail1().equals("")){
                this.email.add(newContactDTO.getEmail1());
            }
        }
        if(newContactDTO.getEmail2()!= null){
            if(!newContactDTO.getEmail2().equals("")){
                this.email.add(newContactDTO.getEmail2());
            }
        }
        this.phoneNumbers = new ArrayList<>();
        if(newContactDTO.getPhoneNumber1()!= null){
            if(!newContactDTO.getPhoneNumber1().equals("")){
                if(newContactDTO.getCountryCodePhoneNumber1() != null){
                    if(!newContactDTO.getCountryCodePhoneNumber1().equals("")){
                        PhoneNumber phoneNumber = new PhoneNumber(newContactDTO.getPhoneNumber1());
                        phoneNumber.setCountryCode(newContactDTO.getCountryCodePhoneNumber1());
                        this.phoneNumbers.add(phoneNumber);
                    }
                }else if(newContactDTO.getExtPhoneNumber1() != null){
                    if(!newContactDTO.getExtPhoneNumber1().equals("")){
                        PhoneNumber phoneNumber = new PhoneNumber(newContactDTO.getPhoneNumber1());
                        phoneNumber.setExtension(newContactDTO.getExtPhoneNumber1());
                        this.phoneNumbers.add(phoneNumber);
                    }
                }else{
                    PhoneNumber phoneNumber = new PhoneNumber(newContactDTO.getPhoneNumber1());
                    this.phoneNumbers.add(phoneNumber);
                }
            }

        }
        if(newContactDTO.getPhoneNumber2()!= null){
            if(!newContactDTO.getPhoneNumber2().equals("")){
                if(newContactDTO.getCountryCodePhoneNumber2() != null){
                    if(!newContactDTO.getCountryCodePhoneNumber2().equals("")){
                        PhoneNumber phoneNumber = new PhoneNumber(newContactDTO.getExtPhoneNumber2());
                        phoneNumber.setCountryCode(newContactDTO.getCountryCodePhoneNumber2());
                        this.phoneNumbers.add(phoneNumber);
                    }
                }else if(newContactDTO.getExtPhoneNumber2() != null){
                    if(!newContactDTO.getExtPhoneNumber2().equals("")){
                        PhoneNumber phoneNumber = new PhoneNumber(newContactDTO.getPhoneNumber2());
                        phoneNumber.setExtension(newContactDTO.getExtPhoneNumber2());
                        this.phoneNumbers.add(phoneNumber);
                    }
                }else{
                    PhoneNumber phoneNumber = new PhoneNumber(newContactDTO.getPhoneNumber2());
                    this.phoneNumbers.add(phoneNumber);
                }
            }
        }

    }

    public Contact(EditContactDetailsDTO editContactDetailsDTO){

        if(editContactDetailsDTO.getAddressLineOne()!= null){
            if(!editContactDetailsDTO.getAddressLineOne().equals("")){
                this.addressLineOne = editContactDetailsDTO.getAddressLineOne();
            }
        }
        if(editContactDetailsDTO.getCity()!= null){
            if(!editContactDetailsDTO.getCity().equals("")){
                this.city = editContactDetailsDTO.getCity();
            }
        }
        if(editContactDetailsDTO.getState()!= null){
            if(!editContactDetailsDTO.getState().equals("")){
                this.state = editContactDetailsDTO.getState();
            }
        }
        if(editContactDetailsDTO.getZipcode()!= null){
            if(!editContactDetailsDTO.getZipcode().equals("")){
                this.zipcode = editContactDetailsDTO.getZipcode();
            }
        }
        this.email = new ArrayList<>();
        if(editContactDetailsDTO.getEmail1()!= null){
            if(!editContactDetailsDTO.getEmail1().equals("")){
                this.email.add(editContactDetailsDTO.getEmail1());
            }
        }
        if(editContactDetailsDTO.getEmail2()!= null){
            if(!editContactDetailsDTO.getEmail2().equals("")){
                this.email.add(editContactDetailsDTO.getEmail2());
            }
        }
        this.phoneNumbers = new ArrayList<>();
        if(editContactDetailsDTO.getPhoneNumber1()!= null){
            if(!editContactDetailsDTO.getPhoneNumber1().equals("")){
                    PhoneNumber phoneNumber = new PhoneNumber(editContactDetailsDTO.getPhoneNumber1());
                    this.phoneNumbers.add(phoneNumber);
            }

        }
        if(editContactDetailsDTO.getPhoneNumber2()!= null){
            if(!editContactDetailsDTO.getPhoneNumber2().equals("")){
                PhoneNumber phoneNumber = new PhoneNumber(editContactDetailsDTO.getPhoneNumber2());
                this.phoneNumbers.add(phoneNumber);
            }
        }

    }

    public Contact(ContactType contactType, String firstName, String lastName, List<PhoneNumber> phoneNumbers){
        this.contactType = contactType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumbers = phoneNumbers;
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

    public Contact(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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
