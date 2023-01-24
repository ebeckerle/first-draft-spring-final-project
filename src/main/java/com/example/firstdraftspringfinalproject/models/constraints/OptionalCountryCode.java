package com.example.firstdraftspringfinalproject.models.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = OptionalZipcodeValidator.class)
@Documented
public @interface OptionalCountryCode {

    String message() default "Phone Number must be a valid 10 digits - a 3 digit area code and a 7 digit phone number";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default {};
}
