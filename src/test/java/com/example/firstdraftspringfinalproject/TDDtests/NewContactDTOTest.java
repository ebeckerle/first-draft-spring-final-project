package com.example.firstdraftspringfinalproject.TDDtests;

import com.example.firstdraftspringfinalproject.models.Contact;
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
                "LastName", "company name", "Address Line One", "City", "ST", "12345");

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
                "this last name must be more than 40 characters", "company name", "Address Line One", "City", "ST", "12345");

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
                "Last Name", "", "Address Line One", "City", "ST", "12345");

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
                "Last Name", "", "Address Line One", "City", "ST", "12345");

        Set<ConstraintViolation<NewContactDTO>> constraintViolations =
                validator.validate( contact );

        assertEquals( 1, constraintViolations.size() );
        assertEquals( "Company Name is required.", constraintViolations.iterator().next().getMessage() );
    }

}
