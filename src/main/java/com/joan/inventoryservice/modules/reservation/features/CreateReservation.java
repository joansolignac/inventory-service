package com.joan.inventoryservice.modules.reservation.features;

import com.joan.inventoryservice.modules.product.exception.VariantInsufficientException;
import com.joan.inventoryservice.modules.product.helper.VariantHelper;
import com.joan.inventoryservice.modules.reservation.dtos.response.ReservationResponse;
import com.joan.inventoryservice.modules.reservation.entity.Reservation;
import com.joan.inventoryservice.modules.reservation.exception.ReservationAlreadyExistsException;
import com.joan.inventoryservice.modules.reservation.repository.ReservationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateReservation {
    private final ReservationRepository reservationRepository;
    private final VariantHelper variantHelper;

    @Transactional
    @Caching(
            evict = {
                    @CacheEvict(value = "variantById", key = "#variantId"),
                    @CacheEvict(value = "variantList", allEntries = true),
                    @CacheEvict(value = "variantListByProduct", allEntries = true)
            }
    )
    public ReservationResponse execute(UUID variantId, UUID reservationId, int quantity) {
        if (reservationRepository.existsById(reservationId)) {
            throw new ReservationAlreadyExistsException(reservationId);
        }

        var variant = variantHelper.findVariantById(variantId);
        var reservation = Reservation.builder()
                .id(reservationId)
                .variant(variant)
                .quantity(quantity)
                .build();

        reservationRepository.saveAndFlush(reservation);

        int rowsAffected = reservationRepository.reserveVariantStock(variantId, quantity);

        if (rowsAffected <= 0) {
            throw new VariantInsufficientException();
        }

        return ReservationResponse.from(reservation);
    }
}
