package com.example.catalogservice.services;

import com.example.catalogservice.entities.Product;
import com.example.catalogservice.repositories.ProductRepository;
import com.example.catalogservice.util.MyThreadLocalHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@Slf4j
public class ProductoService implements ProductServiceInterface {
  private final ProductRepository productRepository;
  private final RestTemplate restTemplate;

  @Autowired
  public ProductoService(ProductRepository productRepository, RestTemplate restTemplate){
      this.productRepository = productRepository;
      this.restTemplate = restTemplate;
  }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findProductByCode(String code) {
      String correlationId = UUID.randomUUID().toString();
      MyThreadLocalHolder.setCorrelationId(correlationId);
      log.info("Before CorrelationID: "+ MyThreadLocalHolder.getCorrelationId());

      Optional<Product> productOptional = productRepository.findByCode(code);
        if(productOptional.isPresent()){
            System.out.println("Feching inventory data");
            ResponseEntity<ProductInventoryResponse> itemResponseEntity =
                    restTemplate.getForEntity("http://inventory-service/api/inventory/findByProductCode/{code}",
                            ProductInventoryResponse.class,code);
            if(itemResponseEntity.getStatusCode() == HttpStatus.OK){
                Integer quantity = itemResponseEntity.getBody().getAvailableQuantity();
                System.out.println("Available quantity: " + quantity);
                productOptional.get().setInStock(quantity > 0);
            } else {
                System.out.println("Unable to get inventory level for product_code: "+ code +
                        ", StatusCode: "+ itemResponseEntity.getStatusCode());
                productOptional.get().setInStock(false);
            }
        }
        log.info("After CorrelationID: " + MyThreadLocalHolder.getCorrelationId());

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
