package com.davi.restaurant_burguer.controllers.docs;

import com.davi.restaurant_burguer.dtos.GenericResponseDTO;
import com.davi.restaurant_burguer.dtos.products.RequestProductDTO;
import com.davi.restaurant_burguer.dtos.products.ResponseProductDTO;
import com.davi.restaurant_burguer.dtos.products.additional.RequestProductAdditionalDTO;
import com.davi.restaurant_burguer.dtos.products.additional.ResponseProductAdditionalDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductControllerDocs {
    @Operation(summary = "busca um produto pelo seu uuid",
            description = "busca o produto com base no uuid",
            tags = {"Products"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ResponseProductDTO.class))
                    ),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            }
    )
    ResponseEntity<ResponseProductDTO> getProductByUuid(@PathVariable String uuid);

    @Operation(summary = "busca todos os produtos",
            description = "busca todos os produtos com deletedAt null",
            tags = {"Products"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            array = @ArraySchema(schema = @Schema(implementation = ResponseProductDTO.class))
                                    )
                            }),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    ResponseEntity<List<ResponseProductDTO>> getAllProducts();

    @Operation(summary = "salva um produto",
            description = "salva o produto depois da validado",
            tags = {"Products"},
            responses = {
                    @ApiResponse(
                            description = "Created",
                            responseCode = "201",
                            content = @Content(schema = @Schema(implementation = ResponseProductDTO.class))
                    ),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            }
    )
    ResponseEntity<ResponseProductDTO> saveProduct(@RequestBody @Valid RequestProductDTO requestProductDTO);

    @Operation(summary = "atualiza um produto",
            description = "atualiza o produto com base no uuid adicionado no path",
            tags = {"Products"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ResponseProductDTO.class))
                    ),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            }
    )
    ResponseEntity<ResponseProductDTO> updateProduct(@RequestBody @Valid RequestProductDTO requestProductDTO, @PathVariable String uuid);

    @Operation(summary = "deleta um produto",
            description = "deleta o produto com base no uuid adicionado no path",
            tags = {"Products"},
            responses = {
                    @ApiResponse(
                            description = "No Content",
                            responseCode = "204",
                            content = @Content(schema = @Schema(implementation = ResponseProductDTO.class))
                    ),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            }
    )
    ResponseEntity deleteProduct(@PathVariable String uuid);

    @Operation(summary = "salva a imagem de um produto",
            description = "salva a imagem do produto em um bucket S3 na AWS",
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
    ResponseEntity<GenericResponseDTO> uploadProductImage(@RequestParam(required = false, defaultValue = "false") boolean isThumbnail, @PathVariable String productUuid, @RequestParam("file") MultipartFile file) throws Exception;

    @Operation(summary = "salva o adicional de um produto",
            description = "salva o adicional de um produto com base no uuid adicionado no path e o body",
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
    ResponseEntity<GenericResponseDTO> saveProductAdditional(@PathVariable String productUuid, @RequestBody RequestProductAdditionalDTO productAdditional);

    @Operation(summary = "busca um adicional de um produto com base no id",
            description = "busca o produto com base no uuid adicionado no path",
            tags = {"Products"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ResponseProductAdditionalDTO.class))
                    ),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            }
    )
    ResponseEntity<ResponseProductAdditionalDTO> findOneProductAdditional(@PathVariable Long productAdditionalId);

    @Operation(summary = "busca todos os adicionais de um produto especifico",
            description = "busca os adicionais com base no UUID do produto",
            tags = {"Products"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ResponseProductAdditionalDTO.class))
                    ),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            }
    )
    ResponseEntity<List<ResponseProductAdditionalDTO>> findAllProductAdditional(@PathVariable String productUuid);

    @Operation(summary = "atualiza um adicional de produto",
            description = "atualiza o adicional com base no uuid adicionado no path",
            tags = {"Products"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ResponseProductAdditionalDTO.class))
                    ),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            }
    )
    ResponseEntity<ResponseProductAdditionalDTO> updateProductAdditional(@PathVariable Long additionalId, @RequestBody RequestProductAdditionalDTO requestProductAdditionalDTO);

    @Operation(summary = "deleta um adicional de produto",
            description = "deleta o adicional de produto com base no uuid adicionado no path",
            tags = {"Products"},
            responses = {
                    @ApiResponse(
                            description = "No Content",
                            responseCode = "204",
                            content = @Content(schema = @Schema(implementation = ResponseProductAdditionalDTO.class))
                    ),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            }
    )
    ResponseEntity deleteProductAdditional(@PathVariable Long additionalId);
}
