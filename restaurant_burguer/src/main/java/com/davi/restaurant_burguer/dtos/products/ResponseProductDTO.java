package com.davi.restaurant_burguer.dtos.products;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record ResponseProductDTO(String uuid, String name, String description, BigDecimal price, int category, boolean isAvailable, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
}
