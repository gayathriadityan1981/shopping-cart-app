package com.capco.shoppingcart.model;

import lombok.Data;

import java.util.List;

@Data
public class ShoppingCartRequest {
    private Client client;
    private List<CartItem> items;
}
