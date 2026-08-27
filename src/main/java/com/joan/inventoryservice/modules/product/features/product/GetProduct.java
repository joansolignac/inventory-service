package com.joan.inventoryservice.modules.product.features.product;

import com.joan.inventoryservice.modules.product.dtos.product.response.ProductResponse;
import com.joan.inventoryservice.modules.product.entity.Product;
import com.joan.inventoryservice.modules.product.helper.ProductHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetProduct {
    private final ProductHelper productHelper;

    @Cacheable(value = "productById", key = "#id")
    public ProductResponse execute(UUID id) {
        Product product = productHelper.findProductById(id);

        return ProductResponse.from(product);
    }
}
