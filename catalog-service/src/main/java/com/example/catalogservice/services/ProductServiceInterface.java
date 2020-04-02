package com.example.catalogservice.services;

import com.example.catalogservice.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductServiceInterface {
    List<Product> findAllProducts();
    Optional<Product> findProductByCode(String code);
    List<Product> findProductByName(String name);
    List<Product> findProductByDescription(String name);
    Product  saveOrUpdateProduct(Product product);
    Optional<Product> findByIdProduct(Long id);
    void deleteProduct(Long id);

}
