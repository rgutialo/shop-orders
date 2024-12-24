package com.tui.proof.application.service;

import com.tui.proof.application.port.out.PilotePriceReaderPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class PilotePriceServiceTest {

    @Mock
    private PilotePriceReaderPort pilotePriceReaderPort;

    @InjectMocks
    private PilotePriceService pilotePriceService;

    @BeforeEach
    void setUp() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPrice() {
        // Arrange: mock the method call on PilotePriceReaderPort
        when(pilotePriceReaderPort.getPrice()).thenReturn(100.0);

        // Act: call the method under test
        double result = pilotePriceService.price();

        // Assert: verify the behavior and assert the result
        assertEquals(100.0, result, "The price should be 100.0");
        verify(pilotePriceReaderPort, times(1)).getPrice();  // Verify that getPrice was called once
    }
}
