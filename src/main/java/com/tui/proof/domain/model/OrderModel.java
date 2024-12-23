package com.tui.proof.domain.model;

import lombok.Builder;
import lombok.Data;

/**
 * Order business model
 */

@Data
@Builder
public class OrderModel {
  private String number;
  private AddressModel deliveryAddress;
  private ClientModel client;
  private int pilotes;
  private double orderTotal;

}
