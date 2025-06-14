package com.davi.restaurant_burguer.helpers.mappers;

import com.davi.restaurant_burguer.exceptions.NotfoundException;
import com.davi.restaurant_burguer.models.OrderItemAdditional;
import com.davi.restaurant_burguer.repositories.OrderItemAdditionalRepository;

public class AdditionalMapperHelper {
    private final OrderItemAdditionalRepository orderItemAdditionalRepository;

    public AdditionalMapperHelper(OrderItemAdditionalRepository orderItemAdditionalRepository) {
        this.orderItemAdditionalRepository = orderItemAdditionalRepository;
    }

    public OrderItemAdditional map(Long id) {
        return this
                .orderItemAdditionalRepository
                .findById(id)
                .orElseThrow(() -> new NotfoundException("adicional de produto não encontrado"));
    }
}
