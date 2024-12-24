package com.tui.proof.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

/** Address entity */
@Getter
@Builder
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ADDRESS")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addressId;

    @Column(name = "street")
    private String street;

    @Column(name = "postcode")
    private String postcode;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Builder
    public AddressEntity(
            final int addressId,
            final String street,
            final String postcode,
            final String city,
            final String country) {
        this.addressId = addressId;
        this.street = street;
        this.postcode = postcode;
        this.city = city;
        this.country = country;
    }
}
