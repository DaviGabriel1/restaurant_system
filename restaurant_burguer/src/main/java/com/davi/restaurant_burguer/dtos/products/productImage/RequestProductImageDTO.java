package com.davi.restaurant_burguer.dtos.products.productImage;

import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public record RequestProductImageDTO(@NotBlank(message = "o uuid é obrigatório") String uuid,
                                     @NotBlank(message = "a imagem é obrigatória") MultipartFile file,
                                     boolean isThumbnail) {
}
