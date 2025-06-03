package com.davi.restaurant_burguer.dtos.auth;

import jakarta.validation.constraints.NotBlank;

public record RequestLoginDTO(
        @NotBlank(message = "o código é obrigatório") String code,
        @NotBlank(message = "o número de telefone é obrigatório") String phone
) { //TODO: adicionar um captcha? add um rate limiting na api
}
