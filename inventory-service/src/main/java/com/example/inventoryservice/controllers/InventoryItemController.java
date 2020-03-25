package com.example.inventoryservice.controllers;

import com.example.inventoryservice.entities.InventoryItem;
import com.example.inventoryservice.services.InterfaceInventoryItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@Slf4j
public class InventoryItemController {
    private  final InterfaceInventoryItem inventoryItemService;

    @Autowired
    public InventoryItemController(InterfaceInventoryItem inventoryItemService){
        this.inventoryItemService = inventoryItemService;
    }

    @GetMapping("/api/inventory/{productCode}")
    public ResponseEntity<InventoryItem> findInventoryByProductCode(@PathVariable("productCode") String productCode){
        System.out.println("Finding inventory for product code : " + productCode);

        Optional<InventoryItem> inventoryItem = inventoryItemService.findByProductCode(productCode);
        if(inventoryItem.isPresent())
            return new ResponseEntity(inventoryItem.get(), HttpStatus.OK);
        else
            return  new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
