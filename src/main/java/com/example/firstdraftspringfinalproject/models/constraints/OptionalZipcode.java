package com.example.firstdraftspringfinalproject.models.constraints;

public @interface OptionalZipcode {

    String message() default "{com.example.firstdraftspringfinalproject.models.constraints.OptionalZipcode." +
            "message}";

    Class<?>[] groups() default { };

}
