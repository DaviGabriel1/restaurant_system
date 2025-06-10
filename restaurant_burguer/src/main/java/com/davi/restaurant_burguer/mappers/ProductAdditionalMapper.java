package com.davi.restaurant_burguer.mappers;

import com.davi.restaurant_burguer.dtos.products.additional.RequestProductAdditionalDTO;
import com.davi.restaurant_burguer.dtos.products.additional.ResponseProductAdditionalDTO;
import com.davi.restaurant_burguer.models.ProductAdditional;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductAdditionalMapper {

    ProductAdditional mapToEntity(RequestProductAdditionalDTO productAdditionalDTO);
    ResponseProductAdditionalDTO mapToDTO(ProductAdditional productAdditional);
    List<ResponseProductAdditionalDTO> mapListToDTO(List<ProductAdditional> productAdditionalList);
}
