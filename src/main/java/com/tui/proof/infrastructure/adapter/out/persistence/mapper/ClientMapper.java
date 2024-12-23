package com.tui.proof.infrastructure.adapter.out.persistence.mapper;

import com.tui.proof.domain.model.AddressModel;
import com.tui.proof.domain.model.ClientModel;
import com.tui.proof.infrastructure.adapter.out.persistence.entity.AddressEntity;
import com.tui.proof.infrastructure.adapter.out.persistence.entity.ClientEntity;
import org.mapstruct.Mapper;

/**
 * Mapper class responsible for mapping {@link ClientEntity} and {@link ClientModel}
 */
@Mapper(componentModel = "spring")
public interface ClientMapper {

    /**
     * Maps method between model and entity
     * @param clientModel {@link ClientModel} input object
     * @return {@link ClientEntity} associated to the input param
     */
    ClientEntity modelToEntity(ClientModel clientModel);

    /**
     * Maps method between entity and model
     * @param clientEntity {@link ClientEntity} input object
     * @return {@link ClientModel} associated to the input param
     */
    ClientModel entityToModel(ClientEntity clientEntity);
}
