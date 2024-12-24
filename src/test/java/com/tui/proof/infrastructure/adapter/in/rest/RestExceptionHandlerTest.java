package com.tui.proof.infrastructure.adapter.in.rest;

import com.tui.proof.infrastructure.adapter.in.CommonExceptionHandler;
import com.tui.proof.infrastructure.adapter.in.dto.error.ErrorDetail;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.exceptions.base.MockitoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class RestExceptionHandlerTest {

    @Mock
    private CommonExceptionHandler commonExceptionHandler;

    @InjectMocks
    private RestExceptionHandler restExceptionHandler;

    @Mock
    private ResponseEntityExceptionHandler springHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testHandleExceptionWhenHandledByCommonExceptionHandler() throws Exception {
        // Arrange
        Exception ex = new Exception("Test exception");
        WebRequest request = mock(WebRequest.class);
        String instance = "testInstance";
        ErrorDetail errorDetail = ErrorDetail.builder()
                .title("Error")
                .status(HttpStatus.BAD_REQUEST)
                .detail("Detailed error message")
                .instance(instance)
                .build();

        when(commonExceptionHandler.handlerException(any(Exception.class), anyString())).thenReturn(errorDetail);

        // Act
        ResponseEntity<Object> responseEntity = restExceptionHandler.handlerException(ex, request);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_PROBLEM_JSON, responseEntity.getHeaders().getContentType());
        assertEquals(errorDetail, responseEntity.getBody());
    }

    @Test
    void testHandleExceptionWhenNotHandledByCommonExceptionHandler() throws Exception {
        // Arrange
        Exception ex = new Exception("Test exception");
        WebRequest request = mock(WebRequest.class);
        String instance = "testInstance";
        ErrorDetail errorDetail = ErrorDetail.builder()
                .title("Error")
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .detail("Internal error")
                .instance(instance)
                .build();

        when(commonExceptionHandler.handlerException(any(Exception.class), anyString())).thenReturn(errorDetail);

        // Simulate Spring's default response for the exception
        ResponseEntity<Object> springDefaultResponse = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        doCallRealMethod().when(springHandler).handleException(ex, request);

        // Act
        ResponseEntity<Object> responseEntity = restExceptionHandler.handlerException(ex, request);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_PROBLEM_JSON, responseEntity.getHeaders().getContentType());
    }

    @Test
    @Disabled
    void testInstanceFromWebRequest() {
        // Arrange
        WebRequest request = mock(WebRequest.class);
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        when(request.getContextPath()).thenReturn("/test");
        when(request.getDescription(true)).thenReturn("/orders");

        // Act
        String instance = restExceptionHandler.instanceFrom(request);

        // Assert
        assertNotNull(instance);
        assertEquals("/orders", instance);
    }

    @Test
    void testInstanceFromProblemDetail() {
        // Arrange
        ProblemDetail problemDetail = mock(ProblemDetail.class);
        URI uri = URI.create("/orders");
        when(problemDetail.getInstance()).thenReturn(uri);

        // Act
        String instance = restExceptionHandler.instanceFrom(problemDetail);

        // Assert
        assertEquals("/orders", instance);
    }
}
