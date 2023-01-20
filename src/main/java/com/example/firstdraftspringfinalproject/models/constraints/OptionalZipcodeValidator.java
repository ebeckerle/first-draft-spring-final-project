package com.example.firstdraftspringfinalproject.models.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OptionalZipcodeValidator implements ConstraintValidator<OptionalZipcode, String> {

    @Override
    public void initialize(OptionalZipcode constraintAnnotation){

    }

    @Override
    public boolean isValid(String zipcode, ConstraintValidatorContext constraintContext){
        return zipcode == null || zipcode.equals("") || zipcode.length() == 5;
    }
}
