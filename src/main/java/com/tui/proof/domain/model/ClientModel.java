package com.tui.proof.domain.model;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

/**
 * Client business model
 */

@Data
@Builder
public class ClientModel {
  private int clientId;
  private String firstName;
  private String lastName;
  private String telephone;
  private Set<AddressModel> deliveryAddress;
}
