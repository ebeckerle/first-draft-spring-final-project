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
@Constraint(validatedBy = OptionalPhoneNumberExtensionValidator.class)
@Documented
public @interface OptionalPhoneNumberExtension {

    String message() default "Phone Number Extension if present must be 3 numerical digits";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default {};
}
