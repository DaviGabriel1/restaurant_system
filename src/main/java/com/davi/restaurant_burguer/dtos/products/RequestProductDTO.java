package com.davi.restaurant_burguer.dtos.products;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.OffsetDateTime;

public record RequestProductDTO(@NotBlank(message = "o nome do produto é obrigatório") String name,
                                @NotBlank(message = "a descrição do produto é obrigatória") String description,
                                @NotBlank(message = "o preço do produto é obrigatório") String price,
                                @NotNull @PositiveOrZero (message = "o campo categoria é obrigatório") int category,
                                boolean isAvailable,
                                OffsetDateTime createdAt,
                                OffsetDateTime updatedAt
) {}
