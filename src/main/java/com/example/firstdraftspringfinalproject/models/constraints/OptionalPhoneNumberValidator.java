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
        return false;
    }
}
