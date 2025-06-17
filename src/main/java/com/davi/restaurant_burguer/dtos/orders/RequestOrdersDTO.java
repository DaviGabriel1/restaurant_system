package com.davi.restaurant_burguer.dtos.orders;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;


public record RequestOrdersDTO (
        @Min(0) @JsonProperty("user_id") Long userId,
        @Min(0) @JsonProperty("address_id") Long addressId, //TODO: mudar para uuid
        String observation,
        @Min(0) @JsonProperty("payment_method") int paymentMethod,
        @Min(0) @JsonProperty("delivery_type") int deliveryType,
        @NotBlank @JsonProperty("change_for") String changeFor,
        @NotEmpty @Valid List<OrderItemDTO> itens
) {}