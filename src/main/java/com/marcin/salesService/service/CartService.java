package com.marcin.salesService.service;

import com.marcin.salesService.exception.ProductException;
import com.marcin.salesService.exception.ProductNotFoundException;
import com.marcin.salesService.helper.PriceHelper;
import com.marcin.salesService.model.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    private Cart cart;

    private final WarehouseService warehouseService;
    private final PriceHelper priceHelper;

    public CartService(WarehouseService warehouseService, PriceHelper priceHelper) {
        this.warehouseService = warehouseService;
        this.priceHelper = priceHelper;
        cart = new Cart();
        cart.setProductInCart(new ArrayList<>());
    }

    public List<ProductInCart> getAllProduct() {

        return cart.getProductInCart();
    }

    /**
     * This method get all products in cart and filter it by given product id
     * @param productId
     * @return productInCart
     */
    public ProductInCart getProductFromCartById(int productId){

        ProductInCart  productInCart = cart.getProductInCart()
                                        .stream()
                                        .filter(p -> p.getProduct().getId() == productId)
                                        .findFirst()
                                        .orElseThrow(ProductNotFoundException::new);

        return productInCart;

    }

    /**
     * This method substract given quantity of product from cart
     * @param productInCart
     * @param quantityToSubtract
     */
    private void subtractQuantityFromCart(ProductInCart productInCart, int quantityToSubtract) {

        int leftInCart = productInCart.getQuantity() - quantityToSubtract;

        productInCart.setQuantity(leftInCart);
    }

    /**
     * This method add given quantity to product in cart
     * @param productInCart
     * @param quantity
     */
    private void addQuantityToCart(ProductInCart productInCart, int quantity){
        int newQuantity = productInCart.getQuantity() + quantity;
        productInCart.setQuantity(newQuantity);
    }

    /**
     * This method add given product and quantity to cart
     * If product exist only add quantity
     * @param product
     * @param quantity
     */
    public void addToCart(Product product, int quantity) {
        ProductInCart productInCart;
        try {
            productInCart = getProductFromCartById(product.getId());
            addQuantityToCart(productInCart, quantity);
        }catch (ProductNotFoundException e){
            productInCart = new ProductInCart(product, quantity);
            cart.getProductInCart().add(productInCart);
        }
    }

    /**
     * Method remove given quantity from product in cart
     * If product quantity is 0 after subtract remove it from cart
     * @param product
     * @param quantity
     */
    public void removeFromCart(Product product, int quantity) {
        try {
            ProductInCart productInCart = getProductFromCartById(product.getId());
            checkQuantity(productInCart, quantity);
            subtractQuantityFromCart(productInCart, quantity);
            if(productInCart.getQuantity() == 0){
                cart.getProductInCart().remove(productInCart);
            }
        }catch(ProductNotFoundException e) {
            throw new ProductNotFoundException("In cart you don't have this product");
        }
    }

    /**
     * Method checks if given quantity is not greater than quantity in cart
     * If quantity is greater throws custom exception
     * @throws ProductException
     * @param productInCart
     * @param quantity
     */
    private void checkQuantity(ProductInCart productInCart, int quantity) {

        if(productInCart.getQuantity() < quantity){
            throw new ProductException("In cart you don't have enough quantity of this product");
        }

    }


    public AmountWrapper getAmount() {
        AmountWrapper amount = priceHelper.calculateAmount(cart, warehouseService);

        return amount;
    }


}
