package com.poula.sales_management.repository;

import com.poula.sales_management.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    Optional<Product> findProductByName(String name);
}
