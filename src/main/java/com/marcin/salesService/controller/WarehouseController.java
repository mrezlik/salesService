package com.marcin.salesService.controller;

import com.marcin.salesService.model.ProductInWarehouse;
import com.marcin.salesService.model.Warehouse;
import com.marcin.salesService.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class WarehouseController {

    private final WarehouseService warehouseService;


    @Autowired
    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping("/warehouse")
    private List<ProductInWarehouse> getProductsInWarehouse(){
        return warehouseService.getProductsInWarehouse();
    }

    @GetMapping("/warehouse/{productID}")
    private ProductInWarehouse getProductInWarehouseById(@PathVariable int productID){

        return warehouseService.getProductInWarehouseById(productID);
    }

}
