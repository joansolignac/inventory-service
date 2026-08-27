package com.joan.inventoryservice.modules.reservation.dtos.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record CreateReservationRequest(
        @NotNull UUID reservationId,
        @Positive @Max(999999) int quantity
) {
}
