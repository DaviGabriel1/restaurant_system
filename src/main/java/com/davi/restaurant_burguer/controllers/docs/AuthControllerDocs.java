package com.davi.restaurant_burguer.controllers.docs;

import com.davi.restaurant_burguer.dtos.GenericResponseDTO;
import com.davi.restaurant_burguer.dtos.auth.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthControllerDocs {
    @PostMapping("/register")
    @Operation(summary = "registra um usuário",
            description = "salva o usuário de forma inativa, após isso é enviado um SMS para entrar na conta",
            tags = {"Auth"},
            responses = {
                    @ApiResponse(
                            description = "Created",
                            responseCode = "201",
                            content = @Content(schema = @Schema(implementation = ResponseRegisterDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            }
    )
    ResponseEntity<ResponseRegisterDTO> register(@RequestBody @Valid RequestRegisterDTO requestRegisterDTO);

    @PostMapping("/login")
    @Operation(summary = "entrar na conta de um usuário",
            description = "entrar na conta com base no código enviado via SMS",
            tags = {"Auth"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ResponseLoginDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            }
    )
    ResponseEntity<ResponseLoginDTO> login(@RequestBody @Valid RequestLoginDTO requestLoginDTO);

    @PostMapping("/resend-code")
    @Operation(summary = "reenvia o código",
            description = "envia um novo código via SMS para entrar na conta",
            tags = {"Auth"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = GenericResponseDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            }
    )
    ResponseEntity<GenericResponseDTO> resendCode(@RequestBody @Valid RequestResendCode requestResendCode);
}
