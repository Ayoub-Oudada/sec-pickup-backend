package com.backend.mappers;

import com.backend.dtos.AddressDto;
import com.backend.entities.Address;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = TrajetMapper.class
)
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    List<AddressDto> addressesToAddressDtos(List<Address> addresses);

    Address addressDtoToAddress(AddressDto addressDto);

    AddressDto addressToAddressDto(Address address);

    @Mapping(target = "id", ignore = true)
    void updateAddressFromAddressDto(AddressDto addressDto, @MappingTarget Address address);
}
