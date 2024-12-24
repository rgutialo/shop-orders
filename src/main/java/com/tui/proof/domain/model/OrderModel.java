package com.tui.proof.domain.model;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/** Order business model */
@Data
@Builder
@EqualsAndHashCode
public class OrderModel {
    private String number;
    private AddressModel deliveryAddress;
    private ClientModel client;
    private int pilotes;
    private double orderTotal;
    private boolean updatable;
    private LocalDateTime creationDate;
}
