/*
 *  OrderTest
 *  1.0
 *  11/8/22, 8:28 PM
 *  Copyright (c) 2022 Unosquare
 *  Any illegal reproduction of this content will result in immediate legal action.
 */

package coe.unosquare.benefits.order;

import coe.unosquare.benefits.product.Product;
import coe.unosquare.benefits.util.ProductGenerator;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static coe.unosquare.benefits.util.PayOrderSimulator.payOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderTest {
    @Test
    void orderWithVisaMoreThan10ProductsDiscountTest() {
        Map<Product, Integer> products = ProductGenerator.generateProducts(15);
        assertEquals(0.15, payOrder(products, "Visa"));
    }

    @Test
    void orderWithVisa10ProductsDiscountTest() {
        Map<Product, Integer> products = ProductGenerator.generateProducts(10);
        assertEquals(0.15, payOrder(products, "Visa"));
    }

    @Test
    void orderWithVisa7ProductsDiscountTest() {
        Map<Product, Integer> products = ProductGenerator.generateProducts(7);
        assertEquals(0.10, payOrder(products, "Visa"));
    }

    @Test
    void orderWithVisaLessThan7ProductsDiscountTest() {
        Map<Product, Integer> products = ProductGenerator.generateProducts(5);
        assertEquals(0.05, payOrder(products, "Visa"));
    }
    
    @Test
    void orderWithMastercardMoreThan100SubtotalTest() {
        Map<Product, Integer> products = ProductGenerator.generateProducts(110.0);
        assertEquals(0.17, payOrder(products, "Mastercard"));
    }
    
    @Test
    void orderWithMastercardMoreThan75SubtotalTest() {
        Map<Product, Integer> products = ProductGenerator.generateProducts(85.0);
        assertEquals(0.12, payOrder(products, "Mastercard"));
    }
    
    @Test
    void orderWithMastercardLessThan75SubtotalTest() {
        Map<Product, Integer> products = ProductGenerator.generateProducts(55.0);
        assertEquals(0.08, payOrder(products, "Mastercard"));
    }
    
    @Test
    void orderWithOthercardLessThan75SubtotalTest() {
        Map<Product, Integer> products = ProductGenerator.generateProducts(55.0);
        assertEquals(0.0, payOrder(products, "American Express"));
    }
    
    @Test
    void orderWithOthercardMoreThan75SubtotalTest() {
        Map<Product, Integer> products = ProductGenerator.generateProducts(80.0);
        assertEquals(0.0, payOrder(products, "American Express"));
    }
    
    @Test
    void orderWithOthercardMoreThan100SubtotalTest() {
        Map<Product, Integer> products = ProductGenerator.generateProducts(120.0);
        assertEquals(0.0, payOrder(products, "American Express"));
    }
}
