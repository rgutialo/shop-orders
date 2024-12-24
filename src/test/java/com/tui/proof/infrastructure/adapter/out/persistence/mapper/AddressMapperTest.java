package com.tui.proof.infrastructure.adapter.out.persistence.mapper;

import com.tui.proof.domain.model.AddressModel;
import com.tui.proof.infrastructure.adapter.out.persistence.entity.AddressEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressMapperImplTest {

    private AddressMapperImpl addressMapper;

    @BeforeEach
    void setUp() {
        addressMapper = new AddressMapperImpl();
    }

    @Test
    void testModelToEntity() {
        // Arrange
        AddressModel addressModel = AddressModel.builder()
                .addressId(1)
                .street("123 Main St")
                .postcode("12345")
                .city("CityName")
                .country("CountryName")
                .build();

        // Act
        AddressEntity addressEntity = addressMapper.modelToEntity(addressModel);

        // Assert
        assertNotNull(addressEntity, "Entity should not be null");
        assertEquals(addressModel.getAddressId(), addressEntity.getAddressId(), "Address ID should match");
        assertEquals(addressModel.getStreet(), addressEntity.getStreet(), "Street should match");
        assertEquals(addressModel.getPostcode(), addressEntity.getPostcode(), "Postcode should match");
        assertEquals(addressModel.getCity(), addressEntity.getCity(), "City should match");
        assertEquals(addressModel.getCountry(), addressEntity.getCountry(), "Country should match");
    }

    @Test
    void testModelToEntityWithNull() {
        // Act
        AddressEntity addressEntity = addressMapper.modelToEntity(null);

        // Assert
        assertNull(addressEntity, "Entity should be null when input model is null");
    }

    @Test
    void testEntityToModel() {
        // Arrange
        AddressEntity addressEntity = AddressEntity.builder()
                .addressId(1)
                .street("123 Main St")
                .postcode("12345")
                .city("CityName")
                .country("CountryName")
                .build();

        // Act
        AddressModel addressModel = addressMapper.entityToModel(addressEntity);

        // Assert
        assertNotNull(addressModel, "Model should not be null");
        assertEquals(addressEntity.getAddressId(), addressModel.getAddressId(), "Address ID should match");
        assertEquals(addressEntity.getStreet(), addressModel.getStreet(), "Street should match");
        assertEquals(addressEntity.getPostcode(), addressModel.getPostcode(), "Postcode should match");
        assertEquals(addressEntity.getCity(), addressModel.getCity(), "City should match");
        assertEquals(addressEntity.getCountry(), addressModel.getCountry(), "Country should match");
    }

    @Test
    void testEntityToModelWithNull() {
        // Act
        AddressModel addressModel = addressMapper.entityToModel(null);

        // Assert
        assertNull(addressModel, "Model should be null when input entity is null");
    }
}
