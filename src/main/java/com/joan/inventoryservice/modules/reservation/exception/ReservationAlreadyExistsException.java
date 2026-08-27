package com.joan.inventoryservice.modules.reservation.exception;

import com.joan.inventoryservice.common.exception.DomainException;
import com.joan.inventoryservice.common.exception.ErrorCode;

import java.util.UUID;

public class ReservationAlreadyExistsException extends DomainException {
    public ReservationAlreadyExistsException(UUID reservationId) {
        super(
                String.format("Reservation with ID '%s' already exists", reservationId),
                ErrorCode.RESERVATION_ALREADY_EXISTS
        );
    }
}
