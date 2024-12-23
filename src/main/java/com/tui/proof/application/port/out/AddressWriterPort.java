package com.tui.proof.application.port.out;

import com.tui.proof.domain.model.AddressModel;

/**
 * Manages write operations for addresses in infraestructure package
 */
public interface AddressWriterPort {

    /**
     * Saves a new address based on the incoming data
     * @param addressModel {@link AddressModel} with the data
     * @return {@link AddressModel} with the saved data
     */
    AddressModel save (final AddressModel addressModel);
}
