package com.schema.checkout.service.discount;

public interface DiscountRule {
   default double getPrice(int productCount, double basePrice){
      return productCount * basePrice;
   }
}
