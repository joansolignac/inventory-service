package com.joan.inventoryservice.modules.product.exception;

import com.joan.inventoryservice.common.exception.DomainException;
import com.joan.inventoryservice.common.exception.ErrorCode;

public class ProductAlreadyExistsException extends DomainException {
    public ProductAlreadyExistsException(String productName) {
        super(String.format("Product '%s' already exists", productName), ErrorCode.PRODUCT_ALREADY_EXISTS);
    }
}
