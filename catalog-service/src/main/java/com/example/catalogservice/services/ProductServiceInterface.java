package com.example.catalogservice.services;

import com.example.catalogservice.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductServiceInterface {
    List<Product> findAllProducts();
    Optional<Product> findProductByCode(String code);
}
