package com.schema.checkout.service.discount;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class PercentageDiscount implements DiscountRule {

  private double percentage;

  @Override
  public double getPrice(int productCount, double basePrice) {
    double totalPrice = productCount * basePrice;
    return totalPrice - totalPrice / percentage;
  }
}
