package com.example.catalogservice.feignClients;

import com.example.catalogservice.dao.ProductInventoryDao;

import com.example.catalogservice.util.InventoryClientFallBackMethod;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "inventory-service" , fallback = InventoryClientFallBackMethod.class)
public interface InventoryRestClient {
    @GetMapping("/api/inventory/findByProductCode/{code}")
    ResponseEntity<ProductInventoryDao> getProductInventory(@PathVariable String code);

}
