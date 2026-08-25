package com.joan.inventoryservice.modules.product.features.product;

import com.joan.inventoryservice.modules.product.dtos.product.response.ProductResponse;
import com.joan.inventoryservice.modules.product.entity.Product;
import com.joan.inventoryservice.modules.product.exception.ProductNotFoundException;
import com.joan.inventoryservice.modules.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetProduct {
    private final ProductRepository productRepository;

    public ProductResponse execute(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        return ProductResponse.from(product);
    }
}
