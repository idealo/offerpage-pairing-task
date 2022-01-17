package com.schema.checkout.service.discount;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuantityBasedDiscount implements DiscountRule {
  private int quantity;
  private double discountPrice;

  @Override
  public double getPrice(int productCount, double basePrice) {
    return getDiscountOnItems(productCount, discountPrice) * remainingItemsWithOutDiscount(productCount, basePrice);
  }

  private double getDiscountOnItems(int productCount, double discountPrice) {
    return Math.floorDiv(productCount, quantity) * discountPrice;
  }

  private double remainingItemsWithOutDiscount(int productCount, double basePrice) {
    return Math.floorMod(productCount, quantity) * basePrice;
  }
}
