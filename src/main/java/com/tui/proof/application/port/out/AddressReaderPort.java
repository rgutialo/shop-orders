package com.tui.proof.application.port.out;

import com.tui.proof.domain.model.AddressModel;
import java.util.Optional;

/** Manages read operations for addresses in infraestructure package */
public interface AddressReaderPort {

    /**
     * Find an address based on the input parameters
     *
     * @param addressModel {@link AddressModel} with the data to search
     * @return {@link Optional<AddressModel>} with the existing address. Empty optional in any other
     *     case
     */
    Optional<AddressModel> findByStreetAndPostcodeAndCityAndCountry(
            final AddressModel addressModel);
}
