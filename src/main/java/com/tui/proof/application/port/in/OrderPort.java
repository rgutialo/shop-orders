package com.tui.proof.application.port.in;

import com.tui.proof.domain.model.AddressModel;
import com.tui.proof.domain.model.OrderModel;

import java.util.List;

/**
 * Manages orders in the model
 */
public interface OrderPort {
    /**
     * Creates a new order
     * @param orderModel {@link OrderModel} received
     * @return {@link OrderModel} with the new order created
     */
    OrderModel createOrder(OrderModel orderModel);
    /**
     * Updates an order.
     * @param orderModel {@link OrderModel} received
     * @return {@link OrderModel} with the order updated if allowed. Otherwise it raise an exception
     */
    OrderModel updateOrder(OrderModel orderModel);

    /**
     * Seach orders by client data
     * @param clientData {@link String} received
     * @return {@link  List<OrderModel>} which matches with de input. Otherwise, returns empty list.
     */
    List<OrderModel> searchOrdersByClient(String clientData);
}
