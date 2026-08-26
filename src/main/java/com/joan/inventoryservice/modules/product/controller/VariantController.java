package com.joan.inventoryservice.modules.product.controller;

import com.joan.inventoryservice.common.dtos.request.PaginationRequest;
import com.joan.inventoryservice.common.dtos.response.PaginationResponse;
import com.joan.inventoryservice.modules.product.commands.CreateVariantCommand;
import com.joan.inventoryservice.modules.product.commands.UpdateVariantCommand;
import com.joan.inventoryservice.modules.product.dtos.variant.request.AddStockRequest;
import com.joan.inventoryservice.modules.product.dtos.variant.request.CreateVariantRequest;
import com.joan.inventoryservice.modules.product.dtos.variant.request.ReserveStockRequest;
import com.joan.inventoryservice.modules.product.dtos.variant.request.UpdateVariantRequest;
import com.joan.inventoryservice.modules.product.dtos.variant.response.VariantResponse;
import com.joan.inventoryservice.modules.product.features.variant.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/variants")
@RequiredArgsConstructor
public class VariantController {
    private final CreateVariant createVariant;
    private final FindAllVariants findAllVariants;
    private final GetVariant getVariant;
    private final UpdateVariant updateVariant;
    private final DeleteVariant deleteVariant;
    private final AddStock addStock;
    private final ReserveStock reserveStock;

    @PostMapping()
    public ResponseEntity<VariantResponse> create(@RequestBody @Valid CreateVariantRequest body) {
        var variant = createVariant.execute(new CreateVariantCommand(
                UUID.fromString(body.productId()),
                body.variantName(),
                body.price(),
                body.stock()
        ));

        return ResponseEntity.ok(variant);
    }

    @PostMapping("/{id}/add-stock")
    public ResponseEntity<VariantResponse> addStock(
            @PathVariable String id,
            @RequestBody @Valid AddStockRequest body
            ) {
        var response = addStock.execute(
                UUID.fromString(id),
                body.quantity()
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/reserve-stock")
    public ResponseEntity<Void> reserveStock(
            @PathVariable String id,
            @RequestBody @Valid ReserveStockRequest body
    ) {
        reserveStock.execute(UUID.fromString(id), body.quantity());

        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public ResponseEntity<PaginationResponse<VariantResponse>> findAll(@ModelAttribute @Valid PaginationRequest request) {
        var response = findAllVariants.execute(request.toPageable());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VariantResponse> findById(@PathVariable String id) {
        return ResponseEntity.ok(
                getVariant.execute(UUID.fromString(id))
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<VariantResponse> update(
            @PathVariable String id,
            @RequestBody @Valid UpdateVariantRequest body
    ) {
        var variant = updateVariant.execute(new UpdateVariantCommand(
                UUID.fromString(id),
                body.productId() != null ? UUID.fromString(body.productId()) : null,
                body.variantName(),
                body.price(),
                body.stock()
        ));

        return ResponseEntity.ok(variant);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        deleteVariant.execute(UUID.fromString(id));

        return ResponseEntity
                .noContent()
                .build();
    }
}
