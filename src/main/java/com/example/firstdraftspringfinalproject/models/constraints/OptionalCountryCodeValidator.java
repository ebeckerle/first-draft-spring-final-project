package com.example.firstdraftspringfinalproject.models.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OptionalCountryCodeValidator implements ConstraintValidator<OptionalCountryCode, String> {

    @Override
    public void initialize(OptionalCountryCode constraintAnnotation){

    }

    @Override
    public boolean isValid(String countryCode, ConstraintValidatorContext constraintContext){
        //should make sure that
        return false;
    }
}
