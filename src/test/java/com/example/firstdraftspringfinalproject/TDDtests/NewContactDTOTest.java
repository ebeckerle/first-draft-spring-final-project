package com.example.firstdraftspringfinalproject.TDDtests;

import com.example.firstdraftspringfinalproject.models.dto.NewContactDTO;
import com.example.firstdraftspringfinalproject.models.enums.ContactType;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.testng.AssertJUnit.assertEquals;

public class NewContactDTOTest {


    //FOLLOWING - TESTING HIBERNATE ANNOTATIONS

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void contactTypeIsNull() {
        //for some reason it seems like the @BeforeClass annotation is not working... so therefore I have setup(); run here:
        setUp();
        NewContactDTO contact = new NewContactDTO(null, "company name");

        Set<ConstraintViolation<NewContactDTO>> constraintViolations =
                validator.validate( contact );

        assertEquals( 1, constraintViolations.size() );
        assertEquals( "Contact Type is required.", constraintViolations.iterator().next().getMessage() );
    }

    @Test
    public void firstNameIsTooLong() {
        //for some reason it seems like the @BeforeClass annotation is not working... so therefore I have setup(); run here:
        setUp();
        NewContactDTO contact = new NewContactDTO(ContactType.GENERAL, "this first name must be more than 40 characters",
                "LastName", "company name", "Address Line One", "City", "MO", "12345");

        Set<ConstraintViolation<NewContactDTO>> constraintViolations =
                validator.validate( contact );

        assertEquals( 1, constraintViolations.size() );
        assertEquals( "Must be under 40 characters", constraintViolations.iterator().next().getMessage() );
    }

    @Test
    public void lastNameIsTooLong() {
        //for some reason it seems like the @BeforeClass annotation is not working... so therefore I have setup(); run here:
        setUp();
        NewContactDTO contact = new NewContactDTO(ContactType.GENERAL, "First Name",
                "this last name must be more than 40 characters", "company name",
                "Address Line One", "City", "MO", "12345");

        Set<ConstraintViolation<NewContactDTO>> constraintViolations =
                validator.validate( contact );

        assertEquals( 1, constraintViolations.size() );
        assertEquals( "Must be under 40 characters", constraintViolations.iterator().next().getMessage() );
    }

    @Test
    public void companyNameIsBlank() {
        //for some reason it seems like the @BeforeClass annotation is not working... so therefore I have setup(); run here:
        setUp();
        NewContactDTO contact = new NewContactDTO(ContactType.GENERAL, "First Name",
                "Last Name", "", "Address Line One", "City", "MO", "12345");

        Set<ConstraintViolation<NewContactDTO>> constraintViolations =
                validator.validate( contact );

        assertEquals( 1, constraintViolations.size() );
        assertEquals( "Company Name is required.", constraintViolations.iterator().next().getMessage() );
    }

    @Test
    public void companyNameIsTooLong() {
        //for some reason it seems like the @BeforeClass annotation is not working... so therefore I have setup(); run here:
        setUp();
        NewContactDTO contact = new NewContactDTO(ContactType.GENERAL, "First Name",
                "Last Name",
                "This company name must be only 60 characters so don't make it too long with lots of characters",
                "Address Line One", "City", "MO", "12345");

        Set<ConstraintViolation<NewContactDTO>> constraintViolations =
                validator.validate( contact );

        assertEquals( 1, constraintViolations.size() );
        assertEquals( "Must be under 60 characters", constraintViolations.iterator().next().getMessage() );
    }

    @Test
    public void AddressLineOneIsTooLong() {
        //for some reason it seems like the @BeforeClass annotation is not working... so therefore I have setup(); run here:
        setUp();
        NewContactDTO contact = new NewContactDTO(ContactType.GENERAL, "First Name",
                "Last Name", "Company Name",
                "This Address Line ONe must be only eighty characters so don't make it too long with lots of characters; Address Line One",
                "City", "MO", "12345");

        Set<ConstraintViolation<NewContactDTO>> constraintViolations =
                validator.validate( contact );

        assertEquals( 1, constraintViolations.size() );
        assertEquals( "Must be under 80 characters", constraintViolations.iterator().next().getMessage() );
    }

    @Test
    public void CityIsTooLong() {
        //for some reason it seems like the @BeforeClass annotation is not working... so therefore I have setup(); run here:
        setUp();
        NewContactDTO contact = new NewContactDTO(ContactType.GENERAL, "First Name",
                "Last Name", "Company Name",
                "Address Line One",
                "This CIty must be only eighty characters so don't make it too long with lots of characters; Address Line One",
                "MO", "12345");

        Set<ConstraintViolation<NewContactDTO>> constraintViolations =
                validator.validate( contact );

        assertEquals( 1, constraintViolations.size() );
        assertEquals( "Must be under 60 characters", constraintViolations.iterator().next().getMessage() );
    }

    @Test
    public void StateIsTooLong() {
        //for some reason it seems like the @BeforeClass annotation is not working... so therefore I have setup(); run here:
        setUp();
        NewContactDTO contact = new NewContactDTO(ContactType.GENERAL, "First Name",
                "Last Name", "Company Name",
                "Address Line One",
                "City", "Missouri", "12345");

        Set<ConstraintViolation<NewContactDTO>> constraintViolations =
                validator.validate( contact );

        assertEquals( 1, constraintViolations.size() );
        assertEquals( "State must be left blank or be a valid two character postal code", constraintViolations.iterator().next().getMessage() );
    }

    @Test
    public void ZipCodeIsTooLong() {
        //for some reason it seems like the @BeforeClass annotation is not working... so therefore I have setup(); run here:
        setUp();
        NewContactDTO contact = new NewContactDTO(ContactType.GENERAL, "First Name",
                "Last Name", "Company Name",
                "Address Line One",
                "City", "MO", "123456");

        Set<ConstraintViolation<NewContactDTO>> constraintViolations =
                validator.validate( contact );

        assertEquals( 1, constraintViolations.size() );
        assertEquals( "Zipcode is optional but must be 5 digits", constraintViolations.iterator().next().getMessage() );
    }

    @Test
    public void emailNotInEmailFormat() {
        //for some reason it seems like the @BeforeClass annotation is not working... so therefore I have setup(); run here:
        setUp();
                NewContactDTO contact = new NewContactDTO(ContactType.GENERAL, "First Name",
                "Last Name", "Company Name",
                "Address Line One",
                "City", "MO", "12345", "email", "3143303181", "+1",
                "");

        Set<ConstraintViolation<NewContactDTO>> constraintViolations =
                validator.validate( contact );

        assertEquals( 1, constraintViolations.size() );
//        assertEquals( "ZipCode must be 5 characters", constraintViolations.iterator().next().getMessage() );
    }

    @Test
    public void optionalZipcodeCustomConstraint() {
        //for some reason it seems like the @BeforeClass annotation is not working... so therefore I have setup(); run here:
        setUp();
        NewContactDTO contact = new NewContactDTO(ContactType.GENERAL, "First Name",
                "Last Name", "Company Name",
                "Address Line One",
                "City", "MO", "123", "email@email.com",
                "3143303181", "", "");

        Set<ConstraintViolation<NewContactDTO>> constraintViolations =
                validator.validate( contact );

        assertEquals( 1, constraintViolations.size() );
        assertEquals( "Zipcode is optional but must be 5 digits", constraintViolations.iterator().next().getMessage() );
    }

    @Test
    public void optionalStateCustomConstraint() {
        //for some reason it seems like the @BeforeClass annotation is not working... so therefore I have setup(); run here:
        setUp();
        NewContactDTO contact = new NewContactDTO(ContactType.GENERAL, "First Name",
                "Last Name", "Company Name",
                "Address Line One",
                "City", "RY", "12345", "email@email.com", "3143303181", "", "");

        Set<ConstraintViolation<NewContactDTO>> constraintViolations =
                validator.validate( contact );

        assertEquals( 1, constraintViolations.size() );
        assertEquals( "State must be left blank or be a valid two character postal code", constraintViolations.iterator().next().getMessage() );
    }

    @Test
    public void optionalPhoneNumberCustomConstraint() {
        //for some reason it seems like the @BeforeClass annotation is not working... so therefore I have setup(); run here:
        setUp();
        NewContactDTO contact = new NewContactDTO(ContactType.GENERAL, "First Name",
                "Last Name", "Company Name",
                "Address Line One",
                "City", "MO", "12345", "email@email.com", "3w43303181", "", "");

        Set<ConstraintViolation<NewContactDTO>> constraintViolations =
                validator.validate( contact );

        assertEquals( 1, constraintViolations.size() );
        assertEquals( "Phone Number must be a valid 10 digits - a 3 digit area code and a 7 digit phone number", constraintViolations.iterator().next().getMessage() );
    }

    @Test
    public void optionalCountryCodeCustomConstraint() {
        //for some reason it seems like the @BeforeClass annotation is not working... so therefore I have setup(); run here:
        setUp();
        NewContactDTO contact = new NewContactDTO(ContactType.GENERAL, "First Name",
                "Last Name", "Company Name",
                "Address Line One",
                "City", "MO", "12345", "email@email.com", "3143303181",
                "+f678", "");

        Set<ConstraintViolation<NewContactDTO>> constraintViolations =
                validator.validate( contact );

        assertEquals( 1, constraintViolations.size() );
        assertEquals( "Country Code if left blank will default to the United States (+1), " +
                "if present the Country code must adhere to the correct format - a plus sign, '+', and any numerical " +
                "digit one through nine, '1-9'", constraintViolations.iterator().next().getMessage() );
    }

    @Test
    public void optionalPhoneNumberExtensionCustomConstraint() {
        //for some reason it seems like the @BeforeClass annotation is not working... so therefore I have setup(); run here:
        setUp();
        NewContactDTO contact = new NewContactDTO(ContactType.GENERAL, "First Name",
                "Last Name", "Company Name",
                "Address Line One",
                "City", "MO", "12345", "email@email.com", "3143303181",
                "+678", "srt5");

        Set<ConstraintViolation<NewContactDTO>> constraintViolations =
                validator.validate( contact );

        assertEquals( 1, constraintViolations.size() );
        assertEquals( "Phone Number Extension if present must be 3 numerical digits", constraintViolations.iterator().next().getMessage() );
    }

}
