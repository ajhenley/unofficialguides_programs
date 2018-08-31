package com.example.demo;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "appRole")
public class AppRole {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(unique = true)
  private String appRole;

  @ManyToMany(mappedBy = "appRoles", fetch = FetchType.LAZY)
  private Collection<AppUser> appUsers;

  public AppRole() {
  }

  public AppRole(String appRole) {
    this.appRole = appRole;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getAppRole() {
    return appRole;
  }

  public void setAppRole(String appRole) {
    this.appRole = appRole;
  }

  public Collection<AppUser> getAppUsers() {
    return appUsers;
  }

  public void setAppUsers(Collection<AppUser> appUsers) {
    this.appUsers = appUsers;
  }
}
