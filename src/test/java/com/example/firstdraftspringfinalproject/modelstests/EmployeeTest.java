package com.example.firstdraftspringfinalproject.modelstests;

import com.example.firstdraftspringfinalproject.models.domainentityclasses.*;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets.DaysOfWeekHoursSet;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets.LineEntry;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets.ProjectWorkTypeSet;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets.Timesheet;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeTest {

    //TODO: move all of these variables for the tests out of this method and some where DRYer???
    Project pIasc = new Project("IASC", "Iowa State Capitol");
    Project pNam = new Project("NAM", "Nelson Atkins Museum");
    WorkType wT101 = new WorkType(101, "Inventory");
    WorkType wT102 = new WorkType(102, "Cut and Process Rough Parts");

    Employee testEmployee = new Employee("Testey", "McTesterson", "The Testiest", 23, 80, "LgI6M");
    GregorianCalendar date1 = new GregorianCalendar(2022, Calendar.FEBRUARY, 6);
    GregorianCalendar date2 = new GregorianCalendar(2022, Calendar.MARCH, 7);
    GregorianCalendar date3 = new GregorianCalendar(2022, Calendar.NOVEMBER, 7);

    Timesheet testTimesheet1 = new Timesheet(testEmployee, date1);
    Timesheet testTimesheet2 = new Timesheet(testEmployee, date2);
    Timesheet testTimesheet3 = new Timesheet(testEmployee, date3);

    ProjectWorkTypeSet pWT1 = new ProjectWorkTypeSet(pIasc, wT101);
    ProjectWorkTypeSet pWT2 = new ProjectWorkTypeSet(pIasc, wT102);
    ProjectWorkTypeSet pWT3 = new ProjectWorkTypeSet(pNam, wT102);
    DaysOfWeekHoursSet dWkHr1 = new DaysOfWeekHoursSet(0, 0, 4,0,0,0);
    DaysOfWeekHoursSet dWkHr2 = new DaysOfWeekHoursSet(0,0,0,0,4,0);
    DaysOfWeekHoursSet dWkHr3 = new DaysOfWeekHoursSet(1,1,3,0,4,0);

    LineEntry lineEntry1 = new LineEntry(pWT1, dWkHr1, testTimesheet1);
    LineEntry lineEntry2 = new LineEntry(pWT1, dWkHr2, testTimesheet1);
    LineEntry lineEntry3 = new LineEntry(pWT2, dWkHr3, testTimesheet1);
    LineEntry lineEntry4 = new LineEntry(pWT3, dWkHr3, testTimesheet1);


    @Test
    public void testGetCurrentTimesheet(){

        testTimesheet1.setSupervisorApproval(true);
        testTimesheet1.setCompletionStatus(true);
        testTimesheet2.setCompletionStatus(true);
        testTimesheet3.setCompletionStatus(false);

        testEmployee.getTimesheets().add(testTimesheet1);
        testEmployee.getTimesheets().add(testTimesheet2);
        testEmployee.getTimesheets().add(testTimesheet3);

        assertEquals(testTimesheet3, testEmployee.getCurrentTimesheet());

        //    public Timesheet getCurrentTimesheet(){
//        Timesheet returnedTimesheet = null;
//        for (Timesheet timesheet:
//                this.timesheets) {
//            if (!timesheet.getCompletionStatus()){
//                returnedTimesheet = timesheet;
//            }
//        }
//        return returnedTimesheet;
//    }

    }



    @Test
    public void testGetTotalHoursWorkedToDate(){

        testTimesheet1.getLineEntries().add(lineEntry1);
        testTimesheet1.getLineEntries().add(lineEntry2);
        testTimesheet1.getLineEntries().add(lineEntry3);
        testTimesheet1.setTotalHours();
        testTimesheet1.setCompletionStatus(true);
        testTimesheet1.setSupervisorApproval(true);
        System.out.println(testTimesheet1.getTotalHours());

        testTimesheet2.getLineEntries().add(lineEntry4);
        testTimesheet2.setTotalHours();
        testTimesheet2.setCompletionStatus(true);
        testTimesheet2.setSupervisorApproval(true);

        testTimesheet3.setCompletionStatus(false);
        testTimesheet3.setSupervisorApproval(false);

        testEmployee.getTimesheets().add(testTimesheet1);
        testEmployee.getTimesheets().add(testTimesheet2);
        testEmployee.getTimesheets().add(testTimesheet3);

        assertEquals(26, testEmployee.getTotalHoursWorkedToDate());

        //    public Integer getTotalHoursWorkedToDate(){
//        List<Timesheet> timesheets = this.timesheets;
//        Integer totalHoursWorkedToDate = 0;
//        for (Timesheet timesheet:
//                timesheets) {
//            if(timesheet.getSupervisorApproval()){
//                totalHoursWorkedToDate += timesheet.getTotalHours();
//            }
//        }
//        return totalHoursWorkedToDate;
//    }

    }


    //TODO - write tests for the following methods:

//    public void setEmergencyContactUpdates(EditContactDetailsDTO editedContactDetails){
//        if(!editedContactDetails.getEcFirstName().equals("")){
//            this.emergencyContact.firstName = editedContactDetails.getEcFirstName();
//        }
//        if(!editedContactDetails.getEcLastName().equals("")){
//            this.emergencyContact.lastName = editedContactDetails.getEcLastName();
//        }
//        if(!editedContactDetails.getEcPhoneNumber().equals("")){
//            PhoneNumber ecPhone = new PhoneNumber(editedContactDetails.getEcPhoneNumber());
//            ArrayList<PhoneNumber> phoneNumbers = new ArrayList<>();
//            phoneNumbers.add(ecPhone);
//            this.emergencyContact.phoneNumbers = phoneNumbers;
//        }
//        if(!editedContactDetails.getEcRelationship().equals("")){
//            this.emergencyContact.relationship = editedContactDetails.getEcRelationship();
//        }
//    }
//    public void setContactInfoUpdates(EditContactDetailsDTO editedContactDetails){
//        if(!editedContactDetails.getAddressLineOne().equals("")){
//            this.contactInfo.setAddressLineOne(editedContactDetails.getAddressLineOne());
//        }
//        if(!editedContactDetails.getCity().equals("")){
//            this.emergencyContact.setCity(editedContactDetails.getCity());
//        }
//        if(!editedContactDetails.getState().equals("")){
//            this.emergencyContact.setState(editedContactDetails.getState());
//        }
//        if(!editedContactDetails.getZipcode().equals("")){
//            this.emergencyContact.setZipcode(editedContactDetails.getZipcode());
//        }
//        if(!editedContactDetails.getEmail1().equals("")){
//            this.contactInfo.getEmail().add(editedContactDetails.getEmail1());
//        }
//        if(!editedContactDetails.getEmail2().equals("")){
//            this.contactInfo.getEmail().add(editedContactDetails.getEmail2());
//        }
//        if(!editedContactDetails.getPhoneNumber1().equals("")){
//            // query database before instantiating a new phone number? (see if already exists,)
//            PhoneNumber phone = new PhoneNumber(editedContactDetails.getPhoneNumber1());
//            this.contactInfo.getPhoneNumbers().add(phone);
//        }
//        if(!editedContactDetails.getPhoneNumber2().equals("")){
//            PhoneNumber phone = new PhoneNumber(editedContactDetails.getPhoneNumber2());
//            this.contactInfo.getPhoneNumbers().add(phone);
//        }
//
//    }
//public String getContactInfoAddressLineOne() {
//    if(contactInfo == null || contactInfo.getAddressLineOne() == null){
//        return " ";
//    }
//    return contactInfo.getAddressLineOne();
//}
//
//    public String getContactInfoCity() {
//        if(contactInfo == null || contactInfo.getCity() == null){
//            return " ";
//        }
//        return contactInfo.getCity();
//    }
//
//    public String getContactInfoState() {
//        if(contactInfo == null || contactInfo.getState() == null){
//            return " ";
//        }
//        return contactInfo.getState();
//    }
//
//    public String getContactInfoZipcode() {
//        if(contactInfo == null || contactInfo.getZipcode() == null){
//            return " ";
//        }
//        return contactInfo.getZipcode();
//    }
//    public List<String> getContactInfoPhoneNumbers() {
//        ArrayList<String> phoneNumbers = new ArrayList<>();
//        if(contactInfo == null || contactInfo.getPhoneNumbers() == null){
//            return phoneNumbers;
//        }
//        for (PhoneNumber number:
//                contactInfo.getPhoneNumbers()) {
//            phoneNumbers.add(number.toString());
//        }
//        return phoneNumbers;
//    }
//
//    public List<String> getContactInfoEmails() {
//        ArrayList<String> emails = new ArrayList<>();
//        if(contactInfo == null || contactInfo.getEmail() == null){
//            return emails;
//        }
//        return contactInfo.getEmail();
//    }
//
//    public void setContactInfo(Contact contactInfo) {
//        this.contactInfo = contactInfo;
//    }
//
//    public String getEmergencyContactFirstName(){
//        if(emergencyContact == null){
//            return " ";
//        }
//        return emergencyContact.getFirstName();
//    }
//
//    public String getEmergencyContactLastName(){
//        if(emergencyContact == null){
//            return " ";
//        }
//        return emergencyContact.getLastName();
//    }
//
//    public String getEmergencyContactPhoneNumbers(){
//        if(emergencyContact == null){
//            return " ";
//        }
//        List<PhoneNumber> phoneNumbers = emergencyContact.getPhoneNumbers();
//        PhoneNumber phoneNumber = phoneNumbers.get(0);
//        return phoneNumber.toString();
//    }
//
//    public String getEmergencyContactRelationship(){
//        if(emergencyContact == null){
//            return " ";
//        }
//        return emergencyContact.getRelationship();
//    }
//
//    public boolean isEmergencyContactNotNull(){
//        if(this.emergencyContact != null){
//            return true;
//        }
//        return false;
//    }

}
