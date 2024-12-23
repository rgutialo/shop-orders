package com.tui.proof.infrastructure.adapter.out.persistence.repository;

import com.tui.proof.infrastructure.adapter.out.persistence.entity.AddressEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Manages the Address I/O operations
 */
public interface IAddressRepo extends CrudRepository<AddressEntity, Integer> {
    /**
     * Find an address based on its attributes
     * @param street
     * @param postcode
     * @param city
     * @param country
     * @return {@link Optional<AddressEntity>} if exits. Empty optional in any other case
     */
    Optional<AddressEntity> findByStreetAndPostcodeAndCityAndCountry(final String street, final String postcode, final String city, final String country);
}
