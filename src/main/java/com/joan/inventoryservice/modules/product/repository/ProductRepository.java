package com.joan.inventoryservice.modules.product.repository;

import com.joan.inventoryservice.modules.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    boolean existsByName(String productName);
    boolean existsByNameAndIdNot(String productName, UUID id);
}
