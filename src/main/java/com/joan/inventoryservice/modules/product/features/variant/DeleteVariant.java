package com.joan.inventoryservice.modules.product.features.variant;

import com.joan.inventoryservice.modules.product.entity.Variant;
import com.joan.inventoryservice.modules.product.helper.VariantHelper;
import com.joan.inventoryservice.modules.product.repository.VariantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteVariant {
    private final VariantRepository variantRepository;
    private final VariantHelper variantHelper;

    public void execute(UUID id) {
        Variant variant = variantHelper.findVariantById(id);

        variantRepository.delete(variant);
    }
}
