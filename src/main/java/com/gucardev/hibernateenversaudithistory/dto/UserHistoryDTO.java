package com.gucardev.hibernateenversaudithistory.dto;

import com.gucardev.hibernateenversaudithistory.enumeration.MyRevType;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserHistoryDTO {
  private Long id;
  private String name;
  private String username;
  private Date dateTime;
  private Date lastModifiedTime;

  // Revision Details
  private Long rev;
  //  private MyRevType revType;
  private int revType;
  private Date revDate;
}
