package com.tui.proof.application.port.in;

import com.tui.proof.domain.model.ClientModel;

/** Manages clients in the model */
public interface ClientPort {
    /**
     * Creates a new client or returns an existing one in the system
     *
     * @param clientModel {@link ClientModel} received
     * @return {@link ClientModel} If it exists, return the existing one. Otherwise, creates a new
     *     one based on the input param
     */
    ClientModel createClient(ClientModel clientModel);
    /**
     * Looks for a client based on the param received
     *
     * @param clientModel {@link ClientModel} received
     * @return {@link ClientModel} Returns the client. Otherwise, returns empty optional.
     */
    // Optional<ClientModel> searchClient (ClientModel clientModel);
}
