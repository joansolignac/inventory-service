package com.joan.inventoryservice.modules.category.exception;

import com.joan.inventoryservice.common.exception.DomainException;
import com.joan.inventoryservice.common.exception.ErrorCode;

import java.util.UUID;

public class CategoryNotFoundException extends DomainException {
    public CategoryNotFoundException(UUID categoryName) {
        super(String.format("Category with UUID '%s' not found.", categoryName), ErrorCode.CATEGORY_NOT_FOUND);
    }
}
