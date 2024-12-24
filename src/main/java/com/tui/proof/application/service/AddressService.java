package com.tui.proof.application.service;

import com.tui.proof.application.port.in.AddressPort;
import com.tui.proof.application.port.out.AddressReaderPort;
import com.tui.proof.application.port.out.AddressWriterPort;
import com.tui.proof.domain.model.AddressModel;

import java.util.Optional;

/**
 * Implementation of the {@link AddressPort}
 */
public class AddressService implements AddressPort {
    private final AddressReaderPort readerPort;
    private final AddressWriterPort writerPort;

    public AddressService(AddressReaderPort readerPort, AddressWriterPort writerPort) {
        this.readerPort = readerPort;
        this.writerPort = writerPort;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AddressModel createAddress(AddressModel address) {
        Optional<AddressModel> existingAddress = this.searchAddress(address);
        if (existingAddress.isPresent())
            return existingAddress.get();

        AddressModel newAddress = AddressModel.builder()
                .street(address.getStreet())
                .postcode(address.getPostcode())
                .city(address.getCity())
                .country(address.getCountry())
                .build();
        return writerPort.save(newAddress);
    }

    /**
     * Searchs an existing address
     *
     * @param addressModel {@link AddressModel} with the address to look for
     * @return {@link Optional<AddressModel>} with the existing address. Empty optional in any other
     * case
     */
    Optional<AddressModel> searchAddress(AddressModel addressModel) {
        return readerPort.findByStreetAndPostcodeAndCityAndCountry(addressModel);
    }
}
