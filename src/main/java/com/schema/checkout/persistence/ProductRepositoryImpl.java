package com.schema.checkout.persistence;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import com.schema.checkout.service.discount.DiscountRule;
import com.schema.checkout.service.discount.NoDiscount;
import com.schema.checkout.service.discount.PercentageDiscount;
import com.schema.checkout.service.discount.QuantityBasedDiscount;
import com.schema.checkout.service.discount.TimedPercentDiscount;

public class ProductRepositoryImpl implements ProductRepository {
  private Map<Long, DiscountRule> productPricingStrategies = new HashMap<>();

  public ProductRepositoryImpl() {
    populateProductsWithPricingStrategy();
  }

  @Override
  public Map<Long, DiscountRule> listOfProducts() {
    return productPricingStrategies;
  }

  private void populateProductsWithPricingStrategy() {
    Long productA = 1L;
    DiscountRule discountForProductA = new QuantityBasedDiscount(3, 100.0);
    productPricingStrategies.put(productA, discountForProductA);

    Long productB = 2L;
    DiscountRule discountForProductB = new QuantityBasedDiscount(2, 80.0);
    productPricingStrategies.put(productB, discountForProductB);

    Long productC = 3L;
    DiscountRule discountForProductC = new NoDiscount();
    productPricingStrategies.put(productC, discountForProductC);

    Long productD = 4L;
    DiscountRule discountForProductD = new NoDiscount();
    productPricingStrategies.put(productD, discountForProductD);

    Long productE = 5L;
    DiscountRule discountForProductE = new PercentageDiscount(10.0);
    productPricingStrategies.put(productE, discountForProductE);

    Long productF = 6L;
    TimedPercentDiscount timedPercentDiscount = new TimedPercentDiscount(LocalDate.of(2021, 11, 28), 50);
    productPricingStrategies.put(productF, timedPercentDiscount);
  }
}
