package com.tui.proof.infrastructure.adapter.out.persistence;

import com.tui.proof.application.port.in.AddressPort;
import com.tui.proof.application.port.out.OrderReaderPort;
import com.tui.proof.domain.model.OrderModel;
import com.tui.proof.infrastructure.adapter.out.persistence.mapper.OrderMapper;
import com.tui.proof.infrastructure.adapter.out.persistence.repository.IOrderRepo;
import lombok.AllArgsConstructor;

/**
 * Implementation of the {@link OrderReaderPort}
 */
@AllArgsConstructor
public class OrderReaderAdapter implements OrderReaderPort {
    private final IOrderRepo orderRepo;
    private final OrderMapper orderMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public OrderModel findOrderById(int orderId) {
        return null;
    }
}
