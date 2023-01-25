package com.example.firstdraftspringfinalproject.models.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OptionalCountryCodeValidator implements ConstraintValidator<OptionalCountryCode, String> {

    @Override
    public void initialize(OptionalCountryCode constraintAnnotation){

    }

    @Override
    public boolean isValid(String countryCode, ConstraintValidatorContext constraintContext){
        //should make sure that the country code is a + sign followed by one to 3 numerical digits if present
        if(countryCode== null || countryCode.equals("")){
            return true;
        }
        if(countryCode.length()<=4 && countryCode.length()>=2 && countryCode.indexOf("+")==0){
            for (int i = 1; i < 4; i++) {
                char character = countryCode.charAt(i);
                String allowedCharacters = "0123456789";
                if(allowedCharacters.indexOf(character)>=0){
                    return true;
                }
            }

        }
        return false;
    }
}
