package com.joan.inventoryservice.modules.product.exception;

import com.joan.inventoryservice.common.exception.DomainException;
import com.joan.inventoryservice.common.exception.ErrorCode;

import java.util.UUID;

public class VariantNotFoundException extends DomainException {
    public VariantNotFoundException(UUID id) {
        super(String.format("Variant with UUID '%s' not found", id), ErrorCode.VARIANT_NOT_FOUND);
    }
}
