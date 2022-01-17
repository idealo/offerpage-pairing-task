package com.schema.checkout.service.checkout;

import com.schema.checkout.domain.Product;
import com.schema.checkout.service.discount.DiscountRule;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CheckoutServiceImpl implements CheckoutService {
  public static final int INCREMENT_BY_ONE = 1;
  
  private Map<Product, Integer> itemCounts = new HashMap<>();

  private Map<Long, DiscountRule> pricingStrategies;

  public CheckoutServiceImpl(Map<Long, DiscountRule> pricingStrategies) {
    this.pricingStrategies = pricingStrategies;
  }

  @Override
  public void resetShoppingCart() {
    itemCounts.clear();
  }

  @Override
  public void scan(Product sku) {
    if (itemCounts.containsKey(sku)) {
      itemCounts.put(sku, itemCounts.get(sku) + INCREMENT_BY_ONE);
    } else {
      itemCounts.put(sku, INCREMENT_BY_ONE);
    }
  }

  @Override
  public double total() {
    double result = -1;

    Set<Product> itemsInShoppingCart = itemCounts.keySet();

    for (Product product : itemsInShoppingCart) {
      int count = itemCounts.get(product);
      DiscountRule discountRule = pricingStrategies.get(product.getId());
      result += discountRule.getPrice(count, product.getUnitPrice());
    }

    return result;
  }
}
