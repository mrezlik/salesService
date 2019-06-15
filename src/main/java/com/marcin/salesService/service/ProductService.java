package com.marcin.salesService.service;

import com.marcin.salesService.exception.ProductNotFoundException;
import com.marcin.salesService.model.Product;
import com.marcin.salesService.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * This method get product from database, by given id
     * If in database we don't product with this id throw ProductNotFoundException
     * @throws ProductNotFoundException
     * @param productId
     * @return product
     */
    public Product getProductById(int productId) {

        Product product = productRepository.findById(productId);
        if (product == null) {
            throw new ProductNotFoundException("In database we don't have product with this ID - " + productId);
        }
        return product;
    }
}

