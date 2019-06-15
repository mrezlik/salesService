package com.marcin.salesService.service;

import com.marcin.salesService.exception.ProductNotFoundException;
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
        warehouse.setProductInWarehouse(this.productInWarehouseRepository.findAll());
    }

    public List<ProductInWarehouse> getProductsInWarehouse() {
        return warehouse.getProductInWarehouse();
    }

    /**
     * This method get all product from warehouse and filter it by given id
     * If in warehouse is no product with this id throw ProductNotFoundException
     * @throws ProductNotFoundException
     * @param productID
     * @return ProductInWarehouse
     */
    public ProductInWarehouse getProductInWarehouseById(int productID) {
        return warehouse.getProductInWarehouse()
                            .stream()
                            .filter(productInWarehouse -> productInWarehouse.getProduct().getId() == productID)
                            .findFirst()
                            .orElseThrow(ProductNotFoundException::new);
    }

}
