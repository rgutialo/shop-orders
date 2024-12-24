package com.tui.proof.application.service;

import com.tui.proof.application.port.in.ClientPort;
import com.tui.proof.application.port.out.ClientReaderPort;
import com.tui.proof.application.port.out.ClientWriterPort;
import com.tui.proof.domain.model.ClientModel;

import java.util.Optional;

/**
 * Implementation of the {@link ClientPort}
 */
public class ClientService implements ClientPort {

    private final ClientReaderPort readerPort;
    private final ClientWriterPort writerPort;

    public ClientService(ClientReaderPort readerPort, ClientWriterPort writerPort) {
        this.readerPort = readerPort;
        this.writerPort = writerPort;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ClientModel createClient(ClientModel client) {
        Optional<ClientModel> existingClient = this.searchClient(client);
        if (existingClient.isPresent())
            return existingClient.get();

        ClientModel newClient = ClientModel.builder()
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .telephone(client.getTelephone())
                .build();

        return writerPort.save(newClient);
    }

    /**
     * Looks for a client based on the param received
     *
     * @param clientModel {@link ClientModel} received
     * @return {@link ClientModel} Returns the client. Otherwise, returns empty optional.
     */
    Optional<ClientModel> searchClient(ClientModel clientModel) {
        return readerPort.findByFirstNameAndLastNameAndTelephone(clientModel);
    }
}
