/*
 *  Order
 *  1.0
 *  11/8/22, 8:28 PM
 *  Copyright (c) 2022 Unosquare
 *  Any illegal reproduction of this content will result in immediate legal action.
 */

package coe.unosquare.benefits.order;

import coe.unosquare.benefits.product.Product;
import java.util.Map;

/**
 * The type Order.
 */
public class Order implements Discount {
    /** Store the final list of products and quantity for each product. **/
    private final Map<Product, Integer> products;
    private final String VISA = "Visa";
    private final String MASTERCARD = "Mastercard";

    /**
     * Instantiates a new Order.
     *
     * @param productsMap the list of products added to the order
     */
    public Order(final Map<Product, Integer> productsMap) {
        products = productsMap;
    }

    /**
     * Pay double.
     *
     * @param paymentType the payment type
     * @return the double
     */
    public Double pay(final String paymentType) {        
        double subtotal = products.entrySet()
                .stream()
                .mapToDouble(product -> product.getKey().getPrice() * product.getValue())
                .sum();
        
        Double discount = calculateDiscount(paymentType, products, subtotal);
        
        return subtotal * (1.0 - discount);
    }
    
    /**
     * Calculate the discount to be applied to the order
     * 
     * @param paymentType the payment type
     * @param products the list of products added to the order
     * @param subtotal amount to pay before discount
     * @return discount
     */
    public double calculateDiscount(String paymentType, Map<Product, Integer> products, double subtotal) {
    	Double discount = 0.0;
    	
    	switch (paymentType) {
    	case VISA:
    		int count = 0;
    		try {
    			count = products.values()
    					.stream()
    					.reduce(0, (totalProductCount, quantity) -> totalProductCount += quantity);
    			if (count <= 0) throw new IncorrectAmountException("Products cannot be less than or equal to zero. ");
    			if (count >= 10) {
        			discount = 0.15;
        		} else if (count >= 7) {
        			discount = 0.10;
        		} else {
        			discount = 0.05;
        		}
    		} catch (IncorrectAmountException e) {
    			System.out.println(e.getMessage() + "Product count: " + count);
    		} catch (Exception e) {
    			System.out.println(e.getMessage());
    		}
    		
    		break;
    		
    	case MASTERCARD:
    		if (subtotal >= 100) {
    			discount = 0.17;
    		} else if (subtotal >= 75) {
    			discount = 0.12;
    		} else {
    			discount = 0.08;
    		}
    		
    		break;
    	}

    	return discount;
    }

    /**
     * Print.
     */
    public void print() {
         products.forEach((product, quantity) ->
                 System.out.println("Product:{" + product.getName() + ","
                         + product.getPrice() + ","
                         + product.getType()
                         + "},Quantity:" + quantity
                         + ",Total:" + product.getPrice() * quantity));
    }
}
