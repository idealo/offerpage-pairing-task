package com.schema.checkout.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
public class Product {
  @EqualsAndHashCode.Exclude
  private Long id;

  private String name;

  @EqualsAndHashCode.Exclude
  private double unitPrice;
}
