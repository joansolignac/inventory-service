package com.joan.inventoryservice.modules.product.features.variant;

import com.joan.inventoryservice.modules.product.commands.UpdateVariantCommand;
import com.joan.inventoryservice.modules.product.dtos.variant.response.VariantResponse;
import com.joan.inventoryservice.modules.product.entity.Product;
import com.joan.inventoryservice.modules.product.entity.Variant;
import com.joan.inventoryservice.modules.product.exception.VariantAlreadyExistsException;
import com.joan.inventoryservice.modules.product.exception.ProductNotFoundException;
import com.joan.inventoryservice.modules.product.exception.VariantNotFoundException;
import com.joan.inventoryservice.modules.product.helper.SkuGenerator;
import com.joan.inventoryservice.modules.product.repository.VariantRepository;
import com.joan.inventoryservice.modules.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateVariant {
    private final VariantRepository variantRepository;
    private final ProductRepository productRepository;
    private final SkuGenerator skuGenerator;

    @Transactional
    public VariantResponse execute(UpdateVariantCommand command) {
        Variant variant = findVariant(command.id());

        if (command.productId() != null) {
            Product product = findProduct(command.productId());

            variant.setProduct(product);

            variant.setSku(
                    skuGenerator.generate(
                            product.getBaseSku(),
                            variant.getName()
                    )
            );
        }

        if (command.variantName() != null) {
            String normalizedVariantName = command.variantName().trim().toUpperCase();

            variant.setName(normalizedVariantName);

            variant.setSku(
                    skuGenerator.generate(
                            variant.getProduct().getBaseSku(),
                            normalizedVariantName
                    )
            );
        }

        if (command.price() != null) {
            variant.setPrice(command.price());
        }

        if (command.stock() != null) {
            variant.setStock(command.stock());
        }

        return VariantResponse.from(variantRepository.save(variant));
    }

    private Variant findVariant(UUID id) {
        return variantRepository.findById(id)
                .orElseThrow(() -> new VariantNotFoundException(id));
    }

    private Product findProduct(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }
}
