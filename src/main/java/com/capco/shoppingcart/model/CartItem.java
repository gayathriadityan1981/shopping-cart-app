package com.capco.shoppingcart.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CartItem {
    private ProductType productType;
    private int quantity;

}
