package com.example.catalogservice.dao;

import lombok.Data;
/**
 It is a Dao to store information from the inventory-service
 @author: Brahian Velazquez Tellez
 **/
@Data
public class ProductInventoryDao {
    private String productCode;
    private int availableQuantity;
}
