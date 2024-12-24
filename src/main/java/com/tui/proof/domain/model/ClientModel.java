package com.tui.proof.domain.model;

import java.util.Set;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/** Client business model */
@Data
@Builder
@EqualsAndHashCode
public class ClientModel {
    private int clientId;
    private String firstName;
    private String lastName;
    private String telephone;
    private Set<AddressModel> deliveryAddress;
}
