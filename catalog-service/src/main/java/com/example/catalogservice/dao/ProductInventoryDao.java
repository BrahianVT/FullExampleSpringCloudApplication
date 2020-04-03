package com.example.catalogservice.dao;

import lombok.Data;

@Data
public class ProductInventoryDao {
    private String productCode;
    private int availableQuantity;
}
