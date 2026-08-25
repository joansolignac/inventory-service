package com.joan.inventoryservice.common.dtos.response;

import java.time.Instant;
import java.util.Map;

public record ErrorResponse(
        String path,
        Instant timestamp,
        String errorCode,
        String message,
        Map<String, String> fieldErrors
) {

    public static ErrorResponse of(String path, String errorCode, String message) {
        return new ErrorResponse(
                path,
                Instant.now(),
                errorCode,
                message,
                null
        );
    }

    public static ErrorResponse of (String path, String errorCode, String message, Map<String, String> fieldErrors) {
        return new ErrorResponse(
                path,
                Instant.now(),
                errorCode,
                message,
                fieldErrors
        );
    }


}
