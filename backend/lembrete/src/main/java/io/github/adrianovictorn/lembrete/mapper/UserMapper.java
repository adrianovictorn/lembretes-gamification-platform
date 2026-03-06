package io.github.adrianovictorn.lembrete.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import io.github.adrianovictorn.lembrete.dto.user.UserCreateDTO;
import io.github.adrianovictorn.lembrete.dto.user.UserListDTO;
import io.github.adrianovictorn.lembrete.dto.user.UserViewDTO;
import io.github.adrianovictorn.lembrete.entity.User;
import io.github.adrianovictorn.lembrete.enums.Role;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "senha",ignore = true)
    @Mapping(target = "lembretes", ignore = true)
    @Mapping(target = "gameProfile", ignore = true)
    @Mapping(target = "userSequence", ignore = true)
    User toEntity(UserCreateDTO dto);

    UserViewDTO toViewDTO(User user);
    List<UserListDTO> toList(List<User> lista);

    default Role mapCargo(String cargo){
        return Role.valueOf(cargo);
    }
    
} 
