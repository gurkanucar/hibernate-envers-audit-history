package com.gucardev.hibernateenversaudithistory.service;

import com.gucardev.hibernateenversaudithistory.dto.AddressDTO;
import com.gucardev.hibernateenversaudithistory.dto.AddressHistoryDTO;
import com.gucardev.hibernateenversaudithistory.dto.request.AddressRequest;
import java.util.List;

public interface AddressService {

  List<AddressDTO> getAll();

  AddressDTO getById(Long id);

  AddressDTO create(AddressRequest addressRequest);

  AddressDTO update(AddressRequest addressRequest);

  void delete(Long id);

  List<AddressHistoryDTO> getAddressHistory(Long id);
}
