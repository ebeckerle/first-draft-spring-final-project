package com.example.firstdraftspringfinalproject.models.constraints;

import com.example.firstdraftspringfinalproject.models.interfaces.ContactConstants;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Map;

public class OptionalPhoneNumberValidator implements ConstraintValidator<OptionalPhoneNumber, String> {

    @Override
    public void initialize(OptionalPhoneNumber constraintAnnotation){

    }

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext constraintContext){
       //should make sure that
        if(phoneNumber == null || phoneNumber.equals("")){
            return true;
        }
        if(phoneNumber.length() == 10 && phoneNumber.matches("\\d+")){
            System.out.println("here using the matches with the regex");
            return true;
        }
        return false;
    }
}
