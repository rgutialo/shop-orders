package com.tui.proof.application.service;

import com.tui.proof.application.port.out.ClientReaderPort;
import com.tui.proof.application.port.out.ClientWriterPort;
import com.tui.proof.domain.model.ClientModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @Mock
    private ClientReaderPort readerPort;

    @Mock
    private ClientWriterPort writerPort;

    @InjectMocks
    private ClientService clientService;

    @Test
    void testCreateClient_NewClient() {
        // Given
        ClientModel clientModel = ClientModel.builder()
                .firstName("John")
                .lastName("Doe")
                .telephone("123456789")
                .build();

        // Mock behavior: no existing client found
        when(readerPort.findByFirstNameAndLastNameAndTelephone(clientModel)).thenReturn(Optional.empty());
        when(writerPort.save(clientModel)).thenReturn(clientModel);

        // When
        ClientModel result = clientService.createClient(clientModel);

        // Then
        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        assertEquals("123456789", result.getTelephone());
        verify(writerPort, times(1)).save(clientModel);
    }

    @Test
    void testCreateClient_ExistingClient() {
        // Given
        ClientModel clientModel = ClientModel.builder()
                .firstName("John")
                .lastName("Doe")
                .telephone("123456789")
                .build();

        // Mock behavior: client found
        when(readerPort.findByFirstNameAndLastNameAndTelephone(clientModel)).thenReturn(Optional.of(clientModel));

        // When
        ClientModel result = clientService.createClient(clientModel);

        // Then
        assertNotNull(result);
        assertEquals(clientModel, result);
        verify(writerPort, times(0)).save(clientModel); // Ensure save is not called since client exists
    }

    @Test
    void testSearchClient_FoundClient() {
        // Given
        ClientModel clientModel = ClientModel.builder()
                .firstName("John")
                .lastName("Doe")
                .telephone("123456789")
                .build();

        // Mock behavior: client found
        when(readerPort.findByFirstNameAndLastNameAndTelephone(clientModel)).thenReturn(Optional.of(clientModel));

        // When
        Optional<ClientModel> result = clientService.searchClient(clientModel);

        // Then
        assertTrue(result.isPresent());
        assertEquals(clientModel, result.get());
    }

    @Test
    void testSearchClient_ClientNotFound() {
        // Given
        ClientModel clientModel = ClientModel.builder()
                .firstName("John")
                .lastName("Doe")
                .telephone("123456789")
                .build();

        // Mock behavior: client not found
        when(readerPort.findByFirstNameAndLastNameAndTelephone(clientModel)).thenReturn(Optional.empty());

        // When
        Optional<ClientModel> result = clientService.searchClient(clientModel);

        // Then
        assertFalse(result.isPresent());
    }
}
