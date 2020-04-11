package com.example.catalogservice.entities;

import lombok.Data;

import javax.persistence.*;

/**
 Entity for the products table, here there is
 the @Data from Lombok to create automatically
 getters and setters.
 @author: Brahian Velazquez Tellez
 **/
@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;
    @Column(nullable = false)
    private String name;
    private String description;
    private double price;

    @Transient
    private boolean inStock;
    @Transient
    private Integer quantity = 0;
}
