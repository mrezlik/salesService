package com.marcin.salesService.helper;


import com.marcin.salesService.model.AmountWrapper;
import com.marcin.salesService.model.Cart;
import com.marcin.salesService.model.ProductInCart;
import com.marcin.salesService.model.ProductInWarehouse;
import com.marcin.salesService.service.WarehouseService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PriceHelper {

    /**
     * @return
     */
    public AmountWrapper calculateAmount(Cart cart, WarehouseService warehouseService) {
        float amount = 0;
        List<ProductInCart> productsInCart = cart.getProductInCart();
        for(ProductInCart productInCart : productsInCart){
            ProductInWarehouse productInWarehouse = warehouseService.getProductInWarehouseById(productInCart.getProduct().getId());
            int quantityToDiscount = productInWarehouse.getQuantityToDiscount();
            if(quantityToDiscount <= productInCart.getQuantity()){
                int howManyPackage = productInCart.getQuantity() / quantityToDiscount;
                int howManySingle = productInCart.getQuantity() - howManyPackage * quantityToDiscount;
                amount += howManyPackage * productInWarehouse.getDiscountPrice() + howManySingle * productInWarehouse.getPrice();
            }else{
                amount += productInWarehouse.getPrice() * productInCart.getQuantity();
            }
        }

        return new AmountWrapper(amount);
    }
}
