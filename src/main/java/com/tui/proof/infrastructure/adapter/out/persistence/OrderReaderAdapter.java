package com.tui.proof.infrastructure.adapter.out.persistence;

import com.tui.proof.application.port.out.OrderReaderPort;
import com.tui.proof.domain.model.OrderModel;
import com.tui.proof.infrastructure.adapter.out.persistence.entity.OrderEntity;
import com.tui.proof.infrastructure.adapter.out.persistence.mapper.OrderMapper;
import com.tui.proof.infrastructure.adapter.out.persistence.repository.IOrderRepo;
import java.util.Optional;
import lombok.AllArgsConstructor;

/** Implementation of the {@link OrderReaderPort} */
@AllArgsConstructor
public class OrderReaderAdapter implements OrderReaderPort {
    private final IOrderRepo orderRepo;
    private final OrderMapper orderMapper;

    /** {@inheritDoc} */
    @Override
    public Optional<OrderModel> findOrderById(int orderId) {
        Optional<OrderEntity> orderEntityFound = orderRepo.findById(orderId);
        return orderEntityFound.map(orderMapper::entityToModel);
    }
}
