package com.example.catalogservice.controllers;

import com.example.catalogservice.entities.Product;
import com.example.catalogservice.services.ProductServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 Class to create the RestFul Api for the products
 @Author: Brahian Velazquez Tellez
 **/
@RestController
@RequestMapping("api/products")
@Slf4j
public class ProductController {
    private final ProductServiceInterface productService;

    @Autowired
    public ProductController(ProductServiceInterface productService){
        this.productService = productService;
    }

    @GetMapping("findAllProducts")
    public ResponseEntity<List<Product>> allProducts(){
        log.info("Finding information for all products");
        List<Product> findProducts = productService.findAllProducts();
        if(findProducts== null && findProducts.size() == 0)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<List<Product>>(findProducts, HttpStatus.OK);
    }

    @GetMapping("findProductByCode/{code}")
    public ResponseEntity<Product> productByCode(@PathVariable String code) throws Exception {
        log.info("Finding information for product with code" + code);
        Optional<Product> productByCode = productService.findProductByCode(code);
        if(!productByCode.isPresent())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(productByCode.get(),HttpStatus.OK);
    }

    @GetMapping("findProductByName/{productName}")
    public ResponseEntity<List<Product>> findProductByName(@PathVariable String productName){
        log.info("looking for Products quering by name");
        List<Product> listProduct = productService.findProductByName(productName);

        if(listProduct != null && listProduct.size() < 1)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<List<Product>>(listProduct, HttpStatus.OK);
    }

    @GetMapping("findProductByDescription/{description}")
    public ResponseEntity<List<Product>> findProductsByDescription(@PathVariable String description){
        log.info("looking for Products quering by description");
        List<Product> listProductByDesc = productService.findProductByDescription(description);

        if(listProductByDesc != null && listProductByDesc.size() < 1)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<List<Product>>(listProductByDesc,HttpStatus.OK);
    }

    @PostMapping("saveProduct")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product){
        log.info("Save a new Product...");

        Product savedProduct = productService.saveOrUpdateProduct(product);

        if(savedProduct == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return  new ResponseEntity<Product>(savedProduct, HttpStatus.OK);
    }
    @PutMapping("updateProduct")
    public ResponseEntity<Product> updateProduct(@RequestBody Product updateProduct){
        log.info("Update product with  id " + updateProduct);

        Product updatedProduct = productService.saveOrUpdateProduct(updateProduct);
        if(updatedProduct == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<Product>(HttpStatus.OK);
    }

    @DeleteMapping("deleteProduct/{idProduct}")
    public ResponseEntity deleteProduct(@PathVariable Long idProduct){
        log.info("Delete product with id " + idProduct);

        if(!productService.findByIdProduct(idProduct).isPresent()){
            log.info("Product with id :" + idProduct);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else
            return new ResponseEntity<>(HttpStatus.OK);

    }

}
