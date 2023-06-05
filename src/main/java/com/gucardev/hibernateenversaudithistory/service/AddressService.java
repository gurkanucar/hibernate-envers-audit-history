package com.gucardev.hibernateenversaudithistory.service;

import com.gucardev.hibernateenversaudithistory.dto.AddressDTO;
import com.gucardev.hibernateenversaudithistory.dto.request.AddressRequest;
import com.gucardev.hibernateenversaudithistory.mapper.AddressMapper;
import com.gucardev.hibernateenversaudithistory.model.Address;
import com.gucardev.hibernateenversaudithistory.model.User;
import com.gucardev.hibernateenversaudithistory.repository.AddressRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

  private final AddressRepository addressRepository;
  private final UserService userService;

  public AddressService(AddressRepository addressRepository, UserService userService) {
    this.addressRepository = addressRepository;
    this.userService = userService;
  }

  public List<AddressDTO> getAll() {
    return addressRepository.findAll().stream()
        .map(AddressMapper.INSTANCE::addressToAddressDTO)
        .collect(Collectors.toList());
  }

  public AddressDTO getById(Long id) {
    Address address = findAddressById(id);
    return AddressMapper.INSTANCE.addressToAddressDTO(address);
  }

  public AddressDTO create(AddressRequest addressRequest) {
    User user = userService.findUserById(addressRequest.getUserId());
    Address address = AddressMapper.INSTANCE.addressRequestToAddress(addressRequest);
    address.setUser(user);
    address = addressRepository.save(address);
    return AddressMapper.INSTANCE.addressToAddressDTO(address);
  }

  public AddressDTO update(Long id, AddressRequest addressRequest) {
    Address address = findAddressById(id);
    Address updatedAddress = AddressMapper.INSTANCE.addressRequestToAddress(addressRequest);
    BeanUtils.copyProperties(updatedAddress, address, "id", "user");
    addressRepository.save(address);
    return AddressMapper.INSTANCE.addressToAddressDTO(address);
  }

  public void delete(Long id) {
    if (addressRepository.existsById(id)) {
      addressRepository.deleteById(id);
    } else {
      throw new RuntimeException("Address not found with id: " + id);
    }
  }

  private Address findAddressById(Long id) {
    return addressRepository
        .findById(id)
        .orElseThrow(() -> new RuntimeException(("Address not found with id: " + id)));
  }
}
