package com.marcin.salesService.service;

import com.marcin.salesService.exception.NotEnoughtProductInWarehouseException;
import com.marcin.salesService.model.Cart;
import com.marcin.salesService.model.Product;
import com.marcin.salesService.model.ProductInCart;
import com.marcin.salesService.model.ProductInWarehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    private Cart cart;

    private final WarehouseService warehouseService;

    public CartService(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
        cart = new Cart();
        cart.setProductInCart(new ArrayList<>());
    }

    public List<ProductInCart> getAllProduct() {

        return cart.getProductInCart();
    }

    public ProductInCart getProductFromCartById(int productId){

        return cart.getProductInCart().stream().filter(productInCart -> productInCart.getProduct().getId() == productId).findFirst().get();
    }

    public void addToCart(int productId, int productQuantity){

        ProductInWarehouse productInWarehouse = warehouseService.getProductInWarehouseById(productId);

        if(productInWarehouse.getQuantity() >= productQuantity){
             ProductInCart productInCart = new ProductInCart(productInWarehouse.getProduct(), productQuantity);
             cart.getProductInCart().add(productInCart);
             warehouseService.substractFromWarehouse(productInWarehouse, productQuantity);
        }else{
            throw new NotEnoughtProductInWarehouseException("In warehouse is only - " + productInWarehouse.getQuantity() + " of " + productInWarehouse.getProduct().getName());
        }

    }

    public void removeFromCart(int productId, int quantity){
        ProductInWarehouse productInWarehouse = warehouseService.getProductInWarehouseById(productId);
        ProductInCart productInCart = getProductFromCartById(productId);

        if(productInCart.getQuantity() > quantity){
            substractFromCart(productInCart, quantity);
            warehouseService.addToWarehouse(productInWarehouse, quantity);
        }
    }

    private void substractFromCart(ProductInCart productInCart, int quantityToSubstract) {

        int leftInCart = productInCart.getQuantity() - quantityToSubstract;

        productInCart.setQuantity(leftInCart);
    }
}
