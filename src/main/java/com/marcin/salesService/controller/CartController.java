package com.marcin.salesService.controller;

import com.marcin.salesService.exception.ProductNotFoundException;
import com.marcin.salesService.model.AmountWrapper;
import com.marcin.salesService.model.Product;
import com.marcin.salesService.model.ProductInCart;
import com.marcin.salesService.model.ProductInCartWrapper;
import com.marcin.salesService.service.CartService;
import com.marcin.salesService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
public class CartController {

    private CartService cartService;

    private ProductService productService;

    @Autowired
    public CartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    /**
     * @return List of all products in cart
     */
    @GetMapping("/cart")
    public List<ProductInCart> getCart() {

        return cartService.getAllProduct();
    }


    /**
     * @param productInCartWrapper
     * @return List of all products in cart
     */
    @PostMapping("/cart")
    public List<ProductInCart> addToCart(@RequestBody ProductInCartWrapper productInCartWrapper){

        if(productInCartWrapper.getProductId() == 0 || productInCartWrapper.getQuantity() == 0){
            throw new ProductNotFoundException("Request body is not correct");
        }

        Product product = productService.getProductById(productInCartWrapper.getProductId());

        cartService.addToCart(product, productInCartWrapper.getQuantity());

        return cartService.getAllProduct();
    }

    /**
     * @param productInCartWrapper
     * @return List all left products in cart
     */
    @DeleteMapping("/cart")
    public List<ProductInCart> deleteFromCart(@RequestBody ProductInCartWrapper productInCartWrapper){
        Product product = productService.getProductById(productInCartWrapper.getProductId());

        cartService.removeFromCart(product, productInCartWrapper.getQuantity());

        return cartService.getAllProduct();
    }

    @GetMapping("/cart/amount")
    public AmountWrapper getAmount(){
        return cartService.getAmount();
    }



}
