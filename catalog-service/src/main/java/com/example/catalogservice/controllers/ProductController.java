package com.example.catalogservice.controllers;

import com.example.catalogservice.entities.Product;
import com.example.catalogservice.services.ProductServiceInterface;
import com.example.catalogservice.util.ProductNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/products")
@Slf4j
public class ProductController {
    private final ProductServiceInterface productService;

    @Autowired
    public ProductController(ProductServiceInterface productService){
        this.productService = productService;
    }
    @GetMapping("")
    public List<Product> allProducts(){
        return productService.findAllProducts();
    }

    @GetMapping("/{code}")
    public Product productByCode(@PathVariable String code) throws Exception {
        return productService.findProductByCode(code)
                .orElseThrow(() -> new ProductNotFoundException("Product with code " +code+ "not found"));
    }
}
