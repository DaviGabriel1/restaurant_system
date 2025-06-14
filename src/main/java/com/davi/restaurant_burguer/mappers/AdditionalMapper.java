package com.davi.restaurant_burguer.mappers;

import com.davi.restaurant_burguer.dtos.orders.RequestAdditionalsDTO;
import com.davi.restaurant_burguer.exceptions.NotfoundException;
import com.davi.restaurant_burguer.models.OrderItem;
import com.davi.restaurant_burguer.models.OrderItemAdditional;
import com.davi.restaurant_burguer.models.ProductAdditional;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class AdditionalMapper {

    public OrderItemAdditional mapToEntity(RequestAdditionalsDTO dto, ProductAdditional productAdditional, OrderItem orderItem) {
        OrderItemAdditional orderItemAdditional = new OrderItemAdditional();
        orderItemAdditional.setQuantity(dto.quantity());
        orderItemAdditional.setAdditional(productAdditional);
        orderItemAdditional.setOrderItem(orderItem);
        return orderItemAdditional;
    }

    public List<OrderItemAdditional> mapListToEntity(List<RequestAdditionalsDTO> dto,OrderItem orderItem, Map<Long,ProductAdditional> productAdditionalMap) {
        List<OrderItemAdditional> orderItemAdditionals = new ArrayList<>();

        for(RequestAdditionalsDTO requestAdditionalsDTO : dto) {
            ProductAdditional productAdditional = productAdditionalMap.get(requestAdditionalsDTO.additionalId());
            if(productAdditional != null) throw new NotfoundException("Produto não encontrado");
            orderItemAdditionals.add(this.mapToEntity(requestAdditionalsDTO,productAdditional,orderItem));
        }
        return orderItemAdditionals;
    }
}
