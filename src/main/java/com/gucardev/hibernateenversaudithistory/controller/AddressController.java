package com.gucardev.hibernateenversaudithistory.controller;

import com.gucardev.hibernateenversaudithistory.dto.AddressDTO;
import com.gucardev.hibernateenversaudithistory.dto.AddressHistoryDTO;
import com.gucardev.hibernateenversaudithistory.dto.request.AddressRequest;
import com.gucardev.hibernateenversaudithistory.service.AddressService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<AddressDTO> create(@RequestBody AddressRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(addressService.create(request));
  }

  @GetMapping
  public ResponseEntity<List<AddressDTO>> getAll() {
    return ResponseEntity.ok(addressService.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<AddressDTO> getById(@PathVariable Long id) {
    return ResponseEntity.ok(addressService.getById(id));
  }

  @PutMapping
  public ResponseEntity<AddressDTO> update(@RequestBody AddressRequest request) {
    return ResponseEntity.ok(addressService.update(request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    addressService.delete(id);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/revision/{id}")
  public ResponseEntity<List<AddressHistoryDTO>> getUserRevisions(@PathVariable Long id) {
    return ResponseEntity.ok(addressService.getAddressHistory(id));
  }
}
