package com.tui.proof.domain.model;

import lombok.Builder;
import lombok.Data;

/**
 * Address business model
 */

@Data
@Builder
public class AddressModel {
  private int addressId;
  private String street;
  private String postcode;
  private String city;
  private String country;
}
