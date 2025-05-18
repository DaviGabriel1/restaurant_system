package com.davi.restaurant_burguer.controllers;

import com.davi.restaurant_burguer.dtos.products.RequestProductDTO;
import com.davi.restaurant_burguer.dtos.products.ResponseProductDTO;
import com.davi.restaurant_burguer.dtos.products.productImage.RequestProductImageDTO;
import com.davi.restaurant_burguer.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("{uuid}")
    public ResponseEntity<ResponseProductDTO> getProductByUuid(@PathVariable String uuid){
        return ResponseEntity.ok(this.productService.getProductByUuid(uuid));
    }

    @GetMapping
    public ResponseEntity<List<ResponseProductDTO>> getAllProducts(){
        return ResponseEntity.ok(this.productService.getAllProducts());
    }

    @PostMapping
    public ResponseEntity<ResponseProductDTO> saveProduct(@RequestBody RequestProductDTO requestProductDTO){
        ResponseProductDTO productDTO = this.productService.saveProduct(requestProductDTO);
        return ResponseEntity.status(201).body(productDTO);
    }

    @PutMapping("{uuid}")
    public ResponseEntity<ResponseProductDTO> updateProduct(@RequestBody RequestProductDTO requestProductDTO, @PathVariable String uuid){
        return ResponseEntity.ok(this.productService.updateProduct(requestProductDTO, uuid));
    }

    @DeleteMapping("{uuid}")
    public ResponseEntity deleteProduct(@PathVariable String uuid){
        this.productService.deleteProduct(uuid);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/upload/{productUuid}")
    public ResponseEntity uploadProductImage(@RequestParam(required = false,defaultValue = "false") boolean isThumbnail,@PathVariable String productUuid, @RequestParam("file") MultipartFile file) throws Exception {
        RequestProductImageDTO requestProductImageDTO = new RequestProductImageDTO(productUuid,file,isThumbnail);
        this.productService.uploadProductImage(requestProductImageDTO);
        return ResponseEntity.ok().build();
    }
}
