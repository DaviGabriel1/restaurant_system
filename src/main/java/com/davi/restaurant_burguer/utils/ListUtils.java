package com.davi.restaurant_burguer.utils;

import com.davi.restaurant_burguer.dtos.orders.OrderItemDTO;
import com.davi.restaurant_burguer.dtos.orders.RequestAdditionalsDTO;
import com.davi.restaurant_burguer.models.Product;
import com.davi.restaurant_burguer.models.ProductAdditional;

import java.util.*;
import java.util.stream.Collectors;

public class ListUtils {

    public static Map<Long, Product> generateMapFromOrderItemDTO(List<OrderItemDTO> orderItensDTO, List<Product> products) {
        Map<Long, Product> productMapped = new HashMap<>();

        for(Product product : products) {
            for(OrderItemDTO orderitemDTO : orderItensDTO) {
                if(Objects.equals(product.getId(), orderitemDTO.productId())) {
                    productMapped.put(orderitemDTO.productId(), product);
                }
            }
        }
        return productMapped;
    }
    public static Map<Long, ProductAdditional> generateMapFromAdditionalDTOToProductAdditional(List<RequestAdditionalsDTO> additionalsDTO, List<ProductAdditional> products) {
        Map<Long, ProductAdditional> productMapped = new HashMap<>();

        for(ProductAdditional product : products) {
            for(RequestAdditionalsDTO additional : additionalsDTO) {
                if(Objects.equals(product.getId(), additional.additionalId())) {
                    productMapped.put(additional.additionalId(), product);
                }
            }
        }
        return productMapped;
    }

    public static boolean validateIfAllProductsWereFound(List<Long> ids, List<Product> products) {
        Set<Long> buscados = products.stream().map(Product::getId).collect(Collectors.toSet());
        Set<Long> faltantes = new HashSet<>(ids);
        faltantes.removeAll(buscados);
        return faltantes.isEmpty();
    }
    public static boolean validateIfAllProductsAdditionalsWereFound(List<Long> ids, List<ProductAdditional> products) {
        Set<Long> buscados = products.stream().map(ProductAdditional::getId).collect(Collectors.toSet());
        Set<Long> faltantes = new HashSet<>(ids);
        faltantes.removeAll(buscados);
        return faltantes.isEmpty();
    }
}
