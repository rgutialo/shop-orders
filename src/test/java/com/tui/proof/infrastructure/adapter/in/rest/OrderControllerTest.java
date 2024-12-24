package com.tui.proof.infrastructure.adapter.in.rest;

import com.tui.proof.application.port.in.OrderPort;
import com.tui.proof.domain.model.OrderModel;
import com.tui.proof.infrastructure.adapter.in.dto.v1.ClientRequest;
import com.tui.proof.infrastructure.adapter.in.dto.v1.DeliveryAddress;
import com.tui.proof.infrastructure.adapter.in.dto.v1.OrderRequest;
import com.tui.proof.infrastructure.adapter.in.dto.v1.OrderResponse;
import com.tui.proof.infrastructure.adapter.out.persistence.mapper.OrderMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class OrderControllerTest {

    @Mock
    private OrderPort orderPort;

    @Mock
    private OrderMapper orderMapper;

    @InjectMocks
    private OrderController orderController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @Test
    @Disabled
    void testCreateOrder() throws Exception {
        // Arrange
        OrderRequest orderRequest = OrderRequest.builder().build();
        ClientRequest clientRequest = new ClientRequest();
        DeliveryAddress deliveryAddress = new DeliveryAddress("A","B","C","D");
        OrderModel orderModel = OrderModel.builder().build();
        OrderModel createdOrder = OrderModel.builder().build();
        OrderResponse orderResponse = new OrderResponse("1", clientRequest, deliveryAddress, 1, 1.33);

        when(orderMapper.createModelFromRequest(orderRequest)).thenReturn(orderModel);
        when(orderPort.createOrder(orderModel)).thenReturn(createdOrder);
        when(orderMapper.createResponseFromModel(createdOrder)).thenReturn(orderResponse);

        // Act & Assert
        mockMvc.perform(post("/orders")
                        .contentType("application/json")
                        .content("{\"someField\":\"someValue\"}"))  // Adjust with real request body structure
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.someField").value("someValue")); // Adjust based on actual response
    }

    @Test
    @Disabled
    void testUpdateOrder() throws Exception {
        // Arrange
        OrderRequest orderRequest = OrderRequest.builder().build();
        ClientRequest clientRequest = new ClientRequest();
        DeliveryAddress deliveryAddress = new DeliveryAddress("A","B","C","D");
        OrderModel orderModel = OrderModel.builder().build();
        OrderModel updatedOrder = OrderModel.builder().build();
        OrderResponse orderResponse = new OrderResponse("1", clientRequest, deliveryAddress, 1, 1.33);

        when(orderMapper.createModelFromRequest(orderRequest)).thenReturn(orderModel);
        when(orderPort.updateOrder(orderModel)).thenReturn(updatedOrder);
        when(orderMapper.createResponseFromModel(updatedOrder)).thenReturn(orderResponse);

        // Act & Assert
        mockMvc.perform(put("/orders")
                        .contentType("application/json")
                        .content("{\"someField\":\"someValue\"}"))  // Adjust with real request body structure
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.someField").value("someValue")); // Adjust based on actual response
    }

    @Test
    @Disabled
    void testSearchOrdersByClientData() throws Exception {
        // Arrange
        String clientContains = "clientData";
        OrderModel orderModel = OrderModel.builder().build();
        ClientRequest clientRequest = new ClientRequest();
        DeliveryAddress deliveryAddress = new DeliveryAddress("A","B","C","D");
        OrderResponse orderResponse = new OrderResponse("1", clientRequest, deliveryAddress, 1, 1.33);
        List<OrderModel> orderModels = Collections.singletonList(orderModel);
        List<OrderResponse> orderResponses = Collections.singletonList(orderResponse);

        when(orderPort.searchOrdersByClient(clientContains)).thenReturn(orderModels);
        when(orderMapper.createResponseFromModel(orderModel)).thenReturn(orderResponse);

        // Act & Assert
        mockMvc.perform(get("/orders")
                        .param("clientContains", clientContains))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].someField").value("someValue")); // Adjust based on actual response
    }
}
