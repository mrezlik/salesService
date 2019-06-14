package com.marcin.salesService.service;

import com.marcin.salesService.model.ProductInWarehouse;
import com.marcin.salesService.model.Warehouse;
import com.marcin.salesService.repository.ProductInWarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseService {

    private final ProductInWarehouseRepository productInWarehouseRepository;

    private Warehouse warehouse;

    @Autowired
    public WarehouseService(ProductInWarehouseRepository productInWarehouseRepository) {
        this.productInWarehouseRepository = productInWarehouseRepository;
        warehouse = new Warehouse();
        warehouse.setProductInWarehouse(productInWarehouseRepository.findAll());
    }

    public List<ProductInWarehouse> getProductsInWarehouse() {
        return warehouse.getProductInWarehouse();
    }

    public ProductInWarehouse getProductInWarehouseById(int productID) {
        return warehouse.getProductInWarehouse().stream().filter(productInWarehouse -> productInWarehouse.getProduct().getId() == productID).findFirst().get();
    }

    void substractFromWarehouse(ProductInWarehouse productInWarehouse, int quantity){

        int leftInWarehouse = productInWarehouse.getQuantity() - quantity;

        productInWarehouse.setQuantity(leftInWarehouse);

    }

    public void addToWarehouse(ProductInWarehouse productInWarehouse, int quantity) {

        int leftInWarehouse = productInWarehouse.getQuantity() + quantity;

        productInWarehouse.setQuantity(leftInWarehouse);

    }
}
