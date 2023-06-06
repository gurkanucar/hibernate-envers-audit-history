package com.gucardev.hibernateenversaudithistory.controller;

import com.gucardev.hibernateenversaudithistory.dto.AddressDTO;
import com.gucardev.hibernateenversaudithistory.dto.AddressHistoryDTO;
import com.gucardev.hibernateenversaudithistory.dto.request.AddressRequest;
import com.gucardev.hibernateenversaudithistory.service.AddressService;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class AddressController {

  private final AddressService addressService;

  public AddressController(AddressService addressService) {
    this.addressService = addressService;
  }

  @PostMapping
  public AddressDTO create(@RequestBody AddressRequest request) {
    return addressService.create(request);
  }

  @GetMapping
  public List<AddressDTO> getAll() {
    return addressService.getAll();
  }

  @GetMapping("/{id}")
  public AddressDTO getById(@PathVariable Long id) {
    return addressService.getById(id);
  }

  @PutMapping
  public AddressDTO update(@RequestBody AddressRequest request) {
    return addressService.update(request);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    addressService.delete(id);
  }

  @GetMapping("/revision/{id}")
  public List<AddressHistoryDTO> getUserRevisions(@PathVariable Long id) {
    return addressService.getAddressHistory(id);
  }
}
