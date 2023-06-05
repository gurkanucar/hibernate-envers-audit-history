package com.gucardev.hibernateenversaudithistory.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
  private Long id;
  private String name;
  private String username;
  private List<AddressDTO> addressesDto;
  private Date createdDate;
  private Date lastModifiedDate;
}
