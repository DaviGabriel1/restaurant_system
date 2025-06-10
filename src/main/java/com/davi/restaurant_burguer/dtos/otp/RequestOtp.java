package com.davi.restaurant_burguer.dtos.otp;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RequestOtp(
        @NotBlank
        @Size(min = 3, max = 20, message = "o numero de telefone deve ter no máximo 20 digitos")
        String phone,
        @NotBlank
        String name) {
}
