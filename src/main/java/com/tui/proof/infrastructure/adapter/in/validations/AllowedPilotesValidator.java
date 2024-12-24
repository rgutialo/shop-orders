package com.tui.proof.infrastructure.adapter.in.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class AllowedPilotesValidator implements ConstraintValidator<AllowedPilotes, Integer> {
    private int[] allowedValues;

    @Override
    public void initialize(AllowedPilotes constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        allowedValues = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(
            Integer inputValue, ConstraintValidatorContext constraintValidatorContext) {
        if (inputValue == null) return false;
        return Arrays.stream(allowedValues).anyMatch(inputValue::equals);
    }
}
