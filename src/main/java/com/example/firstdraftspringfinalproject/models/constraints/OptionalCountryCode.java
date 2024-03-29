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
@Constraint(validatedBy = OptionalCountryCodeValidator.class)
@Documented
public @interface OptionalCountryCode {

    String message() default "Country Code if left blank will default to the United States (+1), " +
            "if present the Country code must adhere to the correct format - a plus sign, '+', and any numerical " +
            "digit one through nine, '1-9'";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default {};
}
