package com.joan.inventoryservice.modules.product.features.variant;

import com.joan.inventoryservice.modules.product.entity.Variant;
import com.joan.inventoryservice.modules.product.exception.VariantNotFoundException;
import com.joan.inventoryservice.modules.product.repository.VariantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteVariant {
    private final VariantRepository variantRepository;

    public void execute(UUID id) {
        Variant variant = findVariant(id);

        variantRepository.delete(variant);
    }

    private Variant findVariant(UUID id) {
        return variantRepository.findById(id)
                .orElseThrow(() -> new VariantNotFoundException(id));
    }
}
