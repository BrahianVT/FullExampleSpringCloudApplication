package com.example.inventoryservice.services;

import com.example.inventoryservice.entities.InventoryItem;
import com.example.inventoryservice.repositories.InventoryItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
/**
 InventoryItemService to implement the business functionality
 @Author: Brahian Velazquez Tellez
 **/

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

    @Override
    public List<InventoryItem> findAllProducts(){
       return inventoryItemRepository.findAll();
    }

    @Override
    public InventoryItem saveOrUpdateProductCode(InventoryItem inventoryItem) {
       return inventoryItemRepository.save(inventoryItem);

    }

    @Override
    public void delete(Long idInventory) {
        inventoryItemRepository.deleteById(idInventory);
    }

    @Override
    public Optional<InventoryItem> findById(Long idInventory) {
        return inventoryItemRepository.findById(idInventory);
    }
}
