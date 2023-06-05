package com.gucardev.hibernateenversaudithistory.mapper;

import com.gucardev.hibernateenversaudithistory.dto.UserDTO;
import com.gucardev.hibernateenversaudithistory.dto.request.UserRequest;
import com.gucardev.hibernateenversaudithistory.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {AddressMapper.class})
public interface UserMapper {

  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  User userRequestToUser(UserRequest userRequest);

  @Mapping(source = "addresses", target = "addressesDto", qualifiedByName = "addressToAddressDTO")
  UserDTO userToUserDTO(User user);

  @Mapping(
      source = "addresses",
      target = "addressesDto",
      qualifiedByName = "addressToAddressDTONullUserIdUsername")
  UserDTO userToUserDTONullUserIdUsername(User user);
}
