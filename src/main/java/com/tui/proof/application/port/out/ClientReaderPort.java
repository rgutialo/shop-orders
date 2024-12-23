package com.tui.proof.application.port.out;

import com.tui.proof.domain.model.ClientModel;

import java.util.Optional;

/**
 * Manages read operations for clients in infraestructure package
 */
public interface ClientReaderPort {

    /**
     * Search client by input data received
     * @param client {@link ClientModel} with the data to search
     * @return {@link Optional<ClientModel>} if it exists, otherwise returns empty optional
     */
    Optional<ClientModel> findByFirstNameAndLastNameAndTelephone(final ClientModel client);
}
