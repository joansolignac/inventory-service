package com.joan.inventoryservice.modules.product.exception;

import com.joan.inventoryservice.common.exception.DomainException;
import com.joan.inventoryservice.common.exception.ErrorCode;

public class VariantAlreadyExistsException extends DomainException {
    public VariantAlreadyExistsException(String sku) {
        super(String.format("Variant with SKU '%s' already exists", sku), ErrorCode.VARIANT_ALREADY_EXISTS);
    }
}
