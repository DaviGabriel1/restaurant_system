package com.davi.restaurant_burguer.services;

import com.davi.restaurant_burguer.models.OrderItemAdditional;
import com.davi.restaurant_burguer.models.ProductAdditional;
import com.davi.restaurant_burguer.repositories.OrderItemAdditionalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemAdditionalService {
    private final OrderItemAdditionalRepository orderItemAdditionalRepository;

    public OrderItemAdditionalService (OrderItemAdditionalRepository orderItemAdditionalRepository) {
        this.orderItemAdditionalRepository = orderItemAdditionalRepository;
    }

    public void saveAllAdditional(List<OrderItemAdditional> ordersItensAdditional) {
        this.orderItemAdditionalRepository.saveAll(ordersItensAdditional);
    }

    public List<ProductAdditional> getAllProductAdditionalByIdIn(List<Long> ids) {
        return this.orderItemAdditionalRepository.findOrderItemAdditionalByIdIn(ids);
    }
}
