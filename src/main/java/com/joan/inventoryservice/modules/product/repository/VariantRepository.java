package com.joan.inventoryservice.modules.product.repository;

import com.joan.inventoryservice.modules.product.entity.Variant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VariantRepository extends JpaRepository<Variant, UUID> {
    boolean existsBySku(String sku);
    boolean existsBySkuAndIdNot(String sku, UUID id);
    Page<Variant> findAllByProductId(UUID productId, Pageable pageable);

    @Modifying
    @Query(
            "UPDATE Variant as v " +
                    "SET v.stock = v.stock - :quantity " +
                    "WHERE v.id = :id AND v.stock >= :quantity"
    )
    int reserveStock(UUID id, int quantity);
}
