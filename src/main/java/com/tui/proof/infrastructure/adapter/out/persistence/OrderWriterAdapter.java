package com.tui.proof.infrastructure.adapter.out.persistence;

import com.tui.proof.application.exception.NotFoundException;
import com.tui.proof.application.port.out.OrderWriterPort;
import com.tui.proof.domain.model.AddressModel;
import com.tui.proof.domain.model.ClientModel;
import com.tui.proof.domain.model.OrderModel;
import com.tui.proof.infrastructure.adapter.out.persistence.entity.AddressEntity;
import com.tui.proof.infrastructure.adapter.out.persistence.entity.ClientEntity;
import com.tui.proof.infrastructure.adapter.out.persistence.entity.OrderEntity;
import com.tui.proof.infrastructure.adapter.out.persistence.mapper.OrderMapper;
import com.tui.proof.infrastructure.adapter.out.persistence.repository.IAddressRepo;
import com.tui.proof.infrastructure.adapter.out.persistence.repository.IClientRepo;
import com.tui.proof.infrastructure.adapter.out.persistence.repository.IOrderRepo;
import java.util.Optional;
import lombok.AllArgsConstructor;

/** Implementation of the {@link OrderWriterPort} */
@AllArgsConstructor
public class OrderWriterAdapter implements OrderWriterPort {
    private final IOrderRepo orderRepo;
    private final OrderMapper orderMapper;
    private final IAddressRepo addressRepo;
    private final IClientRepo clientRepo;

    /** {@inheritDoc} */
    @Override
    public OrderModel save(OrderModel orderModel) {
        final AddressModel deliveryAddress = orderModel.getDeliveryAddress();
        final ClientModel client = orderModel.getClient();
        Optional<AddressEntity> addressFound =
                addressRepo.findByStreetAndPostcodeAndCityAndCountry(
                        deliveryAddress.getStreet(),
                        deliveryAddress.getPostcode(),
                        deliveryAddress.getCity(),
                        deliveryAddress.getCountry());
        Optional<ClientEntity> clientFound =
                clientRepo.findByFirstNameAndLastNameAndTelephone(
                        client.getFirstName(), client.getLastName(), client.getTelephone());

        if (addressFound.isPresent() && clientFound.isPresent()) {
            OrderEntity newOrder =
                    OrderEntity.builder()
                            .pilotes(orderModel.getPilotes())
                            .address(addressFound.get())
                            .client(clientFound.get())
                            .orderTotal(orderModel.getOrderTotal())
                            .build();
            OrderEntity orderSaved = orderRepo.save(newOrder);
            return orderMapper.entityToModel(orderSaved);
        }
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public OrderModel update(OrderModel orderModel) throws NotFoundException {
        Optional<OrderEntity> orderEntity =
                orderRepo.findById(Integer.parseInt(orderModel.getNumber()));
        if (orderEntity.isEmpty()) throw new NotFoundException("Order not found");
        Optional<AddressEntity> addressEntity =
                addressRepo.findById(orderModel.getDeliveryAddress().getAddressId());
        if (addressEntity.isEmpty())
            throw new NotFoundException("Address linked to order was not found");
        Optional<ClientEntity> clientEntity =
                clientRepo.findById(orderModel.getClient().getClientId());
        if (clientEntity.isEmpty())
            throw new NotFoundException("Client linked to order was not found");

        OrderEntity orderEntity1 = orderEntity.get();
        orderEntity1.setPilotes(orderModel.getPilotes());
        orderEntity1.setOrderTotal(orderModel.getOrderTotal());
        orderEntity1.setClient(clientEntity.get());
        orderEntity1.setDeliveryAddress(addressEntity.get());

        OrderEntity orderSaved = orderRepo.save(orderEntity1);
        return orderMapper.entityToModel(orderSaved);
    }
}
