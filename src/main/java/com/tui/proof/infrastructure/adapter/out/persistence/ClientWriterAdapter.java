package com.tui.proof.infrastructure.adapter.out.persistence;

import com.tui.proof.application.port.in.AddressPort;
import com.tui.proof.domain.model.ClientModel;
import com.tui.proof.application.port.out.ClientWriterPort;
import com.tui.proof.infrastructure.adapter.out.persistence.entity.ClientEntity;
import com.tui.proof.infrastructure.adapter.out.persistence.repository.IClientRepo;
import com.tui.proof.infrastructure.adapter.out.persistence.mapper.ClientMapper;
import lombok.AllArgsConstructor;

/**
 * Implementation of the {@link ClientWriterPort}
 */
@AllArgsConstructor
public class ClientWriterAdapter implements ClientWriterPort {

    private final IClientRepo clientRepo;
    private final ClientMapper clientMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public ClientModel save(final ClientModel clientModel) {
        ClientEntity savedClient = clientRepo.save(clientMapper.modelToEntity(clientModel));
        return clientMapper.entityToModel(savedClient);
    }
}
