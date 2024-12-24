package com.tui.proof.application.port.in;

import com.tui.proof.domain.model.AddressModel;

/** Manages addresses in the model */
public interface AddressPort {
    /**
     * Creates a new address or returns an existing one in the system
     *
     * @param addressModel {@link AddressModel} received
     * @return {@link AddressModel} If it exists, return the existing one. Otherwise, creates a new
     *     one based on the input param
     */
    AddressModel createAddress(final AddressModel addressModel);
}
