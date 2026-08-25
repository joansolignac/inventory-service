package com.joan.inventoryservice.modules.product.features.variant;

import com.joan.inventoryservice.modules.product.commands.CreateVariantCommand;
import com.joan.inventoryservice.modules.product.dtos.variant.response.VariantResponse;
import com.joan.inventoryservice.modules.product.entity.Variant;
import com.joan.inventoryservice.modules.product.exception.VariantAlreadyExistsException;
import com.joan.inventoryservice.modules.product.entity.Product;
import com.joan.inventoryservice.modules.product.exception.ProductNotFoundException;
import com.joan.inventoryservice.modules.product.helper.SkuGenerator;
import com.joan.inventoryservice.modules.product.repository.VariantRepository;
import com.joan.inventoryservice.modules.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateVariant {
    private final ProductRepository productRepository;
    private final VariantRepository variantRepository;
    private final SkuGenerator skuGenerator;

    @Transactional
    public VariantResponse execute(CreateVariantCommand command) {
        Product product = findProduct(command.productId());

        String normalizedVariantName = command.variantName()
                .trim()
                .toUpperCase();

        String sku = skuGenerator.generate(product.getBaseSku(), normalizedVariantName);

        if (variantRepository.existsBySku(sku)) {
            throw new VariantAlreadyExistsException(sku);
        }

        var variant = variantRepository.save(
                Variant
                        .builder()
                        .product(product)
                        .name(normalizedVariantName)
                        .sku(sku)
                        .price(command.price())
                        .stock(command.stock())
                        .build()
        );

        return VariantResponse.from(variant);
    }

    private Product findProduct(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }
}
