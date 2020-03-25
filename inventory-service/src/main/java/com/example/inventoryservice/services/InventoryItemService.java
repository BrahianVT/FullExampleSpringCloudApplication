package com.example.inventoryservice.services;

import com.example.inventoryservice.entities.InventoryItem;
import com.example.inventoryservice.repositories.InventoryItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@Slf4j
public class InventoryItemService implements InterfaceInventoryItem {
   private final InventoryItemRepository inventoryItemRepository;

   @Autowired
   public InventoryItemService(InventoryItemRepository inventoryItemRepository){
       this.inventoryItemRepository = inventoryItemRepository;
   }
    @Override
    public Optional<InventoryItem> findByProductCode(String productCode) {
        return inventoryItemRepository.findByProductCode(productCode);
    }
}
