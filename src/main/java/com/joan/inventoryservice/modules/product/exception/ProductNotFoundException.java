package com.joan.inventoryservice.modules.product.exception;

import com.joan.inventoryservice.common.exception.DomainException;
import com.joan.inventoryservice.common.exception.ErrorCode;

import java.util.UUID;

public class ProductNotFoundException extends DomainException {
    public ProductNotFoundException(UUID id) {
        super(String.format("Product with UUID '%s' not found", id), ErrorCode.PRODUCT_NOT_FOUND);
    }
}
