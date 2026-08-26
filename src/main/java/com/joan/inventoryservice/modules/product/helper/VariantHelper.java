package com.joan.inventoryservice.modules.product.helper;

import com.joan.inventoryservice.modules.product.entity.Variant;
import com.joan.inventoryservice.modules.product.exception.VariantNotFoundException;
import com.joan.inventoryservice.modules.product.repository.VariantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class VariantHelper {
    private final VariantRepository variantRepository;

    public Variant findVariantById(UUID id) {
        return variantRepository.findById(id)
                .orElseThrow(() -> new VariantNotFoundException(id));
    }

    public int getLimitStock () {
        return 999999;
    }
}
