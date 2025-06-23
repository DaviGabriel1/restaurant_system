package com.davi.restaurant_burguer.dtos.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record RequestLoginDTO(
        @NotBlank(message = "o código é obrigatório")
        @Pattern(regexp = "^[0-9]+$", message = "O código deve conter apenas números")
        String code,
        @NotBlank(message = "o número de telefone é obrigatório")
        @Pattern(regexp = "^[0-9]+$", message = "O telefone deve conter apenas números")
        String phone
) { //TODO: adicionar um captcha? add um rate limiting na api
}
