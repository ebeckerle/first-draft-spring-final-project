package com.example.firstdraftspringfinalproject.TDDtests;

import com.example.firstdraftspringfinalproject.models.Contact;
import com.example.firstdraftspringfinalproject.models.dto.NewContactDTO;
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
        NewContactDTO contact = new Contact( "company name", null, "Testerina", "Test");

        Set<ConstraintViolation<Contact>> constraintViolations =
                validator.validate( contact );

        assertEquals( 1, constraintViolations.size() );
        assertEquals( "Contact Type is required.", constraintViolations.iterator().next().getMessage() );
    }
}
