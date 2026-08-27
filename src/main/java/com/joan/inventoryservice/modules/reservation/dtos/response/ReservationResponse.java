package com.joan.inventoryservice.modules.reservation.dtos.response;

import com.joan.inventoryservice.modules.reservation.entity.Reservation;

public record ReservationResponse(
        String reservationId,
        String variantId,
        Integer quantity,
        String status
) {
    public static ReservationResponse from(Reservation reservation) {
        return new ReservationResponse(
                reservation.getId().toString(),
                reservation.getVariant().getId().toString(),
                reservation.getQuantity(),
                reservation.getStatus().name()
        );
    }
}
