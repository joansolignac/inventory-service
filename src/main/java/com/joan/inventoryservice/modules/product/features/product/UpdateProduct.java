package com.joan.inventoryservice.modules.product.features.product;

import com.joan.inventoryservice.modules.category.helper.CategoryHelper;
import com.joan.inventoryservice.modules.product.dtos.product.response.ProductResponse;
import com.joan.inventoryservice.modules.product.entity.Product;
import com.joan.inventoryservice.modules.product.exception.ProductAlreadyExistsException;
import com.joan.inventoryservice.modules.product.exception.ProductNotFoundException;
import com.joan.inventoryservice.modules.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateProduct {
    private final ProductRepository productRepository;
    private final CategoryHelper categoryHelper;

    @Transactional
    public ProductResponse execute(UUID id, UUID categoryId, String productName) {
        Product product = findProduct(id);

        if (categoryId != null) {
            product.setCategory(categoryHelper.findCategoryById(categoryId));
        }

        if (productName != null) {
            String normalizedProductName = productName.toUpperCase();

            if (productRepository.existsByNameAndIdNot(normalizedProductName, id)) {
                throw new ProductAlreadyExistsException(productName);
            }

            product.setName(normalizedProductName);

            String baseSku = normalizedProductName
                    .trim()
                    .replaceAll("\\s+", "-");

            product.setBaseSku(baseSku);
        }

        return ProductResponse.from(productRepository.save(product));
    }

    private Product findProduct(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }
}
