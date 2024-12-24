package com.tui.proof.application.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderUpdateExceptionTest {

    @Test
    void testOrderUpdateExceptionMessage() {
        // Arrange
        String expectedMessage = "Order cannot be updated after 5 minutes";

        // Act
        OrderUpdateException exception = new OrderUpdateException(expectedMessage);

        // Assert
        assertNotNull(exception);
        assertEquals(expectedMessage, exception.getMessage(), "The exception message should match the expected message");
    }
}
