package com.joan.inventoryservice.modules.product.features.variant;

import com.joan.inventoryservice.modules.product.dtos.variant.response.VariantResponse;
import com.joan.inventoryservice.modules.product.exception.VariantStockLimitExceededException;
import com.joan.inventoryservice.modules.product.helper.VariantHelper;
import com.joan.inventoryservice.modules.product.repository.VariantRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddStock {
    private final VariantRepository variantRepository;
    private final VariantHelper variantHelper;

    @Transactional
    @Caching(
            evict = {
                    @CacheEvict(value = "variantById", key = "#id"),
                    @CacheEvict(value = "variantList", allEntries = true),
                    @CacheEvict(value = "variantListByProduct", allEntries = true)
            }
    )
    public VariantResponse execute(UUID id, int quantity) {
        var variant = variantHelper.findVariantById(id);

        int newStock = variant.getStock() + quantity;

        if (newStock > variantHelper.getLimitStock()) {
            throw new VariantStockLimitExceededException(variantHelper.getLimitStock());
        }

        variant.setStock(newStock);

        variantRepository.save(variant);

        return VariantResponse.from(variant);
    }
}
