package com.davi.restaurant_burguer.dtos.products.additional;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record ResponseProductAdditionalDTO(
        Long id,
        @JsonProperty("product_uuid") String productUuid,
        String name,
        String description,
        BigDecimal price,
        @JsonProperty("max_quantity") int maxQuantity,
        @Positive boolean required,
        @JsonProperty("created_at") OffsetDateTime createdAt,
        @JsonProperty("updated_at") OffsetDateTime updatedAt
) {}