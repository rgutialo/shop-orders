package com.tui.proof.application.port.out;

import com.tui.proof.domain.model.OrderModel;

/**
 * Manages read operations for orders in infraestructure package
 */
public interface OrderReaderPort {


    OrderModel findOrderById(final int orderId);
}
