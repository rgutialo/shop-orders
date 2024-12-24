package com.tui.proof.infrastructure.config;

import com.tui.proof.application.port.in.AddressPort;
import com.tui.proof.application.port.in.ClientPort;
import com.tui.proof.application.port.in.OrderPort;
import com.tui.proof.application.port.out.*;
import com.tui.proof.application.service.AddressService;
import com.tui.proof.application.service.ClientService;
import com.tui.proof.application.service.OrderService;
import com.tui.proof.infrastructure.adapter.out.persistence.*;
import com.tui.proof.infrastructure.adapter.out.persistence.mapper.AddressMapper;
import com.tui.proof.infrastructure.adapter.out.persistence.mapper.ClientMapper;
import com.tui.proof.infrastructure.adapter.out.persistence.mapper.OrderMapper;
import com.tui.proof.infrastructure.adapter.out.persistence.repository.IAddressRepo;
import com.tui.proof.infrastructure.adapter.out.persistence.repository.IClientRepo;
import com.tui.proof.infrastructure.adapter.out.persistence.repository.IOrderRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Bean definitions for classes defined in the application package and needed to be managed by
 * Spring framework
 */
@Configuration
public class ApplicationConfig {

    @Bean
    public OrderReaderPort orderReaderPort(IOrderRepo orderRepo, OrderMapper orderMapper) {
        return new OrderReaderAdapter(orderRepo, orderMapper);
    }

    @Bean
    public OrderWriterPort orderWriterPort(
            IOrderRepo orderRepo,
            OrderMapper orderMapper,
            IAddressRepo addressRepo,
            IClientRepo clientRepo) {
        return new OrderWriterAdapter(orderRepo, orderMapper, addressRepo, clientRepo);
    }

    @Bean
    public ClientReaderPort clientReaderPort(IClientRepo clientRepo, ClientMapper clientMapper) {
        return new ClientReaderAdapter(clientRepo, clientMapper);
    }

    @Bean
    public ClientWriterPort clientWriterPort(IClientRepo clientRepo, ClientMapper clientMapper) {
        return new ClientWriterAdapter(clientRepo, clientMapper);
    }

    @Bean
    public AddressReaderPort addressReaderPort(
            IAddressRepo addressRepo, AddressMapper addressMapper) {
        return new AddressReaderAdapter(addressRepo, addressMapper);
    }

    @Bean
    public AddressWriterPort addressWriterPort(
            IAddressRepo addressRepo, AddressMapper addressMapper) {
        return new AddressWriterAdapter(addressRepo, addressMapper);
    }

    @Bean
    public ClientPort clientPort(
            ClientReaderPort clientReaderPort, ClientWriterPort clientWriterPort) {
        return new ClientService(clientReaderPort, clientWriterPort);
    }

    @Bean
    public AddressPort addressPort(
            AddressReaderPort addressReaderPort, AddressWriterPort addressWriterPort) {
        return new AddressService(addressReaderPort, addressWriterPort);
    }

    @Bean
    public OrderPort createOrderPort(
            OrderReaderPort orderReaderPort,
            OrderWriterPort orderWriterPort,
            ClientPort clientPort,
            AddressPort addressPort,
            PilotePriceReaderPort pilotePriceReaderPort) {
        return new OrderService(
                orderReaderPort, orderWriterPort, clientPort, addressPort, pilotePriceReaderPort);
    }
}
