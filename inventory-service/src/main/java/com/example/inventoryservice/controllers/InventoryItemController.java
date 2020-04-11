package com.example.inventoryservice.controllers;

import com.example.inventoryservice.entities.InventoryItem;
import com.example.inventoryservice.services.InterfaceInventoryItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 Class to create the RestFul Api of the inventory items
 @author: Brahian Velazquez Tellez
 **/
@RestController
@Slf4j
@RequestMapping("api/inventory")
public class InventoryItemController {
    private  final InterfaceInventoryItem inventoryItemService;

    @Autowired
    public InventoryItemController(InterfaceInventoryItem inventoryItemService){
        this.inventoryItemService = inventoryItemService;
    }

    @GetMapping("findByProductCode/{productCode}")
    public ResponseEntity<InventoryItem> findInventoryByProductCode(@PathVariable("productCode") String productCode){
        log.info("Finding inventory for product code : " + productCode);

        Optional<InventoryItem> inventoryItem = inventoryItemService.findByProductCode(productCode);
        if(inventoryItem.isPresent())
            return new ResponseEntity(inventoryItem.get(), HttpStatus.OK);
        else
            return  new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("findAllProducts")
    public ResponseEntity<List<InventoryItem>> findAllProducts(){
        log.info("Looking for all Products...");
        List<InventoryItem> listProducts = inventoryItemService.findAllProducts();
        if(listProducts != null && listProducts.size() > 0)
            return new ResponseEntity<>(listProducts, HttpStatus.OK);
         else
             return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("saveProductCode")
    public ResponseEntity<InventoryItem> saveInventory(@RequestBody InventoryItem inventoryItem){
        log.info("Saving Inventory...");
        InventoryItem savedOrUpdatedInventory = inventoryItemService.saveOrUpdateProductCode(inventoryItem);
        if(savedOrUpdatedInventory != null)
            return new ResponseEntity<>(savedOrUpdatedInventory,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("updateProductCode")
    public ResponseEntity<InventoryItem> updateInventory(@RequestBody InventoryItem inventoryItem){
        log.info("Updating Inventory...");
        InventoryItem savedOrUpdatedInventory = inventoryItemService.saveOrUpdateProductCode(inventoryItem);
        if(savedOrUpdatedInventory != null)
            return new ResponseEntity<>(savedOrUpdatedInventory,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("deleteInventory/{idInventory}")
    public ResponseEntity deleteInventory(@PathVariable("idInventory") Long idInventory){
        log.info("Deleting Inventory...");
        if(!inventoryItemService.findById(idInventory).isPresent()){
            log.info(" the inventory with id " + " is not found");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else
            return new ResponseEntity<>(HttpStatus.OK);

    }
}
