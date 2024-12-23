package com.tui.proof.infrastructure.adapter.out.persistence.repository;

import com.tui.proof.infrastructure.adapter.out.persistence.entity.AddressEntity;
import com.tui.proof.infrastructure.adapter.out.persistence.entity.ClientEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Manages the Client I/O operations
 */
import java.util.Optional;

public interface IClientRepo extends CrudRepository<ClientEntity,Integer> {

    /**
     * Find a client based on its attributes
     * @param fistNMame
     * @param lastName
     * @param telephone
     * @return {@link Optional< ClientEntity >} if exits. Empty optional in any other case
     */
    public Optional<ClientEntity> findByFirstNameAndLastNameAndTelephone(final String fistNMame, final String lastName, final String telephone);


}
