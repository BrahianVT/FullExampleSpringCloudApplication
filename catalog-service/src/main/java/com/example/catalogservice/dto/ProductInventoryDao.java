package com.example.catalogservice.dto;

import lombok.Data;

@Data
public class ProductInventoryDao {
    private String productCode;
    private int availableQuantity;
}
