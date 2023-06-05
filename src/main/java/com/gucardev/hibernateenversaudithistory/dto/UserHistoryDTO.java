package com.gucardev.hibernateenversaudithistory.dto;

import com.gucardev.hibernateenversaudithistory.enumeration.RevType;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserHistoryDTO {
  private Long id;
  private String name;
  private String username;

  // Revision Details
  private Long rev;
  private RevType revType;
  // private int revType;
  private Date revDate;
}
