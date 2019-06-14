package com.marcin.salesService.controller;

import com.marcin.salesService.model.Cart;
import com.marcin.salesService.model.Product;
import com.marcin.salesService.model.ProductInCart;
import com.marcin.salesService.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
public class CartController {

    private CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/cart")
    public List<ProductInCart> getCart() {

        return cartService.getAllProduct();
    }

    @PostMapping("/cart/{productID}/{productQuantity}")
    public List<ProductInCart> addProductToCart(@PathVariable("productID") @Min(value = 0, message = "Product ID has to be greater than 0") int productID,
                                                @PathVariable("productQuantity") @Min(value = 1, message = "Product quantity has to be greater than 1") int productQuantity){

        cartService.addToCart(productID, productQuantity);

        return cartService.getAllProduct();
    }

    @DeleteMapping("/cart/{productID}/{productQuantity}")
    public List<ProductInCart> removeProductFromCart(@RequestParam(name="productID") @Min(value = 0, message = "Product ID has to be greater than 0") int productID,
                                                     @PathVariable("productQuantity") @Min(value = 1, message = "Product quantity has to be greater than 1") int productQuantity){
        cartService.removeFromCart(productID, productQuantity);

        return cartService.getAllProduct();
    }

    @PostMapping("/cart")
    public ProductInCart testProduct(@RequestBody ProductInCart productInCart){
        return productInCart;
    }


}
