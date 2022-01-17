package com.schema.checkout.service.checkout;

import com.schema.checkout.domain.Product;

public interface CheckoutService {
  void resetShoppingCart();
  void scan(Product sku);
  double total();
}
