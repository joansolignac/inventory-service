package com.joan.inventoryservice.modules.product.exception;

import com.joan.inventoryservice.common.exception.DomainException;
import com.joan.inventoryservice.common.exception.ErrorCode;

public class VariantStockLimitExceededException extends DomainException {

    public VariantStockLimitExceededException(int limitStock) {
        super(String.format("Stock cannot exceed the limit of '%d'", limitStock), ErrorCode.VARIANT_STOCK_LIMIT_EXCEEDED);
    }
}
