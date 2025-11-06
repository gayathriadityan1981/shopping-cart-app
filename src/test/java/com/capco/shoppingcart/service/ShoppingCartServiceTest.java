package com.capco.shoppingcart.service;

import com.capco.shoppingcart.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ShoppingCartServiceTest {

    @Autowired
    private ShoppingCartService cartService;


    private ShoppingCartService shoppingCartService;
    private IndividualClient individualClient;
    private ProfessionalClient professionalClientHighRevenue;
    private ProfessionalClient professionalClientLowRevenue;

    @BeforeEach
    void setUp() {
        shoppingCartService = new ShoppingCartService();

        // Create mock client objects for testing
        individualClient = new IndividualClient("IND-001","JOHN","BRITTO");

        // > €10M
        professionalClientHighRevenue = new ProfessionalClient("PRO-H-001","",15000000d);

        // < €10M
        professionalClientLowRevenue = new ProfessionalClient("PRO-L-001","",5_000_000d);

    }

    @Test
    void testCalculateTotalCostForIndividualClient() {
        // Shopping cart for an individual client

        // €1500
        CartItem highEndPhone = new CartItem();
        highEndPhone.setProductType(ProductType.HIGH_END_PHONE);
        highEndPhone.setQuantity(1);

        // €800 * 2 = €1600
        CartItem midRangePhone = new CartItem();
        midRangePhone.setProductType(ProductType.MID_RANGE_PHONE);
        midRangePhone.setQuantity(2);

        //  €1200
        CartItem laptop = new CartItem();
        laptop.setProductType(ProductType.LAPTOP);
        laptop.setQuantity(1);
        List<CartItem> items = Arrays.asList(highEndPhone,midRangePhone,laptop);
        double expectedTotal = 1500.0 + (800.0 * 2) + 1200.0; // = €4300

        double actualTotal = shoppingCartService.calculateTotalCost(individualClient, items);
        assertEquals(expectedTotal, actualTotal, "Total cost for individual client is incorrect");
    }

    @Test
    void testCalculateTotalCostForProfessionalClientHighRevenue() {
        // Shopping cart for a professional client with high revenue (> €10M)
        // €1000 * 2 = €2000
        CartItem highEndPhone = new CartItem();
        highEndPhone.setProductType(ProductType.HIGH_END_PHONE);
        highEndPhone.setQuantity(2);
        // €900
        CartItem midRangePhone = new CartItem();
        midRangePhone.setProductType(ProductType.MID_RANGE_PHONE);
        midRangePhone.setQuantity(1);

        List<CartItem> items = Arrays.asList(highEndPhone, midRangePhone);
        // = €2900
        double expectedTotal = (1000.0 * 2) + 550.0;

        double actualTotal = shoppingCartService.calculateTotalCost(professionalClientHighRevenue, items);
        assertEquals(expectedTotal, actualTotal, "Total cost for high-revenue professional client is incorrect");
    }

    @Test
    void testCalculateTotalCostForProfessionalClientLowRevenue() {
        // Shopping cart for a professional client with low revenue (< €10M)

        // €600
        CartItem midRangePhone = new CartItem();
        midRangePhone.setProductType(ProductType.MID_RANGE_PHONE);
        midRangePhone.setQuantity(1);

        // €1150
        CartItem highRangePhone = new CartItem();
        highRangePhone.setProductType(ProductType.HIGH_END_PHONE);
        highRangePhone.setQuantity(1);
        List<CartItem> items = Arrays.asList(midRangePhone,highRangePhone);

        // = €1750
        double expectedTotal = 600.0 + 1150.0;

        double actualTotal = shoppingCartService.calculateTotalCost(professionalClientLowRevenue, items);
        assertEquals(expectedTotal, actualTotal, "Total cost for low-revenue professional client is incorrect");
    }
    @Test
    void testGetProductPriceForIndividualClient() {
        assertEquals(1500, shoppingCartService.getProductPrice(individualClient, ProductType.HIGH_END_PHONE));
        assertEquals(800.0, shoppingCartService.getProductPrice(individualClient, ProductType.MID_RANGE_PHONE));
        assertEquals(1200.0, shoppingCartService.getProductPrice(individualClient, ProductType.LAPTOP));

    }

    @Test
    void testGetProductPriceForProfessionalClientHighRevenue() {
        assertEquals(1000, shoppingCartService.getProductPrice(professionalClientHighRevenue, ProductType.HIGH_END_PHONE));
        assertEquals(550.0, shoppingCartService.getProductPrice(professionalClientHighRevenue, ProductType.MID_RANGE_PHONE));
        assertEquals(900.0, shoppingCartService.getProductPrice(professionalClientHighRevenue, ProductType.LAPTOP));
    }

    @Test
    void testGetProductPriceForProfessionalClientLowRevenue() {
        assertEquals(1150.0, shoppingCartService.getProductPrice(professionalClientLowRevenue, ProductType.HIGH_END_PHONE));
        assertEquals(600.0, shoppingCartService.getProductPrice(professionalClientLowRevenue, ProductType.MID_RANGE_PHONE));
        assertEquals(1000.0, shoppingCartService.getProductPrice(professionalClientLowRevenue, ProductType.LAPTOP));
    }
}
