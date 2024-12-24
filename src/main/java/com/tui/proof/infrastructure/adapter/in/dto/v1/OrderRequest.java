package com.tui.proof.infrastructure.adapter.in.dto.v1;

import com.tui.proof.infrastructure.adapter.in.validations.AllowedPilotes;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderRequest {
    private String number;

    @AllowedPilotes(
            value = {5, 10, 15},
            message = "Pilotes must be one of 5, 10, or 15")
    private int pilotes;

    private ClientRequest client;
    private DeliveryAddress deliveryAddress;
}
