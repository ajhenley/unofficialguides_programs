package com.example.demo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="AppUser")
public class AppUser {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "password")
  private String password;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "enabled")
  private boolean enabled;

  @Column(name = "username")
  private String username;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(joinColumns = @JoinColumn(name = "user_id"),
          inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Collection<AppRole> appRoles;

  public AppUser() {
  }

  public AppUser(String email, String password, String firstName, String lastName,
                 boolean enabled, String username) {
    this.email = email;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.enabled = enabled;
    this.username = username;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    this.password = passwordEncoder.encode(password);
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Collection<AppRole> getAppRoles() {
    return appRoles;
  }

  public void setAppRoles(Collection<AppRole> appRoles) {
    this.appRoles = appRoles;
  }
}
