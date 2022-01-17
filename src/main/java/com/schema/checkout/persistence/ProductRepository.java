package com.schema.checkout.persistence;

import com.schema.checkout.domain.Product;
import com.schema.checkout.service.discount.DiscountRule;

import java.util.Map;

public interface ProductRepository {
  Map<Long, DiscountRule> listOfProducts();
}
