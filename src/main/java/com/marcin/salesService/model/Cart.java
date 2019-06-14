package com.marcin.salesService.model;


import lombok.Data;

import java.util.List;


@Data
public class Cart {

    private List<ProductInCart> productInCart;


}
