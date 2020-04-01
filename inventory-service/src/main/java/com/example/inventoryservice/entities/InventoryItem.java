package com.example.inventoryservice.entities;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name="inventory")
public class InventoryItem {
    @Column(name = "id_inventory")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInventory;
    @Column(name = "product_code", nullable = false, unique = true)
    private String productCode;
    @Column(name = "quantity")
    private Integer availableQuantity;

}
