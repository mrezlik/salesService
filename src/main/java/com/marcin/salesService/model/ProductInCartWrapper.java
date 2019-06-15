package com.marcin.salesService.model;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class ProductInCartWrapper {

    @NotNull(message = "Product ID cannot be missing or empty")
    @Min(value = 1, message = "Product ID has to be greater than 0")
    private int productId;

    @NotNull(message = "Quantity cannot be missing or empty")
    @Min(value = 1, message = "Quantity has to be greater than 0")
    private int quantity;

}
