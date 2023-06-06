package com.gucardev.hibernateenversaudithistory.mapper;

import com.gucardev.hibernateenversaudithistory.dto.AddressHistoryDTO;
import com.gucardev.hibernateenversaudithistory.enumeration.RevType;
import com.gucardev.hibernateenversaudithistory.model.Address;
import java.util.Date;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressHistoryMapper {
  AddressHistoryMapper INSTANCE = Mappers.getMapper(AddressHistoryMapper.class);

  @Mapping(target = "rev", source = "revisionEntity.id")
  @Mapping(target = "revType", source = "revisionType")
  @Mapping(
      target = "revDate",
      source = "revisionEntity.timestamp",
      qualifiedByName = "mapTimestampToDate")
  @Mapping(target = "id", source = "address.id")
  AddressHistoryDTO addressToUserHistoryDTO(
      Address address, RevisionType revisionType, DefaultRevisionEntity revisionEntity);

  default RevType map(RevisionType value) {
    return RevType.fromInt(value.ordinal());
  }

  @Named("mapTimestampToDate")
  default Date mapTimestampToDate(long timestamp) {
    return new Date(timestamp);
  }
}
