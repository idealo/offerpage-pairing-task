package com.schema.checkout;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.schema.checkout.domain.Product;
import com.schema.checkout.persistence.ProductRepository;
import com.schema.checkout.persistence.ProductRepositoryImpl;
import com.schema.checkout.service.checkout.CheckoutService;
import com.schema.checkout.service.checkout.CheckoutServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ItemPricingStrategyTest {
  private final ProductRepository skuRepository = new ProductRepositoryImpl();
  private CheckoutService checkoutService;

  @BeforeEach
  public void initTest() {
    checkoutService = new CheckoutServiceImpl(skuRepository.listOfProducts());
  }

  @Test
  public void totalForQuantityBasedItems() {
    List<Product> items = new ArrayList<>();
    items.add(new Product(1L, "A", 40.0));
    items.add(new Product(2L, "A", 40.0));
    items.add(new Product(3L, "A", 40.0));
    assertEquals(100.0, calculatePrice(items));
  }

  @Test
  public void totalForPercentageBasedItems() {
    List<Product> items = new ArrayList<>();
    items.add(new Product(5L, "E", 100.0));
    items.add(new Product(5L, "E", 100.0));
    assertEquals(180.0, calculatePrice(items));
  }

  @Test
  public void totalForTimedDiscount() {
    List<Product> items = new ArrayList<>();
    items.add(new Product(6L, "E", 100.0));
    items.add(new Product(6L, "E", 100.0));
    assertEquals(100.0, calculatePrice(items));
  }

  @Test
  public void totalForNonDiscountProducts() {
    List<Product> items = new ArrayList<>();
    items.add(new Product(1L, "C", 25.0));
    items.add(new Product(2L, "D", 20.0));
    assertEquals(45.0, calculatePrice(items));
  }

  @Test
  public void totalForNoItemExistInCart() {
    List<Product> items = new ArrayList<>();
    assertEquals(0.0, calculatePrice(items));
  }

  @Test
  public void totalForQuantityAndPercentageBasedItems() {
    List<Product> items = new ArrayList<>();
    items.add(new Product(1L, "A", 40.0));
    items.add(new Product(2L, "A", 40.0));
    items.add(new Product(3L, "A", 40.0));
    items.add(new Product(5L, "E", 100.0));
    assertEquals(190.0, calculatePrice(items));
  }

  @Test
  public void totalForQuantityAndNoCurrentBasedItems() {
    List<Product> items = new ArrayList<>();
    items.add(new Product(1L, "A", 40.0));
    items.add(new Product(2L, "A", 40.0));
    items.add(new Product(3L, "A", 40.0));
    items.add(new Product(4L, "D", 20.0));
    assertEquals(120.0, calculatePrice(items));
  }

  @Test
  public void incremental() {
    Product firstItemA = new Product(1L, "A", 40.0);
    Product secondItemA = new Product(3L, "A", 40.0);
    Product thirdItemA = new Product(4L, "A", 40.0);

    Product firstItemB = new Product(2L, "B", 50.0);
    Product secondItemB = new Product(5L, "B", 50.0);

    assertEquals(0.0, checkoutService.total());

    checkoutService.scan(firstItemA);
    assertEquals(40.0, checkoutService.total());

    checkoutService.scan(firstItemB);
    assertEquals(90.0, checkoutService.total());

    checkoutService.scan(secondItemA);
    assertEquals(130.0, checkoutService.total());

    checkoutService.scan(thirdItemA);
    assertEquals(150.0, checkoutService.total());

    checkoutService.scan(secondItemB);
    assertEquals(180.0, checkoutService.total());
  }

  private double calculatePrice(List<Product> products) {
    for (Product sku : products) {
      checkoutService.scan(sku);
    }
    return checkoutService.total();
  }


}
