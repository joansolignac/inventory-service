package com.joan.inventoryservice.modules.product.features.product;

import com.joan.inventoryservice.modules.product.entity.Product;
import com.joan.inventoryservice.modules.product.exception.ProductNotFoundException;
import com.joan.inventoryservice.modules.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteProduct {
    private final ProductRepository productRepository;

    public void execute(UUID id) {
        Product product = findProduct(id);

        productRepository.delete(product);
    }

    private Product findProduct(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }
}
