package com.example.firstdraftspringfinalproject.models.constraints;

//import org.hibernate.annotations.Target;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = OptionalZipcodeValidator.class)
@Documented
public @interface OptionalZipcode {

    String message() default "Zipcode is optional bu tmust be 5 digits";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default {};

}
