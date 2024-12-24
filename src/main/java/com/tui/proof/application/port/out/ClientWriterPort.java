package com.tui.proof.application.port.out;

import com.tui.proof.domain.model.ClientModel;

/** Manages write operations for clients in infraestructure package */
public interface ClientWriterPort {

    /**
     * Saves a new client based on the incoming data
     *
     * @param clientModel {@link ClientModel} with the new client
     * @return {@link ClientModel} with the new client created
     */
    ClientModel save(final ClientModel clientModel);
}
