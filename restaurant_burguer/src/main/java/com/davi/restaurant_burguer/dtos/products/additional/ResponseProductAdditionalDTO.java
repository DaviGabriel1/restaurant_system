package com.davi.restaurant_burguer.dtos.products.additional;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record ResponseProductAdditionalDTO(Long id, String productUuid, String name, String description, BigDecimal price, int maxQuantity, boolean required, OffsetDateTime createdAt, OffsetDateTime updatedAt) {}