package com.gucardev.hibernateenversaudithistory.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

@Audited
@Entity
@Getter
@Setter
@Table(name = "`user`")
@NoArgsConstructor
public class User extends BaseEntity {

  private String name;
  private String username;

  @NotAudited
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<Address> addresses;

}
