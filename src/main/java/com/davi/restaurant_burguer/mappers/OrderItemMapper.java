package com.davi.restaurant_burguer.mappers;

import com.davi.restaurant_burguer.dtos.orders.OrderItemDTO;
import com.davi.restaurant_burguer.dtos.orders.RequestAdditionalsDTO;
import com.davi.restaurant_burguer.exceptions.NotfoundException;
import com.davi.restaurant_burguer.models.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class OrderItemMapper {
    private final AdditionalMapper additionalMapper;

    public OrderItemMapper(AdditionalMapper additionalMapper) {
        this.additionalMapper = additionalMapper;
    }

    public OrderItem mapToEntity(OrderItemDTO dto, Order order, Product product) {
        OrderItem orderItemMapped = new OrderItem();
        orderItemMapped.setOrder(order);
        orderItemMapped.setNote(dto.note());
        orderItemMapped.setProduct(product);
        orderItemMapped.setQuantity(dto.quantity());
        orderItemMapped.setPriceUnitAtTime(new BigDecimal(dto.priceUnit()));
        return orderItemMapped;
    }

    public List<OrderItem> mapListToEntity(List<OrderItemDTO> itemDTOs, Order order, List<Product> products, List<ProductAdditional> allAdditionals) {
         return itemDTOs.stream()
                .map(dto -> {
                    Product product = products.stream()
                            .filter(p -> p.getId().equals(dto.productId()))
                            .findFirst()
                            .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado: " + dto.productId()));

                    OrderItem item = new OrderItem();
                    item.setOrder(order);
                    item.setProduct(product);
                    item.setQuantity(dto.quantity());
                    item.setNote(dto.note());
                    item.setPriceUnitAtTime(product.getPrice());

                    List<OrderItemAdditional> itemAdditionals = dto.additionals().stream()
                            .map(addDTO -> {
                                ProductAdditional additional = allAdditionals.stream()
                                        .filter(pa -> pa.getId().equals(addDTO.additionalId()))
                                        .findFirst()
                                        .orElseThrow(() -> new IllegalArgumentException("Adicional não encontrado: " + addDTO.additionalId()));
                                return new OrderItemAdditional(item, additional, addDTO.quantity());
                            }).toList();

                    item.setOrderItemAdditionals(itemAdditionals);
                    return item;
                }).toList();
    }
}
