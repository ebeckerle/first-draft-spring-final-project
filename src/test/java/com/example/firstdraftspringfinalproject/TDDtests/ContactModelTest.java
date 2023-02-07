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
import java.util.ArrayList;
import java.util.Set;

import static java.util.Objects.isNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;

public class ContactModelTest {

    private Contact testContactOne = new Contact(ContactType.GENERAL,
            "Testey",
            "McTesterson",
            "Test Co.",
            "134 Arroyo Drive" ,
            "New York", "NY", "64110",
            "testing@test.com", "5555550000");

    private Contact testContactTwo = new Contact(ContactType.GENERAL, "Testerina", "Test");

    private Contact testContactThree = new Contact(ContactType.GENERAL, "Tester", "Testerson", "5551114444");

    @Test
    public void testConstructorOne(){

        assertFalse(isNull(testContactOne));

        assertEquals("555", testContactOne.getPhoneNumbers().get(0).getAreaCode());
        assertEquals("555", testContactOne.getPhoneNumbers().get(0).getExchangeCode());
        assertEquals("0000", testContactOne.getPhoneNumbers().get(0).getLineNumber());

        assertFalse(testContactOne.getEmail().isEmpty());

    }

    @Test
    public void testConstructorTwo(){

        assertFalse(isNull(testContactTwo));
        assertEquals("Testerina", testContactTwo.getFirstName());
        assertEquals("Test", testContactTwo.getLastName());
        assertTrue(isNull(testContactTwo.getAddressLineOne()));

    }

    @Test
    public void testConstructorThree(){

        assertFalse(isNull(testContactThree));
        assertEquals("555", testContactThree.getPhoneNumbers().get(0).getAreaCode());
        assertEquals("111", testContactThree.getPhoneNumbers().get(0).getExchangeCode());
        assertEquals("4444", testContactThree.getPhoneNumbers().get(0).getLineNumber());
        assertTrue(testContactThree.getEmail().isEmpty());
    }

    @Test
    public void testConstructorFour(){

        NewContactDTO contactDTO = new NewContactDTO(ContactType.GENERAL, "Fox's Freight");
        Contact contact = new Contact(contactDTO);

        assertEquals(contact.getCompanyName(), "Fox's Freight");

        NewContactDTO contactDTO2 = new NewContactDTO(ContactType.COLLEAGUE,  "First Name",
                "Last Name", "Company Name",
                "Address Line One",
                "City", "MO", "12345", "email@email.com", "3143303181",
                null, "");
        Contact contact2 = new Contact(contactDTO2);
        PhoneNumber phoneNumber = new PhoneNumber("3143303181");

        assertEquals(contact2.getPhoneNumbers().get(0).getCountryCode(),"+1");
        assertEquals(contact2.getPhoneNumbers().get(0).getExtension(), null);
        assertEquals(contact2.getPhoneNumbers().get(0).getAreaCode(), "314");

        NewContactDTO contactDTO3 = new NewContactDTO(ContactType.COLLEAGUE,  "",
                "Last Name", "Company Name",
                "",
                "City", "MO", "12345", "email@email.com", "3143303181",
                null, "");
        Contact contact3 = new Contact(contactDTO3);

        assertEquals(contact3.getFirstName(), null);


    }

    @Test
    public void testSetAnEmail(){

        testContactOne.setAnEmail("ilovetdd@test.com");
        assertFalse(isNull(testContactOne.getEmail()));
        assertTrue(testContactOne.getEmail().contains("ilovetdd@test.com"));

        testContactTwo.setAnEmail("ilovetesting@test.com");
        assertFalse(isNull(testContactTwo.getEmail()));
        assertEquals("ilovetesting@test.com", testContactTwo.getEmail().get(0));

        testContactOne.setAnEmail("ilovetddtestcom");
        assertFalse(testContactOne.getEmail().contains("ilovetddtestcom"));

    }


    @Test
    public void testSetAPhoneNumber(){

        PhoneNumber phoneNumber = new PhoneNumber("3141234567");
        testContactTwo.setAPhoneNumber(phoneNumber);

        assertEquals(testContactTwo.getPhoneNumbers().get(0).getAreaCode(), "314");

    }

//    public void setAPhoneNumber(PhoneNumber phoneNumber){
//        if(this.phoneNumbers == null){
//            this.phoneNumbers = new ArrayList<>();
//            this.phoneNumbers.add(phoneNumber);
//        }else{
//            this.phoneNumbers.add(phoneNumber);
//        }
//    }

}
