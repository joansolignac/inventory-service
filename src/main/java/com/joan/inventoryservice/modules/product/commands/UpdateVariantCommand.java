package com.joan.inventoryservice.modules.product.commands;

import java.math.BigDecimal;
import java.util.UUID;

public record UpdateVariantCommand(
        UUID id,
        UUID productId,
        String variantName,
        BigDecimal price,
        Integer stock
) {
}
