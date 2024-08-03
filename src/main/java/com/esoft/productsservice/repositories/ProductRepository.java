package com.esoft.productsservice.repositories;

import com.esoft.productsservice.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String> {
    Optional<Product> findById(String id);
//    Product findByIdOrTitle(String id, String title);
Product findByIdOrTitle(String id, String title);
}
