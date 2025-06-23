package com.davi.restaurant_burguer.controllers.docs;

import com.davi.restaurant_burguer.dtos.GenericResponseDTO;
import com.davi.restaurant_burguer.dtos.orders.RequestOrdersDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface OrderControllerDocs {
    @Operation(summary = "gerador de comanda",
            description = "gera uma comanda com base no corpo da requisição," +
                    " salva no banco de dados e envia a comanda via SQS para aplicação base",
            tags = {"Products"},
            responses = {
                    @ApiResponse(
                            description = "Created",
                            responseCode = "201",
                            content = @Content(schema = @Schema(implementation = GenericResponseDTO.class))
                    ),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            }
    )
    ResponseEntity<GenericResponseDTO> generateOrder(@RequestBody @Valid RequestOrdersDTO ordersDto);
}
