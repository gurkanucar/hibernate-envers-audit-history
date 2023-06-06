package com.gucardev.hibernateenversaudithistory.service;

import com.gucardev.hibernateenversaudithistory.dto.AddressDTO;
import com.gucardev.hibernateenversaudithistory.dto.AddressHistoryDTO;
import com.gucardev.hibernateenversaudithistory.dto.request.AddressRequest;
import com.gucardev.hibernateenversaudithistory.mapper.AddressHistoryMapper;
import com.gucardev.hibernateenversaudithistory.mapper.AddressMapper;
import com.gucardev.hibernateenversaudithistory.model.Address;
import com.gucardev.hibernateenversaudithistory.model.User;
import com.gucardev.hibernateenversaudithistory.repository.AddressRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

  private final AddressRepository addressRepository;
  private final UserServiceImpl userService;
  private final EntityManager entityManager;

  public AddressServiceImpl(
      AddressRepository addressRepository, UserServiceImpl userService, EntityManager entityManager) {
    this.addressRepository = addressRepository;
    this.userService = userService;
    this.entityManager = entityManager;
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

  public AddressDTO update(AddressRequest addressRequest) {
    Address address = findAddressById(addressRequest.getId());
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

  public List<AddressHistoryDTO> getAddressHistory(Long id) {
    AuditReader auditReader = AuditReaderFactory.get(entityManager);

    List<Object[]> revisions =
        auditReader
            .createQuery()
            .forRevisionsOfEntity(Address.class, false, true)
            .add(AuditEntity.id().eq(id))
            .getResultList();

    return revisions.stream().map(this::mapToUserHistoryDTO).collect(Collectors.toList());
  }

  private AddressHistoryDTO mapToUserHistoryDTO(Object[] revision) {
    Address address = (Address) revision[0];
    DefaultRevisionEntity revisionEntity = (DefaultRevisionEntity) revision[1];
    RevisionType revisionType = (RevisionType) revision[2];

    return AddressHistoryMapper.INSTANCE.addressToUserHistoryDTO(
        address, revisionType, revisionEntity);
  }
}
