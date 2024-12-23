package com.tui.proof.infrastructure.adapter.in.dto.v1;

public record OrderResponse(String number, ClientRequest client, DeliveryAddress deliveryAddress, int pilotes, double orderTotal) {
}
