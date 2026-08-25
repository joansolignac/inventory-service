package com.joan.inventoryservice.common.exception;

import lombok.Getter;

public abstract class DomainException extends RuntimeException {
    @Getter
    private final ErrorCode errorCode;

    protected DomainException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
