package com.example.firstdraftspringfinalproject.models.constraints;

import javax.validation.Payload;

import java.lang.annotation.Target;

import javax.validation.Constraint;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = OptionalZipcodeValidator.class)
@Documented
public @interface OptionalState {

    String message() default "State must be left blank or be a valid two character postal code";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default {};
}
