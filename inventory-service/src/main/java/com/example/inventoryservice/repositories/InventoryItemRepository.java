package com.example.inventoryservice.repositories;

import com.example.inventoryservice.entities.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 It is the repository that implements the JPA repository
 to implement CRUD functionalities
 @author: Brahian Velazquez Tellez
 **/
@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem,Long> {
    Optional<InventoryItem> findByProductCode(String productCode);
}
