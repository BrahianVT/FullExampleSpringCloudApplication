package com.example.inventoryservice.services;

import com.example.inventoryservice.entities.InventoryItem;

import java.util.List;
import java.util.Optional;

public interface InterfaceInventoryItem {
    Optional<InventoryItem> findByProductCode (String productCode);
    List<InventoryItem> findAllProducts();
    InventoryItem saveOrUpdateProductCode(InventoryItem inventoryItem);
    void delete(Long idInventory);
    Optional<InventoryItem> findById(Long idInventory);
}
