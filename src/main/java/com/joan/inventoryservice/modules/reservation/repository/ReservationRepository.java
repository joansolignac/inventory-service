package com.joan.inventoryservice.modules.reservation.repository;

import com.joan.inventoryservice.modules.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
    @Modifying
    @Query(
            "UPDATE Variant as v " +
                    "SET v.stock = v.stock - :quantity " +
                    "WHERE v.id = :variantId AND v.stock >= :quantity"
    )
    int reserveVariantStock(UUID variantId, int quantity);
}
