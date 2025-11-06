package com.capco.shoppingcart.controller;

import com.capco.shoppingcart.model.*;
import com.capco.shoppingcart.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/shopping-cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService = new ShoppingCartService();

    @PostMapping(value = "/calculate")
    public ResponseEntity<ShoppingCartResponse> calculateTotal(@RequestBody ShoppingCartRequest request) {

        // Validate request body
        if (request == null || request.getClient() == null || request.getItems() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            double totalCost = shoppingCartService.calculateTotalCost(request.getClient(), request.getItems());
            ShoppingCartResponse response = new ShoppingCartResponse();
            response.setTotalCost(totalCost);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            // Log the exception
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}