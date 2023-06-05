package com.gucardev.hibernateenversaudithistory.mapper;

import com.gucardev.hibernateenversaudithistory.dto.UserHistoryDTO;
import com.gucardev.hibernateenversaudithistory.enumeration.RevType;
import com.gucardev.hibernateenversaudithistory.model.User;
import java.util.Date;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserHistoryMapper {
  UserHistoryMapper INSTANCE = Mappers.getMapper(UserHistoryMapper.class);

  @Mapping(target = "rev", source = "revisionEntity.id")
  @Mapping(target = "revType", source = "revisionType")
  @Mapping(
      target = "revDate",
      source = "revisionEntity.timestamp",
      qualifiedByName = "mapTimestampToDate")
  @Mapping(target = "id", source = "user.id")
  UserHistoryDTO userToUserHistoryDTO(
      User user, RevisionType revisionType, DefaultRevisionEntity revisionEntity);

  default RevType map(RevisionType value) {
    //  return value.ordinal();
    return RevType.fromInt(value.ordinal());
  }

  @Named("mapTimestampToDate")
  default Date mapTimestampToDate(long timestamp) {
    return new Date(timestamp);
  }
}
