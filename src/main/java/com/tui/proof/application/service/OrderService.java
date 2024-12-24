package com.tui.proof.application.service;

import com.tui.proof.application.exception.NotFoundException;
import com.tui.proof.application.exception.OrderUpdateException;
import com.tui.proof.application.port.in.AddressPort;
import com.tui.proof.application.port.in.ClientPort;
import com.tui.proof.application.port.in.OrderPort;
import com.tui.proof.application.port.out.OrderReaderPort;
import com.tui.proof.application.port.out.OrderWriterPort;
import com.tui.proof.application.port.out.PilotePriceReaderPort;
import com.tui.proof.domain.model.AddressModel;
import com.tui.proof.domain.model.ClientModel;
import com.tui.proof.domain.model.OrderModel;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/** Implementation of the {@link OrderPort} */
public class OrderService implements OrderPort {
    private final OrderReaderPort readerPort;
    private final OrderWriterPort writerPort;
    private final ClientPort clientPort;
    private final AddressPort addressPort;
    private final PilotePriceReaderPort pilotePriceReaderPort;

    public OrderService(
            OrderReaderPort readerPort,
            OrderWriterPort writerPort,
            ClientPort clientPort,
            AddressPort addressPort,
            PilotePriceReaderPort pilotePriceReaderPort) {
        this.readerPort = readerPort;
        this.writerPort = writerPort;
        this.clientPort = clientPort;
        this.addressPort = addressPort;
        this.pilotePriceReaderPort = pilotePriceReaderPort;
    }

    /** {@inheritDoc} */
    @Override
    public OrderModel createOrder(OrderModel orderRequestModel) {
        ClientModel client = clientPort.createClient(orderRequestModel.getClient());
        AddressModel address = addressPort.createAddress(orderRequestModel.getDeliveryAddress());

        OrderModel orderModel =
                OrderModel.builder()
                        .pilotes(orderRequestModel.getPilotes())
                        .client(client)
                        .deliveryAddress(address)
                        .orderTotal(
                                orderRequestModel.getPilotes() * pilotePriceReaderPort.getPrice())
                        .build();

        return writerPort.save(orderModel);
    }

    /** {@inheritDoc} */
    @Override
    public OrderModel updateOrder(OrderModel orderUpdated)
            throws OrderUpdateException, NotFoundException {
        if (orderUpdated.getNumber() == null) {
            throw new NotFoundException("Number attribute in order is missing");
        }
        Optional<OrderModel> orderFound =
                readerPort.findOrderById(Integer.parseInt(orderUpdated.getNumber()));
        if (orderFound.isEmpty()) throw new NotFoundException("Order not found");

        OrderModel orderRetrieved = orderFound.get();

        if (orderRetrieved.getCreationDate().isAfter(LocalDateTime.now().minusMinutes(5))) {
            ClientModel client = clientPort.createClient(orderUpdated.getClient());
            AddressModel address = addressPort.createAddress(orderUpdated.getDeliveryAddress());
            OrderModel orderModified =
                    OrderModel.builder()
                            .number(orderRetrieved.getNumber())
                            .pilotes(orderUpdated.getPilotes())
                            .client(client)
                            .deliveryAddress(address)
                            .orderTotal(
                                    orderUpdated.getPilotes() * pilotePriceReaderPort.getPrice())
                            .creationDate(orderRetrieved.getCreationDate())
                            .build();

            return writerPort.update(orderModified);
        }
        throw new OrderUpdateException("Order is being cooked, so it cannot be modified anymore");
    }

    /** {@inheritDoc} */
    @Override
    public List<OrderModel> searchOrdersByClient(String clientData) {
        return List.of();
    }
}
