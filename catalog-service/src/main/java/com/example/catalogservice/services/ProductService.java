package com.example.catalogservice.services;

import com.example.catalogservice.dto.ProductInventoryDao;
import com.example.catalogservice.entities.Product;
import com.example.catalogservice.feignClients.InventoryRestClient;
import com.example.catalogservice.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class ProductService implements ProductServiceInterface {
  private final ProductRepository productRepository;

  @Autowired
  InventoryRestClient inventoryRestClient;

  @Autowired
  public ProductService(ProductRepository productRepository){
      this.productRepository = productRepository;
  }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findProductByCode(String code) {
      Optional<Product> productOptional = productRepository.findByCode(code);

      if(productOptional.isPresent()){
          log.info("Consuming inventory service...");
            ResponseEntity<ProductInventoryDao> productInventory = inventoryRestClient.getProductInventory(code);
            if(productInventory.getStatusCode() == HttpStatus.OK && productInventory.getBody().getAvailableQuantity() != -1){
                productOptional.get().setInStock(true);
                productOptional.get().setQuantity(productInventory.getBody().getAvailableQuantity());
            } else productOptional.get().setQuantity(0);
      }

      return productOptional;
    }

    @Override
    public List<Product> findProductByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> findProductByDescription(String name) {
        return productRepository.findByDescription(name);
    }

    @Override
    public Product saveOrUpdateProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> findByIdProduct(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
