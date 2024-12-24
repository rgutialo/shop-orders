package com.tui.proof.infrastructure.adapter.in.validations;

import jakarta.validation.Payload;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AllowedPilotesValidatorTest {

    private AllowedPilotesValidator validator;

    @BeforeEach
    void setUp() {
        validator = new AllowedPilotesValidator();
    }

    @Test
    void testIsValidWithNullInput() {
        // Act
        boolean isValid = validator.isValid(null, null);

        // Assert
        assertFalse(isValid, "Null value should not be valid");
    }

    @Test
    void testIsValidWithValidInput() {
        // Arrange
        AllowedPilotes allowedPilotesAnnotation = new AllowedPilotes() {
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return AllowedPilotes.class;
            }

            @Override
            public int[] value() {
                return new int[]{1, 2, 3};
            }

            @Override
            public String message() {
                return "";
            }

            @Override
            public Class<?>[] groups() {
                return new Class[0];
            }

            @Override
            public Class<? extends Payload>[] payload() {
                return new Class[0];
            }
        };

        validator.initialize(allowedPilotesAnnotation);

        // Act
        boolean isValid = validator.isValid(2, null);

        // Assert
        assertTrue(isValid, "Input value 2 should be valid");
    }

    @Test
    void testIsValidWithInvalidInput() {
        // Arrange
        AllowedPilotes allowedPilotesAnnotation = new AllowedPilotes() {
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return AllowedPilotes.class;
            }

            @Override
            public int[] value() {
                return new int[]{1, 2, 3};
            }

            @Override
            public String message() {
                return "";
            }

            @Override
            public Class<?>[] groups() {
                return new Class[0];
            }

            @Override
            public Class<? extends Payload>[] payload() {
                return new Class[0];
            }
        };

        validator.initialize(allowedPilotesAnnotation);

        // Act
        boolean isValid = validator.isValid(4, null);

        // Assert
        assertFalse(isValid, "Input value 4 should not be valid");
    }

    @Test
    void testIsValidWithEmptyAllowedValues() {
        // Arrange
        AllowedPilotes allowedPilotesAnnotation = new AllowedPilotes() {
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return AllowedPilotes.class;
            }

            @Override
            public int[] value() {
                return new int[]{};
            }

            @Override
            public String message() {
                return "";
            }

            @Override
            public Class<?>[] groups() {
                return new Class[0];
            }

            @Override
            public Class<? extends Payload>[] payload() {
                return new Class[0];
            }
        };

        validator.initialize(allowedPilotesAnnotation);

        // Act
        boolean isValid = validator.isValid(1, null);

        // Assert
        assertFalse(isValid, "Input value should not be valid when no allowed values are specified");
    }
}
