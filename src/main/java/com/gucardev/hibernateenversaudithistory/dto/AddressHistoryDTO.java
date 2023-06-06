package com.gucardev.hibernateenversaudithistory.dto;

import com.gucardev.hibernateenversaudithistory.enumeration.RevType;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressHistoryDTO {
  private Long id;
  private String street;
  private String city;
  private String country;

  // Revision Details
  private Long rev;
  private RevType revType;
  private Date revDate;
}
