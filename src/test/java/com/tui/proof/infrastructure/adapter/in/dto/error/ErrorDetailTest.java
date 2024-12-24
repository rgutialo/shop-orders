package com.tui.proof.infrastructure.adapter.in.dto.error;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

class ErrorDetailTest {

    @Test
    void testGetTitleWithNonNullTitle() {
        // Arrange
        String expectedTitle = "Custom Title";
        ErrorDetail errorDetail = ErrorDetail.builder()
                .title(expectedTitle)
                .status(HttpStatus.BAD_REQUEST)
                .build();

        // Act
        String title = errorDetail.getTitle();

        // Assert
        assertEquals(expectedTitle, title, "The title should be the non-null custom title");
    }

    @Test
    void testGetTitleWithNullTitle() {
        // Arrange
        ErrorDetail errorDetail = ErrorDetail.builder()
                .status(HttpStatus.BAD_REQUEST)
                .build();

        // Act
        String title = errorDetail.getTitle();

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST.getReasonPhrase(), title, "The title should match the reason phrase of the status");
    }

    @Test
    void testGetTitleWithNullTitleAndNoStatus() {
        // Arrange
        ErrorDetail errorDetail = ErrorDetail.builder()
                .status(null)
                .build();

        // Act
        String title = errorDetail.getTitle();

        // Assert
        assertNull(title, "The title should be null when both title and status are null");
    }

    @Test
    void testBuilderPattern() {
        // Arrange
        ErrorDetail errorDetail = ErrorDetail.builder()
                .title("Test Title")
                .status(HttpStatus.NOT_FOUND)
                .detail("Detail message")
                .instance("instanceId")
                .build();

        // Act & Assert
        assertNotNull(errorDetail);
        assertEquals("Test Title", errorDetail.getTitle());
        assertEquals(HttpStatus.NOT_FOUND, errorDetail.getStatus());
        assertEquals("Detail message", errorDetail.getDetail());
        assertEquals("instanceId", errorDetail.getInstance());
    }
}
