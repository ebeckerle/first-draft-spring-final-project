package com.example.firstdraftspringfinalproject.models.constraints;

import com.example.firstdraftspringfinalproject.models.interfaces.ContactConstants;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Map;

public class OptionalStateValidator implements ConstraintValidator<OptionalState, String> {

    @Override
    public void initialize(OptionalState constraintAnnotation){

    }

    @Override
    public boolean isValid(String state, ConstraintValidatorContext constraintContext){
        if(state == null || state.equals("")){
            return true;
        }
        for (Map.Entry<String, String> stateCode :
                ContactConstants.populateAllStatesHashMap().entrySet()) {
            if(state.equals(stateCode.getKey())){
                return true;
            }
        }
        return false;
    }
}
