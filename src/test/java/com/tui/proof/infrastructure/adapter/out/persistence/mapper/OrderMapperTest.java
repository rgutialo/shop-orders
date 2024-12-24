package com.tui.proof.infrastructure.adapter.out.persistence.mapper;

import com.tui.proof.domain.model.AddressModel;
import com.tui.proof.domain.model.ClientModel;
import com.tui.proof.domain.model.OrderModel;
import com.tui.proof.infrastructure.adapter.in.dto.v1.ClientRequest;
import com.tui.proof.infrastructure.adapter.in.dto.v1.DeliveryAddress;
import com.tui.proof.infrastructure.adapter.in.dto.v1.OrderRequest;
import com.tui.proof.infrastructure.adapter.in.dto.v1.OrderResponse;
import com.tui.proof.infrastructure.adapter.out.persistence.entity.AddressEntity;
import com.tui.proof.infrastructure.adapter.out.persistence.entity.ClientEntity;
import com.tui.proof.infrastructure.adapter.out.persistence.entity.OrderEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class OrderMapperImplTest {

    private OrderMapperImpl orderMapper;

    @BeforeEach
    void setUp() {
        orderMapper = new OrderMapperImpl();
    }

    @Test
    void testCreateModelFromRequest() {
        // Arrange
        DeliveryAddress deliveryAddress = new DeliveryAddress("Main St", "12345", "City", "Country");
        ClientRequest clientRequest = new ClientRequest();
        clientRequest.setFirstName("John");
        clientRequest.setLastName("Doe");
        clientRequest.setTelephone("123456789");
        OrderRequest orderRequest =OrderRequest.builder().number("123").deliveryAddress(deliveryAddress).client(clientRequest).pilotes(5).build();

        // Act
        OrderModel orderModel = orderMapper.createModelFromRequest(orderRequest);

        // Assert
        assertNotNull(orderModel, "OrderModel should not be null");
        assertEquals(orderRequest.getNumber(), orderModel.getNumber(), "Order number should match");
        assertEquals(orderRequest.getPilotes(), orderModel.getPilotes(), "Pilotes should match");
        assertEquals(orderRequest.getDeliveryAddress().street(), orderModel.getDeliveryAddress().getStreet(), "Street should match");
        assertEquals(orderRequest.getClient().getFirstName(), orderModel.getClient().getFirstName(), "First name should match");
    }

    @Test
    void testCreateResponseFromModel() {
        // Arrange
        AddressModel addressModel = AddressModel.builder().street("Main St").postcode("12345").city("City").country("Country").build();
        ClientModel clientModel = ClientModel.builder().firstName("John").lastName("Doe").telephone("123456789").build();
        OrderModel orderModel = OrderModel.builder().number("123").deliveryAddress(addressModel).client(clientModel).pilotes(5).orderTotal(50.0).build();

        // Act
        OrderResponse orderResponse = orderMapper.createResponseFromModel(orderModel);

        // Assert
        assertNotNull(orderResponse, "OrderResponse should not be null");
        assertEquals(orderModel.getNumber(), orderResponse.number(), "Order number should match");
        assertEquals(orderModel.getOrderTotal(), orderResponse.orderTotal(), "Order total should match");
        assertEquals(orderModel.getClient().getFirstName(), orderResponse.client().getFirstName(), "Client first name should match");
        assertEquals(orderModel.getDeliveryAddress().getStreet(), orderResponse.deliveryAddress().street(), "Delivery address street should match");
    }

    @Test
    void testModelToEntity() {
        // Arrange
        ClientModel clientModel = ClientModel.builder().clientId(1).firstName("John").lastName("Doe").telephone("123456789").build();
        OrderModel orderModel = OrderModel.builder().number("123").client(clientModel).pilotes(5).orderTotal(50.0).creationDate(LocalDateTime.now()).build();

        // Act
        OrderEntity orderEntity = orderMapper.modelToEntity(orderModel);

        // Assert
        assertNotNull(orderEntity, "OrderEntity should not be null");
        assertEquals(orderModel.getPilotes(), orderEntity.getPilotes(), "Pilotes should match");
        assertEquals(orderModel.getOrderTotal(), orderEntity.getOrderTotal(), "Order total should match");
        assertEquals(orderModel.getClient().getClientId(), orderEntity.getClient().getClientId(), "Client ID should match");
    }

    @Test
    void testEntityToModel() {
        // Arrange
        ClientEntity clientEntity = ClientEntity.builder().clientId(1).firstName("John").lastName("Doe").telephone("123456789").build();
        OrderEntity orderEntity = OrderEntity.builder().id(123).client(clientEntity).pilotes(5).orderTotal(50.0).creationDate(LocalDateTime.now()).build();

        // Act
        OrderModel orderModel = orderMapper.entityToModel(orderEntity);

        // Assert
        assertNotNull(orderModel, "OrderModel should not be null");
        assertEquals(String.valueOf(orderEntity.getId()), orderModel.getNumber(), "Order number should match");
        assertEquals(orderEntity.getOrderTotal(), orderModel.getOrderTotal(), "Order total should match");
        assertEquals(orderEntity.getClient().getClientId(), orderModel.getClient().getClientId(), "Client ID should match");
    }

    @Test
    void testNullHandling() {
        // Act & Assert
        assertNull(orderMapper.createModelFromRequest(null), "Model should be null when input request is null");
        assertNull(orderMapper.createResponseFromModel(null), "Response should be null when input model is null");
        assertNull(orderMapper.modelToEntity(null), "Entity should be null when input model is null");
        assertNull(orderMapper.entityToModel(null), "Model should be null when input entity is null");
    }
}
