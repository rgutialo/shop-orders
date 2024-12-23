package com.tui.proof.infrastructure.adapter.out.persistence.mapper;

import com.tui.proof.domain.model.AddressModel;
import com.tui.proof.infrastructure.adapter.out.persistence.entity.AddressEntity;
import org.mapstruct.Mapper;

/**
 * Mapper class responsible for mapping {@link AddressEntity} and {@link AddressModel}
 */
@Mapper(componentModel = "spring")
public interface AddressMapper {

    /**
     * Maps method between model and entity
     * @param addressModel {@link AddressModel} input object
     * @return {@link AddressEntity} associated to the input param
     */
    AddressEntity modelToEntity(AddressModel addressModel);

    /**
     * Maps method between entity and model
     * @param addressEntity {@link AddressEntity} input object
     * @return {@link AddressModel} associated to the input param
     */
    AddressModel entityToModel(AddressEntity addressEntity);
}
