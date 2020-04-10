package com.example.catalogservice.repositories;

import com.example.catalogservice.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 Here I defined a repository to implement CRUD functionalities
 and I also defined three query methods to search in the database in
 base on the entity attributes.
 @Author: Brahian Velazquez Tellez
 **/
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByCode(String code);
    List<Product> findByName(String name);
    List<Product> findByDescription(String description);
}
