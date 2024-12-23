package com.tui.proof.infrastructure.adapter.in.dto.v1;

import lombok.Data;

@Data
public class OrderRequest {
    private int pilotes;
    private ClientRequest client;
    private DeliveryAddress deliveryAddress;

}
