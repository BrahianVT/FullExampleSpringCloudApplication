package com.example.catalogservice.util;

import com.example.catalogservice.dao.ProductInventoryDao;
import com.example.catalogservice.feignClients.InventoryRestClient;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class InventoryClientFallBackMethod implements InventoryRestClient {

    @Override
    public ResponseEntity<ProductInventoryDao> getProductInventory(String code) {
        log.info("FallBack method for InventoryRestClient getProductInventory  code " + code);
        ProductInventoryDao fallbackProductInventory = new ProductInventoryDao();
        fallbackProductInventory.setAvailableQuantity(0);
        return new ResponseEntity<ProductInventoryDao>(fallbackProductInventory, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}

