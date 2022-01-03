package com.boulow.mono.service.mapper;

import org.mapstruct.*;

import com.boulow.mono.entity.User;
import com.boulow.mono.entity.dto.UserDTO;

/**
 * Mapper for the entity {@link BoulowUser} and its DTO {@link BoulowUserDTO}.
 */
@Mapper(componentModel = "spring", uses = { AddressMapper.class })
public interface UserMapper extends EntityMapper<UserDTO, User> {
    UserDTO toDto(User s);
}
