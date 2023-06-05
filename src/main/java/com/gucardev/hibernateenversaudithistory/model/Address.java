package com.gucardev.hibernateenversaudithistory.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

@Audited
@Entity
@Getter
@Setter
public class Address extends BaseEntity {

  private String street;
  private String city;
  private String state;
  private String country;
  private String zipCode;

  @ManyToOne(fetch = FetchType.LAZY)
  private User user;
}
