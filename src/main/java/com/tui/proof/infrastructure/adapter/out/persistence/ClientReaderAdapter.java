package com.tui.proof.infrastructure.adapter.out.persistence;

import com.tui.proof.application.port.out.ClientReaderPort;
import com.tui.proof.domain.model.ClientModel;
import com.tui.proof.infrastructure.adapter.out.persistence.entity.ClientEntity;
import com.tui.proof.infrastructure.adapter.out.persistence.mapper.ClientMapper;
import com.tui.proof.infrastructure.adapter.out.persistence.repository.IClientRepo;
import java.util.Optional;
import lombok.AllArgsConstructor;

/** Implementation of the {@link ClientReaderPort} */
@AllArgsConstructor
public class ClientReaderAdapter implements ClientReaderPort {

    private final IClientRepo clientRepo;
    private final ClientMapper clientMapper;

    /** {@inheritDoc} */
    @Override
    public Optional<ClientModel> findByFirstNameAndLastNameAndTelephone(ClientModel client) {
        Optional<ClientEntity> foundClient =
                clientRepo.findByFirstNameAndLastNameAndTelephone(
                        client.getFirstName(), client.getLastName(), client.getTelephone());
        return foundClient.map(clientMapper::entityToModel);
    }
}
