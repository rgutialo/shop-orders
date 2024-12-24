package com.tui.proof.application.service;

import com.tui.proof.application.port.out.AddressReaderPort;
import com.tui.proof.application.port.out.AddressWriterPort;
import com.tui.proof.domain.model.AddressModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {

    @Mock
    private AddressReaderPort readerPort;

    @Mock
    private AddressWriterPort writerPort;

    @InjectMocks
    private AddressService addressService;

    @Test
    void testCreateAddress_NewAddress() {
        // Given
        AddressModel addressModel = AddressModel.builder()
                .street("Street 1")
                .postcode("12345")
                .city("City")
                .country("Country")
                .build();

        // Mock behavior: no existing address found
        when(readerPort.findByStreetAndPostcodeAndCityAndCountry(addressModel)).thenReturn(Optional.empty());
        when(writerPort.save(addressModel)).thenReturn(addressModel);

        // When
        AddressModel result = addressService.createAddress(addressModel);

        // Then
        assertNotNull(result);
        assertEquals("Street 1", result.getStreet());
        assertEquals("12345", result.getPostcode());
        assertEquals("City", result.getCity());
        assertEquals("Country", result.getCountry());
        verify(writerPort, times(1)).save(addressModel);
    }

    @Test
    void testCreateAddress_ExistingAddress() {
        // Given
        AddressModel addressModel = AddressModel.builder()
                .street("Street 1")
                .postcode("12345")
                .city("City")
                .country("Country")
                .build();

        // Mock behavior: address found
        when(readerPort.findByStreetAndPostcodeAndCityAndCountry(addressModel)).thenReturn(Optional.of(addressModel));

        // When
        AddressModel result = addressService.createAddress(addressModel);

        // Then
        assertNotNull(result);
        assertEquals(addressModel, result);
        verify(writerPort, times(0)).save(addressModel); // Ensure save is not called since address exists
    }

    @Test
    void testSearchAddress_FoundAddress() {
        // Given
        AddressModel addressModel = AddressModel.builder()
                .street("Street 1")
                .postcode("12345")
                .city("City")
                .country("Country")
                .build();

        // Mock behavior: address found
        when(readerPort.findByStreetAndPostcodeAndCityAndCountry(addressModel)).thenReturn(Optional.of(addressModel));

        // When
        Optional<AddressModel> result = addressService.searchAddress(addressModel);

        // Then
        assertTrue(result.isPresent());
        assertEquals(addressModel, result.get());
    }

    @Test
    void testSearchAddress_AddressNotFound() {
        // Given
        AddressModel addressModel = AddressModel.builder()
                .street("Street 1")
                .postcode("12345")
                .city("City")
                .country("Country")
                .build();

        // Mock behavior: address not found
        when(readerPort.findByStreetAndPostcodeAndCityAndCountry(addressModel)).thenReturn(Optional.empty());

        // When
        Optional<AddressModel> result = addressService.searchAddress(addressModel);

        // Then
        assertFalse(result.isPresent());
    }
}
