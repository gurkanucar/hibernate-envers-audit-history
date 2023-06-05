package com.gucardev.hibernateenversaudithistory.mapper;

import com.gucardev.hibernateenversaudithistory.dto.UserHistoryDTO;
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

  default int map(RevisionType value) {
    return value.ordinal();
  }

  @Mapping(target = "rev", source = "revisionEntity.id")
  @Mapping(target = "revType", source = "revisionType")
  @Mapping(
      target = "revDate",
      source = "revisionEntity.timestamp",
      qualifiedByName = "mapTimestampToDate")
  @Mapping(target = "id", source = "user.id")
  @Mapping(target = "dateTime", source = "user.createDate")
  @Mapping(target = "lastModifiedTime", source = "user.lastModifiedTime")
  UserHistoryDTO userToUserHistoryDTO(
      User user, RevisionType revisionType, DefaultRevisionEntity revisionEntity);

  @Named("mapTimestampToDate")
  default Date mapTimestampToDate(long timestamp) {
    return new Date(timestamp);
  }
}
