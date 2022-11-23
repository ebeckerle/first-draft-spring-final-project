package com.example.firstdraftspringfinalproject.TDDtests;

import com.example.firstdraftspringfinalproject.models.Contact;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import static java.util.Objects.isNull;
import static org.junit.jupiter.api.Assertions.*;

public class ContactModelTest {


    private Contact testContactOne = new Contact("Testey",
            "McTesterson",
            "134 Arroyo Drive" ,
            "New York", "NY", "64110",
            "testing@test.com", "5555550000");

    private Contact testContactTwo = new Contact("Testerina", "Test");

    private Contact testContactThree = new Contact("Tester", "Testerson", "5551114444");



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
        assertTrue(isNull(testContactThree.getEmail()));
    }

    @Test
    public void testSetAnEmail(){

        testContactOne.setAnEmail("ilovetdd@test.com");
        assertFalse(isNull(testContactOne.getEmail()));
        assertTrue(testContactOne.getEmail().contains("ilovetdd@test.com"));

        testContactTwo.setAnEmail("ilovetesting@test.com");
        assertFalse(isNull(testContactTwo.getEmail()));
        assertEquals("ilovetesting@test.com", testContactTwo.getEmail().get(0));
    }
}
