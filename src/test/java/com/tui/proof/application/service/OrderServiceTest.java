package com.tui.proof.application.service;

import com.tui.proof.application.exception.NotFoundException;
import com.tui.proof.application.exception.OrderUpdateException;
import com.tui.proof.application.port.in.AddressPort;
import com.tui.proof.application.port.in.ClientPort;
import com.tui.proof.application.port.out.OrderReaderPort;
import com.tui.proof.application.port.out.OrderWriterPort;
import com.tui.proof.application.port.out.PilotePriceReaderPort;
import com.tui.proof.domain.model.AddressModel;
import com.tui.proof.domain.model.ClientModel;
import com.tui.proof.domain.model.OrderModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @Mock
    private OrderReaderPort readerPort;
    @Mock
    private OrderWriterPort writerPort;
    @Mock
    private ClientPort clientPort;
    @Mock
    private AddressPort addressPort;
    @Mock
    private PilotePriceReaderPort pilotePriceReaderPort;

    @InjectMocks
    private OrderService orderService;


    private OrderModel orderModel;
    @Mock
    private ClientModel clientModel;
    @Mock
    private AddressModel addressModel;

    @BeforeEach
    void setUp() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);

        // Setup some basic mock data
        orderModel = OrderModel.builder()
                .number("12345")
                .pilotes(5)
                .client(clientModel)
                .deliveryAddress(addressModel)
                .creationDate(LocalDateTime.now().minusMinutes(3))
                .orderTotal(500.0)
                .build();
    }

    @Test
    void testCreateOrder() {
        // Arrange
        when(clientPort.createClient(any())).thenReturn(clientModel);
        when(addressPort.createAddress(any())).thenReturn(addressModel);
        when(pilotePriceReaderPort.getPrice()).thenReturn(100.0);
        when(writerPort.save(any(OrderModel.class))).thenReturn(orderModel);

        // Act
        OrderModel result = orderService.createOrder(orderModel);

        // Assert
        assertNotNull(result);
        assertEquals(orderModel.getNumber(), result.getNumber());
        verify(writerPort, times(1)).save(any(OrderModel.class));
    }

    @Test
    void testUpdateOrder() throws OrderUpdateException, NotFoundException {
        // Arrange
        OrderModel updatedOrder = orderModel.toBuilder()
                .pilotes(10)
                .orderTotal(1000.0)
                .creationDate(LocalDateTime.now().minusMinutes(3))
                .build();
        when(readerPort.findOrderById(anyInt())).thenReturn(Optional.of(orderModel));
        when(clientPort.createClient(any())).thenReturn(clientModel);
        when(addressPort.createAddress(any())).thenReturn(addressModel);
        when(pilotePriceReaderPort.getPrice()).thenReturn(100.0);
        when(writerPort.update(any(OrderModel.class))).thenReturn(updatedOrder);

        // Act
        OrderModel result = orderService.updateOrder(updatedOrder);

        // Assert
        assertNotNull(result);
        assertEquals(updatedOrder.getPilotes(), result.getPilotes());
        verify(writerPort, times(1)).update(any(OrderModel.class));
    }

    @Test
    void testUpdateOrderThrowsOrderUpdateException() {
        OrderModel outDatedOrderModel = OrderModel.builder()
                .number("12345")
                .pilotes(5)
                .client(clientModel)
                .deliveryAddress(addressModel)
                .creationDate(LocalDateTime.now().minusMinutes(10))
                .orderTotal(500.0)
                .build();


        // Arrange
        OrderModel updatedOrder = orderModel.toBuilder()
                .pilotes(10)
                .orderTotal(1000.0)
                .build();
        when(readerPort.findOrderById(anyInt())).thenReturn(Optional.of(outDatedOrderModel));

        // Act & Assert
        assertThrows(OrderUpdateException.class, () -> orderService.updateOrder(updatedOrder));
    }

    @Test
    void testSearchOrdersByClient() {
        // Arrange
        when(readerPort.findOrdersByClientData(anyString())).thenReturn(List.of(orderModel));

        // Act
        var result = orderService.searchOrdersByClient("client1");

        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(readerPort, times(1)).findOrdersByClientData(anyString());
    }
}
