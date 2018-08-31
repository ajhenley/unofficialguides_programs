package com.example.demo;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "appRole")
public class AppRole {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(unique=true)
  private String role;

  @ManyToMany(mappedBy = "appRoles", fetch = FetchType.LAZY)
  private Collection<AppUser> appUsers;


  public AppRole() {
  }

  public AppRole(String role) {
    this.role = role;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public Collection<AppUser> getAppUsers() {
    return appUsers;
  }

  public void setAppUsers(Collection<AppUser> appUsers) {
    this.appUsers = appUsers;
  }
}
