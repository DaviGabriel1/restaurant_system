package com.davi.restaurant_burguer.mappers;

import com.davi.restaurant_burguer.dtos.products.RequestProductDTO;
import com.davi.restaurant_burguer.dtos.products.ResponseProductDTO;
import com.davi.restaurant_burguer.models.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface ProductMapper {

    Product mapToProduct(RequestProductDTO requestProductDTO);

    ResponseProductDTO mapToResponseProductDTO(Product product);

    @Mapping(target = "id", ignore = true)
    List<ResponseProductDTO> mapToResponseProductDTOList(List<Product> products);
}
