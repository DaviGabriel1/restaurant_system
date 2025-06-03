package com.davi.restaurant_burguer.dtos.products;

import com.davi.restaurant_burguer.dtos.products.productImage.ResponseProductImageDTO;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

public record ResponseProductDTO(String uuid, String name, String description, BigDecimal price, int category, boolean isAvailable, List<ResponseProductImageDTO> images, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
}
