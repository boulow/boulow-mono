package com.boulow.mono.service.mapper;

import org.mapstruct.*;

import com.boulow.mono.entity.Address;
import com.boulow.mono.entity.dto.AddressDTO;

/**
 * Mapper for the entity {@link Address} and its DTO {@link AddressDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AddressMapper extends EntityMapper<AddressDTO, Address> {
    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    AddressDTO toDtoId(Address address);
}
