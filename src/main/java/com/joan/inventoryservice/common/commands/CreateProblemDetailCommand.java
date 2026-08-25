package com.joan.inventoryservice.common.commands;

import org.springframework.http.HttpStatus;

import java.net.URI;

public record CreateProblemDetailCommand(
        HttpStatus httpStatus,
        String title,
        String detail
) {
}
