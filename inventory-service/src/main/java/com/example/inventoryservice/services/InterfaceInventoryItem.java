package com.example.inventoryservice.services;

import com.example.inventoryservice.entities.InventoryItem;

import java.util.Optional;

public interface InterfaceInventoryItem {
    Optional<InventoryItem> findByProductCode (String productCode);
}
