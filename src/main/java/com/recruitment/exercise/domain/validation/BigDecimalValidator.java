package com.recruitment.exercise.domain.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class BigDecimalValidator implements ConstraintValidator<ValidBigDecimal, BigDecimal> {

    @Override
    public boolean isValid(BigDecimal bigDecimal, ConstraintValidatorContext constraintValidatorContext) {
        if (bigDecimal == null) {
            return false;
        }

        if (bigDecimal.scale() > 2) {
            return false;
        }

        if (bigDecimal.compareTo(BigDecimal.ZERO) < 1) {
            return false;
        }

        return true;
    }
}
