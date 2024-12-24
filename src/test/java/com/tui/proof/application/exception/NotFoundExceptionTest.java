package com.tui.proof.application.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotFoundExceptionTest {

    @Test
    void testNotFoundExceptionMessage() {
        // Arrange
        String expectedMessage = "Order not found";

        // Act
        NotFoundException exception = new NotFoundException(expectedMessage);

        // Assert
        assertNotNull(exception);
        assertEquals(expectedMessage, exception.getMessage(), "The exception message should match the expected message");
    }
}
