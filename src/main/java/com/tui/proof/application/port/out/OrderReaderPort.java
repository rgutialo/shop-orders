package com.tui.proof.application.port.out;

import com.tui.proof.domain.model.OrderModel;
import java.util.List;
import java.util.Optional;

/** Manages read operations for orders in infraestructure package */
public interface OrderReaderPort {

    Optional<OrderModel> findOrderById(final int orderId);

    List<OrderModel> findOrdersByClientData(final String clientData);
}
