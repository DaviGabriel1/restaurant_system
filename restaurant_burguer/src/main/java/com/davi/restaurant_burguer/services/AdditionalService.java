package com.davi.restaurant_burguer.services;

import com.davi.restaurant_burguer.dtos.products.additional.RequestProductAdditionalDTO;
import com.davi.restaurant_burguer.dtos.products.additional.ResponseProductAdditionalDTO;
import com.davi.restaurant_burguer.exceptions.NotfoundException;
import com.davi.restaurant_burguer.mappers.ProductAdditionalMapper;
import com.davi.restaurant_burguer.models.Product;
import com.davi.restaurant_burguer.models.ProductAdditional;
import com.davi.restaurant_burguer.repositories.ProductAdditionalRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AdditionalService {
    private final ProductAdditionalRepository productAdditionalRepository;
    private final ProductAdditionalMapper productAdditionalMapper;

    public AdditionalService(ProductAdditionalRepository productAdditionalRepository, ProductAdditionalMapper productAdditionalMapper) {
        this.productAdditionalRepository = productAdditionalRepository;
        this.productAdditionalMapper = productAdditionalMapper;
    }

    public List<ResponseProductAdditionalDTO> findAll(Long productId) {
        List<ProductAdditional> productAdditional = this.productAdditionalRepository.findAll(productId);
        return this.productAdditionalMapper.mapListToDTO(productAdditional);
    }

    public ResponseProductAdditionalDTO findOne(Long additionalId) {
        ProductAdditional productAdditional = this.productAdditionalRepository.findOne(additionalId);
        return this.productAdditionalMapper.mapToDTO(productAdditional);
    }

    public void saveProductAdditional(Product product, RequestProductAdditionalDTO productAdditionalDTO) {
        ProductAdditional productAdditional = this.productAdditionalMapper.mapToEntity(productAdditionalDTO);
        productAdditional.setProductId(product);
        this.productAdditionalRepository.save(productAdditional);
    }

    public ResponseProductAdditionalDTO updateProductAdditional(Long productAdditionalId,RequestProductAdditionalDTO productAdditionalDTO) {
        if(productAdditionalId == null) {
            throw new IllegalArgumentException("o id do adicional é inválido!");
        }
        ProductAdditional productAdditional = this.productAdditionalMapper.mapToEntity(productAdditionalDTO);
        productAdditional.setId(productAdditionalId);
        ProductAdditional updatedProductAdditional = this.productAdditionalRepository.save(productAdditional);
        return this.productAdditionalMapper.mapToDTO(updatedProductAdditional);
    }

    public void delete(Long productAdditionalId) {
        Optional<ProductAdditional> productAdditional = this.productAdditionalRepository.findById(productAdditionalId);
        if(productAdditional.isEmpty()) throw new NotfoundException("adicional de produto não encontrado");
        ProductAdditional newProductAdditional = productAdditional.get();
        newProductAdditional.setDeletedAt(OffsetDateTime.now());
        this.productAdditionalRepository.save(newProductAdditional);
    }
}
