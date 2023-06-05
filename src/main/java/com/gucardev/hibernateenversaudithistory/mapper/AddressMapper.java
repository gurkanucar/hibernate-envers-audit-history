package com.gucardev.hibernateenversaudithistory.mapper;

import com.gucardev.hibernateenversaudithistory.dto.AddressDTO;
import com.gucardev.hibernateenversaudithistory.dto.request.AddressRequest;
import com.gucardev.hibernateenversaudithistory.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper {
  AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

  @Mapping(source = "userId", target = "user.id")
  Address addressRequestToAddress(AddressRequest addressRequest);

  @Named("addressToAddressDTO")
  @Mapping(source = "user.id", target = "userId")
  @Mapping(source = "user.username", target = "username")
  AddressDTO addressToAddressDTO(Address address);

  @Named("addressToAddressDTONullUserIdUsername")
  @Mapping(target = "userId", ignore = true)
  @Mapping(target = "username", ignore = true)
  AddressDTO addressToAddressDTONullUserIdUsername(Address address);
}
