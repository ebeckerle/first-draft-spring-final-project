package com.example.firstdraftspringfinalproject.TDDtests;

import com.example.firstdraftspringfinalproject.models.Contact;
import com.example.firstdraftspringfinalproject.models.PhoneNumber;
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
                "City", "MO", "12345", "email@email.com", "3143303181", "", "");

        Set<ConstraintViolation<NewContactDTO>> constraintViolations =
                validator.validate( contact );

        assertEquals( 1, constraintViolations.size() );
        assertEquals( "State must be left blank or be a valid two character postal code", constraintViolations.iterator().next().getMessage() );
    }

}
