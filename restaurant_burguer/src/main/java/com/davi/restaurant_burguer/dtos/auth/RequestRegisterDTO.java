package com.davi.restaurant_burguer.dtos.auth;

import jakarta.validation.constraints.NotBlank;

public record RequestRegisterDTO(
        @NotBlank(message = "o campo nome é obrigatório") String name,
        @NotBlank(message = "o campo telefone é obrigatório") String phone
){
}
