package coe.unosquare.benefits.order;

import java.util.Map;

import coe.unosquare.benefits.product.Product;

public interface Discount {
	public double calculateDiscount(String paymentType, Map<Product, Integer> products, double subtotal);
}
