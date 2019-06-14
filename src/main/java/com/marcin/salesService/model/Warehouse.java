package com.marcin.salesService.model;

import lombok.Data;

import java.util.List;

@Data
public class Warehouse {

    private List<ProductInWarehouse> productInWarehouse;

}
