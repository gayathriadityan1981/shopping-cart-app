package com.capco.shoppingcart.service;

import com.capco.shoppingcart.model.*;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.capco.shoppingcart.model.ClientType.INDIVIDUAL;

@Service
public class ShoppingCartService {

    public double calculateTotalCost(Client client, List<CartItem> items) {
        double totalCost = 0.0;

        for (CartItem item : items) {
            double productPrice = getProductPrice(client, item.getProductType());
            totalCost += productPrice * item.getQuantity();
        }

        return totalCost;
    }

    public double getProductPrice(Client client, ProductType productType) {
        // Prices for individual clients
        if (client instanceof IndividualClient) {
            switch (productType) {
                case HIGH_END_PHONE:
                    return 1500.0;
                case MID_RANGE_PHONE:
                    return 800.0;
                case LAPTOP:
                    return 1200.0;
            }
            // Prices for professional clients based on annual revenue
        } else if (client instanceof ProfessionalClient) {
            ProfessionalClient professionalClient = (ProfessionalClient) client;
            if (professionalClient.getAnnualRevenue() > 10000000) {
                switch (productType) {
                    case HIGH_END_PHONE:
                        return 1000.0;
                    case MID_RANGE_PHONE:
                        return 550.0;
                    case LAPTOP:
                        return 900.0;
                }
            } else {
                switch (productType) {
                    case HIGH_END_PHONE:
                        return 1150.0;
                    case MID_RANGE_PHONE:
                        return 600.0;
                    case LAPTOP:
                        return 1000.0;
                }
            }
        }
        return 0.0; // Should not be reached in this example
    }
}