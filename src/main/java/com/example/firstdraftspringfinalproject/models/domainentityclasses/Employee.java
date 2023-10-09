package com.example.firstdraftspringfinalproject.models.domainentityclasses;

import com.example.firstdraftspringfinalproject.models.domainentityclasses.contacts.Contact;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.contacts.EmergencyContact;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.contacts.PhoneNumber;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets.Timesheet;
import com.example.firstdraftspringfinalproject.models.dto.EditContactDetailsDTO;
import com.example.firstdraftspringfinalproject.models.enums.ContactType;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

@Entity
public class Employee {

    @Id
    @GeneratedValue
    private Integer id;

    private String firstName;
    private String lastName;
    private String firstNameLastNameCombo;
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

    @OneToOne(cascade = CascadeType.ALL)
    private Contact contactInfo;

    @OneToOne(cascade = CascadeType.ALL)
    private EmergencyContact emergencyContact;

    private Integer totalHoursWorkedToDate = 0;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public Employee (String firstName, String lastName, String title, Integer payRate, Integer paidTimeOff, String oneTimePassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactInfo = new Contact(ContactType.EMPLOYEE, firstName, lastName);
        this.firstNameLastNameCombo = firstName + ' ' +lastName;
        this.title = title;
        this.payRate = payRate;
        this.paidTimeOff = paidTimeOff;
        this.pwHash = encoder.encode(oneTimePassword);
        this.contactInfo = new Contact(firstName, lastName);
    }

    public Employee (String username, String password) {
        this.username = username;
        this.pwHash = encoder.encode(password);
    }

    public Employee () {}


    //  GETTERS & SETTERS


    public Integer getId() {
        return id;
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

    public String getFirstNameLastNameCombo() {
        return firstNameLastNameCombo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
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

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getSupervisorAccess() {
        return supervisorAccess;
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

    public void setFirstDateOfWork(GregorianCalendar firstDateOfWork) {
        this.firstDateOfWork = firstDateOfWork;
    }

    public void setPwHash(String password){
        this.pwHash = encoder.encode(password);
    }

    public Integer getTotalApprovedHoursWorkedToDate(){
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

    public void resetTotalApprovedHoursWorkedToDate(){
        List<Timesheet> timesheets = this.timesheets;
        Integer newTotalHoursWorkedToDate = 0;
        for (Timesheet timesheet:
                timesheets) {
            if(timesheet.getSupervisorApproval()){
                newTotalHoursWorkedToDate += timesheet.getTotalHours();
            }
        }
        this.totalHoursWorkedToDate = newTotalHoursWorkedToDate;
    }



    public Contact getContactInfo() {
        return contactInfo;
    }

    public String getContactInfoAddressLineOne() {
        if(contactInfo == null || contactInfo.getAddressLineOne() == null){
            return " ";
        }
        return contactInfo.getAddressLineOne();
    }

    public String getContactInfoCity() {
        if(contactInfo == null || contactInfo.getCity() == null){
            return " ";
        }
        return contactInfo.getCity();
    }

    public String getContactInfoState() {
        if(contactInfo == null || contactInfo.getState() == null){
            return " ";
        }
        return contactInfo.getState();
    }

    public String getContactInfoZipcode() {
        if(contactInfo == null || contactInfo.getZipcode() == null){
            return " ";
        }
        return contactInfo.getZipcode();
    }
    public List<String> getContactInfoPhoneNumbers() {
        ArrayList<String> phoneNumbers = new ArrayList<>();
        if(contactInfo == null || contactInfo.getPhoneNumbers() == null){
            return phoneNumbers;
        }
        for (PhoneNumber number:
             contactInfo.getPhoneNumbers()) {
            phoneNumbers.add(number.toString());
        }
        return phoneNumbers;
    }

    public List<String> getContactInfoEmails() {
        ArrayList<String> emails = new ArrayList<>();
        if(contactInfo == null || contactInfo.getEmail() == null){
            return emails;
        }
        return contactInfo.getEmail();
    }

    public void setContactInfo(Contact contactInfo) {
        this.contactInfo = contactInfo;
    }

    public EmergencyContact getEmergencyContact() {
        return emergencyContact;
    }

    public String getEmergencyContactFirstName(){
        if(emergencyContact == null){
            return " ";
        }
        return emergencyContact.getFirstName();
    }

    public String getEmergencyContactLastName(){
        if(emergencyContact == null){
            return " ";
        }
        return emergencyContact.getLastName();
    }

    public String getEmergencyContactPhoneNumbers(){
        if(emergencyContact == null){
            return " ";
        }
        List<PhoneNumber> phoneNumbers = emergencyContact.getPhoneNumbers();
        PhoneNumber phoneNumber = phoneNumbers.get(0);
        return phoneNumber.toString();
    }

    public String getEmergencyContactRelationship(){
        if(emergencyContact == null){
            return " ";
        }
        return emergencyContact.getRelationship();
    }

    public void setEmergencyContact(EmergencyContact emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public boolean isEmergencyContactNotNull(){
        return this.emergencyContact != null;
    }

    public void setEmergencyContactUpdates(EditContactDetailsDTO editedContactDetails){
        if(!editedContactDetails.getEcFirstName().equals("")){
            this.emergencyContact.setFirstName(editedContactDetails.getEcFirstName());
        }
        if(!editedContactDetails.getEcLastName().equals("")){
            this.emergencyContact.setLastName(editedContactDetails.getEcLastName());
        }
        if(!editedContactDetails.getEcPhoneNumber().equals("")){
            PhoneNumber ecPhone = new PhoneNumber(editedContactDetails.getEcPhoneNumber());
            ArrayList<PhoneNumber> phoneNumbers = new ArrayList<>();
            phoneNumbers.add(ecPhone);
            this.emergencyContact.setPhoneNumbers(phoneNumbers);
        }
        if(!editedContactDetails.getEcRelationship().equals("")){
            this.emergencyContact.setRelationship(editedContactDetails.getEcRelationship());
        }
    }
    public void setContactInfoUpdates(EditContactDetailsDTO editedContactDetails){
        if(!editedContactDetails.getAddressLineOne().equals("")){
            this.contactInfo.setAddressLineOne(editedContactDetails.getAddressLineOne());
        }
        if(!editedContactDetails.getCity().equals("")){
            this.emergencyContact.setCity(editedContactDetails.getCity());
        }
        if(!editedContactDetails.getState().equals("")){
            this.emergencyContact.setState(editedContactDetails.getState());
        }
        if(!editedContactDetails.getZipcode().equals("")){
            this.emergencyContact.setZipcode(editedContactDetails.getZipcode());
        }
        if(!editedContactDetails.getEmail1().equals("")){
            this.contactInfo.getEmail().add(editedContactDetails.getEmail1());
        }
        if(!editedContactDetails.getEmail2().equals("")){
            this.contactInfo.getEmail().add(editedContactDetails.getEmail2());
        }
        if(!editedContactDetails.getPhoneNumber1().equals("")){
            //TODO - we could end up with repeated phone numbers in the database with this? - autowire repo and
            // query database before instantiating a new phone number? (see if already exists,)
            PhoneNumber phone = new PhoneNumber(editedContactDetails.getPhoneNumber1());
            this.contactInfo.getPhoneNumbers().add(phone);
        }
        if(!editedContactDetails.getPhoneNumber2().equals("")){
            PhoneNumber phone = new PhoneNumber(editedContactDetails.getPhoneNumber2());
            this.contactInfo.getPhoneNumbers().add(phone);
        }

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
        return id.equals(employee.id);
    }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }

}
