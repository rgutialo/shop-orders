package com.tui.proof.application.port.out;

import com.tui.proof.application.exception.NotFoundException;
import com.tui.proof.domain.model.OrderModel;

/** Manages write operations for orders in infraestructure package */
public interface OrderWriterPort {

    /**
     * Saves a new order based on the incoming data
     *
     * @param orderModel {@link OrderModel} with the new order
     * @return {@link OrderModel} with the new order created
     */
    OrderModel save(final OrderModel orderModel);

    OrderModel update(final OrderModel orderModel) throws NotFoundException;
}
