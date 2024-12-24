package com.tui.proof.infrastructure.adapter.out.persistence.mapper;

import com.tui.proof.domain.model.ClientModel;
import com.tui.proof.infrastructure.adapter.out.persistence.entity.ClientEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientMapperImplTest {

    private ClientMapperImpl clientMapper;

    @BeforeEach
    void setUp() {
        clientMapper = new ClientMapperImpl();
    }

    @Test
    void testModelToEntity() {
        // Arrange
        ClientModel clientModel = ClientModel.builder()
                .clientId(1)
                .firstName("John")
                .lastName("Doe")
                .telephone("123456789")
                .build();

        // Act
        ClientEntity clientEntity = clientMapper.modelToEntity(clientModel);

        // Assert
        assertNotNull(clientEntity, "Entity should not be null");
        assertEquals(clientModel.getClientId(), clientEntity.getClientId(), "Client ID should match");
        assertEquals(clientModel.getFirstName(), clientEntity.getFirstName(), "First name should match");
        assertEquals(clientModel.getLastName(), clientEntity.getLastName(), "Last name should match");
        assertEquals(clientModel.getTelephone(), clientEntity.getTelephone(), "Telephone should match");
    }

    @Test
    void testModelToEntityWithNull() {
        // Act
        ClientEntity clientEntity = clientMapper.modelToEntity(null);

        // Assert
        assertNull(clientEntity, "Entity should be null when input model is null");
    }

    @Test
    void testEntityToModel() {
        // Arrange
        ClientEntity clientEntity = ClientEntity.builder()
                .clientId(1)
                .firstName("John")
                .lastName("Doe")
                .telephone("123456789")
                .build();

        // Act
        ClientModel clientModel = clientMapper.entityToModel(clientEntity);

        // Assert
        assertNotNull(clientModel, "Model should not be null");
        assertEquals(clientEntity.getClientId(), clientModel.getClientId(), "Client ID should match");
        assertEquals(clientEntity.getFirstName(), clientModel.getFirstName(), "First name should match");
        assertEquals(clientEntity.getLastName(), clientModel.getLastName(), "Last name should match");
        assertEquals(clientEntity.getTelephone(), clientModel.getTelephone(), "Telephone should match");
    }

    @Test
    void testEntityToModelWithNull() {
        // Act
        ClientModel clientModel = clientMapper.entityToModel(null);

        // Assert
        assertNull(clientModel, "Model should be null when input entity is null");
    }
}
