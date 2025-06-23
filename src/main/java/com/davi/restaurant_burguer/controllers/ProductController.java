package com.davi.restaurant_burguer.controllers;

import com.davi.restaurant_burguer.controllers.docs.ProductControllerDocs;
import com.davi.restaurant_burguer.dtos.GenericResponseDTO;
import com.davi.restaurant_burguer.dtos.products.RequestProductDTO;
import com.davi.restaurant_burguer.dtos.products.ResponseProductDTO;
import com.davi.restaurant_burguer.dtos.products.additional.RequestProductAdditionalDTO;
import com.davi.restaurant_burguer.dtos.products.additional.ResponseProductAdditionalDTO;
import com.davi.restaurant_burguer.dtos.products.productImage.RequestProductImageDTO;
import com.davi.restaurant_burguer.exceptions.FileSizeException;
import com.davi.restaurant_burguer.exceptions.InvalidTypeException;
import com.davi.restaurant_burguer.infrastructure.security.SecurityConfiguration;
import com.davi.restaurant_burguer.services.ProductService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/products")
@Tag(name = "Products", description = "controller de CRUD de produtos")
@SecurityRequirement(name = SecurityConfiguration.SECURITY)
public class ProductController implements ProductControllerDocs {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("{uuid}")
    @Override
    public ResponseEntity<ResponseProductDTO> getProductByUuid(@PathVariable String uuid){
        return ResponseEntity.ok(this.productService.getProductByUuid(uuid));
    }

    @GetMapping
    @Override
    public ResponseEntity<List<ResponseProductDTO>> getAllProducts(){
        return ResponseEntity.ok(this.productService.getAllProductsCacheable());
    }

    @PostMapping
    @Override
    public ResponseEntity<ResponseProductDTO> saveProduct(@RequestBody @Valid RequestProductDTO requestProductDTO){
        ResponseProductDTO productDTO = this.productService.saveProduct(requestProductDTO);
        return ResponseEntity.status(201).body(productDTO);
    }

    @PutMapping("{uuid}")
    @Override
    public ResponseEntity<ResponseProductDTO> updateProduct(@RequestBody @Valid RequestProductDTO requestProductDTO, @PathVariable String uuid){
        return ResponseEntity.ok(this.productService.updateProduct(requestProductDTO, uuid));
    }

    @DeleteMapping("{uuid}")
    @Override
    public ResponseEntity deleteProduct(@PathVariable String uuid){
        this.productService.deleteProduct(uuid);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/upload/{productUuid}")
    @Override
    public ResponseEntity<GenericResponseDTO> uploadProductImage(@RequestParam(required = false, defaultValue = "false") boolean isThumbnail, @PathVariable String productUuid, @RequestParam("file") MultipartFile file) throws Exception {
        if(file.getSize() > 2 * 1024 * 1024){
            throw new FileSizeException("O tamanho do arquivo deve ser menor que 2MB");
        }
        if(file.getContentType() == null || !file.getContentType().equals("image/jpeg") && !file.getContentType().equals("image/png")){
            throw new InvalidTypeException("O arquivo deve ser uma imagem JPEG ou PNG");
        }
        RequestProductImageDTO requestProductImageDTO = new RequestProductImageDTO(productUuid,file,isThumbnail);
        this.productService.uploadProductImage(requestProductImageDTO);
        return ResponseEntity.status(201).body(new GenericResponseDTO("imagem adicionada com sucesso!"));
    }

    @PostMapping("/additional/{productUuid}")
    @Override
    public ResponseEntity<GenericResponseDTO> saveProductAdditional(@PathVariable String productUuid, @RequestBody RequestProductAdditionalDTO productAdditional) {
        this.productService.saveAdditional(productUuid,productAdditional);
        return ResponseEntity.status(201).body(new GenericResponseDTO("adicional de produto criado com sucesso!"));
    }

    @GetMapping("additional/find-one/{productAdditionalId}")
    @Override
    public ResponseEntity<ResponseProductAdditionalDTO> findOneProductAdditional(@PathVariable Long productAdditionalId) {
        ResponseProductAdditionalDTO responseProductAdditionalDTO = this.productService.findOneProductAdditional(productAdditionalId);
        return ResponseEntity.status(200).body(responseProductAdditionalDTO);
    }

    @GetMapping("additional/find-all/{productUuid}")
    @Override
    public ResponseEntity<List<ResponseProductAdditionalDTO>> findAllProductAdditional(@PathVariable String productUuid) {
        List<ResponseProductAdditionalDTO> responseProductAdditionalDTO = this.productService.findAllProductAdditional(productUuid);
        return ResponseEntity.status(200).body(responseProductAdditionalDTO);
    }

    @PutMapping("/additional/{additionalId}")
    @Override
    public ResponseEntity<ResponseProductAdditionalDTO> updateProductAdditional(@PathVariable Long additionalId, @RequestBody RequestProductAdditionalDTO requestProductAdditionalDTO) {
        ResponseProductAdditionalDTO responseProductAdditionalDTO = this.productService.updateProductAdditional(additionalId,requestProductAdditionalDTO);
        return ResponseEntity.status(200).body(responseProductAdditionalDTO);
    }

    @DeleteMapping("/additional/{additionalId}")
    @Override
    public ResponseEntity deleteProductAdditional(@PathVariable Long additionalId) {
        this.productService.deleteProductAdditional(additionalId);
        return ResponseEntity.noContent().build();
    }
}