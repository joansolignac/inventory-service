package com.joan.inventoryservice.modules.category.exception;

import com.joan.inventoryservice.common.exception.DomainException;
import com.joan.inventoryservice.common.exception.ErrorCode;

public class CategoryAlreadyExistsException extends DomainException {
    public CategoryAlreadyExistsException(String categoryName) {
        super(String.format("Category '%s' already exists.", categoryName), ErrorCode.CATEGORY_ALREADY_EXISTS);
    }
}
