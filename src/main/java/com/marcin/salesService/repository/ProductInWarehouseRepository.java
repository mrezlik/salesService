package com.marcin.salesService.repository;

import com.marcin.salesService.model.ProductInWarehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductInWarehouseRepository extends JpaRepository<ProductInWarehouse, Integer> {

    List<ProductInWarehouse> findAll();
    ProductInWarehouse findById(int id);

}
