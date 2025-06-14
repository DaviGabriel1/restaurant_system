package com.davi.restaurant_burguer.dtos.orders;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;


public record RequestOrdersDTO (
        @Min(0) Long userId,
        @Min(0) Long addressId, //TODO: mudar para uuid
        String observation,
        @Min(0) int paymentMethod,
        @Min(0) int deliveryType,
        @NotBlank String changeFor,
        @NotEmpty @Valid List<OrderItemDTO> itens
) {}