package com.tui.proof.infrastructure.adapter.out.persistence;

import com.tui.proof.application.port.in.AddressPort;
import com.tui.proof.domain.model.AddressModel;
import com.tui.proof.application.port.out.AddressWriterPort;
import com.tui.proof.infrastructure.adapter.out.persistence.entity.AddressEntity;
import com.tui.proof.infrastructure.adapter.out.persistence.repository.IAddressRepo;
import com.tui.proof.infrastructure.adapter.out.persistence.mapper.AddressMapper;
import lombok.AllArgsConstructor;

/**
 * Implementation of the {@link AddressWriterPort}
 */
@AllArgsConstructor
public class AddressWriterAdapter  implements AddressWriterPort {

    private final IAddressRepo addressRepo;
    private final AddressMapper addressMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public AddressModel save(AddressModel addressModel) {
        AddressEntity savedEntity = addressRepo.save(addressMapper.modelToEntity(addressModel));
        return addressMapper.entityToModel(savedEntity);
    }
}
