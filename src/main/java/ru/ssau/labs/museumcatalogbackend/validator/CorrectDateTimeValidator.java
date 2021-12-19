package ru.ssau.labs.museumcatalogbackend.validator;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class CorrectDateTimeValidator implements ConstraintValidator<CorrectDateTime, Object> {

    private String dateBefore;
    private String dateAfter;

    @Override
    public void initialize(CorrectDateTime constraintAnnotation) {
        this.dateBefore = constraintAnnotation.dateBefore();
        this.dateAfter = constraintAnnotation.dateAfter();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {

        Object before = new BeanWrapperImpl(value).getPropertyValue(dateBefore);
        Object after = new BeanWrapperImpl(value).getPropertyValue(dateAfter);
        if (before instanceof LocalDateTime && after instanceof LocalDateTime) {
            return isValidDate((LocalDateTime) before, (LocalDateTime) after);
        }
        if (before instanceof LocalDate && after instanceof LocalDate) {
            return isValidDate((LocalDate) before, (LocalDate) after);
        }
        return false;
    }

    private boolean isValidDate(LocalDateTime localDateTimeAfter, LocalDateTime localDateTimeBefore) {
        if (localDateTimeBefore != null) {
            return localDateTimeAfter == null || localDateTimeBefore.isBefore(localDateTimeAfter);
        } else {
            return localDateTimeAfter == null;
        }
    }

    private boolean isValidDate(LocalDate localDateAfter, LocalDate localDateBefore) {
        if (localDateBefore != null) {
            return localDateAfter == null || localDateBefore.isBefore(localDateAfter);
        } else {
            return localDateAfter == null;
        }
    }
}