package com.example.firstdraftspringfinalproject.models.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OptionalPhoneNumberExtensionValidator implements ConstraintValidator<OptionalPhoneNumberExtension, String> {


    @Override
    public void initialize(OptionalPhoneNumberExtension constraintAnnotation){

    }

    @Override
    public boolean isValid(String phoneNumberExtension, ConstraintValidatorContext constraintContext){
        //should make sure that
        if(phoneNumberExtension== null || phoneNumberExtension.equals("")){
            return true;
        }
        return false;
    }
}
