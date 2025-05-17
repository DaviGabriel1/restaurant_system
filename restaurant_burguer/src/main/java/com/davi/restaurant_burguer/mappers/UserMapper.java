package com.davi.restaurant_burguer.mappers;

import com.davi.restaurant_burguer.dtos.auth.RequestRegisterDTO;
import com.davi.restaurant_burguer.dtos.users.ResponseUserDTO;
import com.davi.restaurant_burguer.enums.Provider;
import com.davi.restaurant_burguer.enums.Role;
import com.davi.restaurant_burguer.models.Users;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.WARN) //cria uma classe mapper em tempo de execução
public interface UserMapper {

    default Role mapRole(byte role) {return Role.fromByte(role);}

    default Provider mapProvider(byte provider) {return Provider.fromByte(provider);}

    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", source = "created_at", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "updatedAt", source = "updated_at", dateFormat = "yyyy-MM-dd HH:mm:ss")
    List<ResponseUserDTO> mapFindAll(List<Users> users);

    ResponseUserDTO mapToResponseUserDTO(Users users);

    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", ignore = true)
    Users mapToUsers(RequestRegisterDTO requestRegisterDTO);
}
