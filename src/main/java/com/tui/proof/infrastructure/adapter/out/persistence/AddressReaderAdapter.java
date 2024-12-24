package com.tui.proof.infrastructure.adapter.out.persistence;

import com.tui.proof.application.port.out.AddressReaderPort;
import com.tui.proof.domain.model.AddressModel;
import com.tui.proof.infrastructure.adapter.out.persistence.entity.AddressEntity;
import com.tui.proof.infrastructure.adapter.out.persistence.mapper.AddressMapper;
import com.tui.proof.infrastructure.adapter.out.persistence.repository.IAddressRepo;
import java.util.Optional;
import lombok.AllArgsConstructor;

/** Implementation of the {@link AddressReaderPort} */
@AllArgsConstructor
public class AddressReaderAdapter implements AddressReaderPort {
    private final IAddressRepo addressRepo;
    private final AddressMapper addressMapper;

    /** {@inheritDoc} */
    public Optional<AddressModel> findByStreetAndPostcodeAndCityAndCountry(
            AddressModel addressModel) {
        Optional<AddressEntity> foundAddress =
                addressRepo.findByStreetAndPostcodeAndCityAndCountry(
                        addressModel.getStreet(),
                        addressModel.getPostcode(),
                        addressModel.getCity(),
                        addressModel.getCountry());
        return foundAddress.map(addressMapper::entityToModel);
    }
}
