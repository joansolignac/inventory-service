package com.joan.inventoryservice.modules.reservation.controller;

import com.joan.inventoryservice.modules.reservation.dtos.request.CreateReservationRequest;
import com.joan.inventoryservice.modules.reservation.dtos.response.ReservationResponse;
import com.joan.inventoryservice.modules.reservation.features.CreateReservation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/variants")
@RequiredArgsConstructor
public class ReservationController {
    private final CreateReservation createReservation;

    @PostMapping("/{variantId}/reserve")
    public ResponseEntity<ReservationResponse> reserve(
            @PathVariable UUID variantId,
            @RequestBody @Valid CreateReservationRequest body
    ) {
        var response = createReservation.execute(
                variantId,
                body.reservationId(),
                body.quantity()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
