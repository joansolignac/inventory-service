package com.joan.inventoryservice.modules.product.features.product;

import com.joan.inventoryservice.modules.product.entity.Product;
import com.joan.inventoryservice.modules.product.helper.ProductHelper;
import com.joan.inventoryservice.modules.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteProduct {
    private final ProductRepository productRepository;
    private final ProductHelper productHelper;

    public void execute(UUID id) {
        Product product = productHelper.findProductById(id);

        productRepository.delete(product);
    }
}
