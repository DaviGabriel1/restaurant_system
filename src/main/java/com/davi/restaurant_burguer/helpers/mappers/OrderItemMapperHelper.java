package com.davi.restaurant_burguer.helpers.mappers;

import com.davi.restaurant_burguer.exceptions.NotfoundException;
import com.davi.restaurant_burguer.models.ProductAdditional;
import com.davi.restaurant_burguer.repositories.ProductAdditionalRepository;

public class OrderItemMapperHelper {
    private final ProductAdditionalRepository productAdditionalRepository;

    public OrderItemMapperHelper(ProductAdditionalRepository productAdditionalRepository) {
        this.productAdditionalRepository = productAdditionalRepository;
    }

    public ProductAdditional map(Long id) {
        return this
                .productAdditionalRepository
                .findById(id)
                .orElseThrow(() -> new NotfoundException("produto inválido enviado"));
    }
}
