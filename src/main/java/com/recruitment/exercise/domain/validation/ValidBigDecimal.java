package com.recruitment.exercise.domain.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Retention;

@Constraint(validatedBy = {BigDecimalValidator.class})
@Target({METHOD, FIELD})
@Retention(RUNTIME)
public @interface ValidBigDecimal {

    String message() default "Invalid BigDecimal value";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
