package ru.ssau.labs.museumcatalogbackend.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CorrectDateTimeValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface CorrectDateTime {

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String message() default "The date before must be less than the date after!";

    String dateBefore();

    String dateAfter();

    @Target({ ElementType.TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        CorrectDateTime[] value();
    }
}