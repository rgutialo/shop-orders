package com.tui.proof.infrastructure.adapter.in.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AllowedPilotesValidator.class)
public @interface AllowedPilotes {
    int[] value();

    String message() default "Pilotes must be one of {value}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
