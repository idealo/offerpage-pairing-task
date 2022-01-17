package com.schema.checkout.service.discount;

import java.time.LocalDate;

import lombok.Data;

@Data
public class TimedPercentDiscount implements DiscountRule {
  private LocalDate blackFriday = LocalDate.now();
  private double percentage;

  public TimedPercentDiscount(LocalDate date,  double percentage) {
    this.percentage = percentage;
  }

  @Override
  public double getPrice(int productCount, double basePrice) {
    double totalPrice = productCount * basePrice;

    if (blackFriday.isEqual(LocalDate.now())) {
      return totalPrice - ((totalPrice) / percentage);
    }

    return totalPrice;
  }
}
