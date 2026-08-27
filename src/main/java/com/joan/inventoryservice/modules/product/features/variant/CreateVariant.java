package com.joan.inventoryservice.modules.product.features.variant;

import com.joan.inventoryservice.modules.product.commands.CreateVariantCommand;
import com.joan.inventoryservice.modules.product.dtos.variant.response.VariantResponse;
import com.joan.inventoryservice.modules.product.entity.Variant;
import com.joan.inventoryservice.modules.product.exception.VariantAlreadyExistsException;
import com.joan.inventoryservice.modules.product.entity.Product;
import com.joan.inventoryservice.modules.product.helper.ProductHelper;
import com.joan.inventoryservice.modules.product.helper.SkuGenerator;
import com.joan.inventoryservice.modules.product.repository.VariantRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateVariant {
    private final VariantRepository variantRepository;
    private final SkuGenerator skuGenerator;
    private final ProductHelper productHelper;

    @Transactional
    @CacheEvict(value = {"variantList", "variantListByProduct"}, allEntries = true)
    public VariantResponse execute(CreateVariantCommand command) {
        Product product = productHelper.findProductById(command.productId());

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
}
