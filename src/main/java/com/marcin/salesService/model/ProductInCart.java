package com.marcin.salesService.model;

import lombok.Data;

@Data
public class ProductInCart {

    private Product product;

    private int quantity;

    public ProductInCart(Product product, int productQuantity) {
        this.product = product;

        this.quantity = productQuantity;
    }
}
