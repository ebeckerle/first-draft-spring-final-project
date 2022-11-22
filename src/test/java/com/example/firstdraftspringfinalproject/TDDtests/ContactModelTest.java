package com.example.firstdraftspringfinalproject.TDDtests;

import com.example.firstdraftspringfinalproject.models.Contact;
import org.junit.jupiter.api.Test;

import static java.util.Objects.isNull;
import static org.junit.jupiter.api.Assertions.*;

public class ContactModelTest {

    // TODO next! 11/21
    private Contact testContactOne = new Contact("Testey",
            "McTesterson",
            "134 Arroyo Drive" ,
            "New York", "NY", "64110",
            "testing@test.com", "5555550000");

    private Contact testContactTwo = new Contact("Testerina", "Test");



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
        assertEquals("101", "102");
    }

    @Test
    public void testConstructorThree(){
        assertEquals("101", "102");
    }

    @Test
    public void test(){
        assertEquals("101", "102");
    }
}
