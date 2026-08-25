package com.joan.inventoryservice.modules.product.features.product;

import com.joan.inventoryservice.modules.category.entity.Category;
import com.joan.inventoryservice.modules.category.helper.CategoryHelper;
import com.joan.inventoryservice.modules.product.dtos.product.response.ProductResponse;
import com.joan.inventoryservice.modules.product.entity.Product;
import com.joan.inventoryservice.modules.product.exception.ProductAlreadyExistsException;
import com.joan.inventoryservice.modules.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateProduct {
    private final ProductRepository productRepository;
    private final CategoryHelper categoryHelper;

    @Transactional
    public ProductResponse execute(UUID categoryId, String productName) {
        String normalizedProductName = productName.toUpperCase();

        Category category = categoryHelper.findCategoryById(categoryId);

        if (productRepository.existsByName(normalizedProductName)) {
            throw new ProductAlreadyExistsException(normalizedProductName);
        }

        String baseSku = normalizedProductName
                .trim()
                .replaceAll("\\s+", "-");

        var product = productRepository.save(
                Product
                        .builder()
                        .category(category)
                        .name(normalizedProductName)
                        .baseSku(baseSku)
                        .build()

        );

        return ProductResponse.from(product);
    }
}
