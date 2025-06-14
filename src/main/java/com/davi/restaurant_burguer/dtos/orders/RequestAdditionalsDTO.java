package com.davi.restaurant_burguer.dtos.orders;

import jakarta.validation.constraints.Min;

public record RequestAdditionalsDTO(
        @Min(0) Long additionalId,
        @Min(0) int quantity
) {
}
