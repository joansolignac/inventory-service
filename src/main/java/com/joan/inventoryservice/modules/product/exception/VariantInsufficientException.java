package com.joan.inventoryservice.modules.product.exception;

import com.joan.inventoryservice.common.exception.DomainException;
import com.joan.inventoryservice.common.exception.ErrorCode;

public class VariantInsufficientException extends DomainException {
    public VariantInsufficientException() {
        super("Variant Insufficient Stock", ErrorCode.VARIANT_INSUFFICIENT);
    }
}
