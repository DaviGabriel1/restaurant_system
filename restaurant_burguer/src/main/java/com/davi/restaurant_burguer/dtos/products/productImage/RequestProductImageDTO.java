package com.davi.restaurant_burguer.dtos.products.productImage;

import org.springframework.web.multipart.MultipartFile;

public record RequestProductImageDTO(String uuid, MultipartFile file, boolean isThumbnail) {
}
