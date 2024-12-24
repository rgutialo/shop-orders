package com.tui.proof.domain.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/** Address business model */
@Data
@Builder
@EqualsAndHashCode
public class AddressModel {
    private int addressId;
    private String street;
    private String postcode;
    private String city;
    private String country;
}
