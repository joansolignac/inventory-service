package com.joan.inventoryservice.modules.product.commands;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateVariantCommand(
        UUID productId,
        String variantName,
        BigDecimal price,
        Integer stock
) {
}
