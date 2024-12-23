package com.tui.proof.application.service;

import com.tui.proof.application.port.in.AddressPort;
import com.tui.proof.application.port.in.ClientPort;
import com.tui.proof.application.port.in.OrderPort;
import com.tui.proof.application.port.out.OrderReaderPort;
import com.tui.proof.application.port.out.OrderWriterPort;
import com.tui.proof.application.port.out.PilotePriceReaderPort;
import com.tui.proof.domain.model.AddressModel;
import com.tui.proof.domain.model.ClientModel;
import com.tui.proof.domain.model.OrderModel;

import java.util.List;

/**
 * Implementation of the {@link OrderPort}
 */
public class OrderService implements OrderPort {
    private final OrderReaderPort readerPort;
    private final OrderWriterPort writerPort;
    private final ClientPort clientPort;
    private final AddressPort addressPort;
    private final PilotePriceReaderPort pilotePriceReaderPort;

    public OrderService(OrderReaderPort readerPort, OrderWriterPort writerPort, ClientPort clientPort, AddressPort addressPort, PilotePriceReaderPort pilotePriceReaderPort) {
        this.readerPort = readerPort;
        this.writerPort = writerPort;
        this.clientPort = clientPort;
        this.addressPort = addressPort;
        this.pilotePriceReaderPort = pilotePriceReaderPort;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OrderModel createOrder(OrderModel orderRequestModel) {
        ClientModel client = clientPort.createClient(orderRequestModel.getClient());
        AddressModel address = addressPort.createAddress(orderRequestModel.getDeliveryAddress());

        OrderModel orderModel = OrderModel.builder()
                .pilotes(orderRequestModel.getPilotes())
                .client(client)
                .deliveryAddress(address)
                .orderTotal(orderRequestModel.getPilotes() * pilotePriceReaderPort.getPrice())
                .build();

        return writerPort.save(orderModel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OrderModel updateOrder(OrderModel orderModel) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<OrderModel> searchOrdersByClient(String clientData) {
        return List.of();
    }
}
