package com.example.firstdraftspringfinalproject.models.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OptionalPhoneNumberExtensionValidator implements ConstraintValidator<OptionalPhoneNumberExtension, String> {


    @Override
    public void initialize(OptionalPhoneNumberExtension constraintAnnotation){

    }

    @Override
    public boolean isValid(String phoneNumberExtenstion, ConstraintValidatorContext constraintContext){
        //should make sure that
        return false;
    }
}
