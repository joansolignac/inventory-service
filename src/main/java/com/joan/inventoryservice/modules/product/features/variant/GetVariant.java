package com.joan.inventoryservice.modules.product.features.variant;

import com.joan.inventoryservice.modules.product.dtos.variant.response.VariantResponse;
import com.joan.inventoryservice.modules.product.entity.Variant;
import com.joan.inventoryservice.modules.product.exception.VariantNotFoundException;
import com.joan.inventoryservice.modules.product.repository.VariantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetVariant {
    private final VariantRepository variantRepository;

    public VariantResponse execute(UUID id) {
        Variant variant = variantRepository.findById(id)
                .orElseThrow(() -> new VariantNotFoundException(id));

        return VariantResponse.from(variant);
    }
}
