package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;


@Service
public class UserService {

  @Autowired
  AppUserRepository appUserRepository;

  @Autowired
  AppRoleRepository appRoleRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  public UserService(AppUserRepository userRepository){
    this.appUserRepository = userRepository;
  }

  public AppUser findByEmail(String email){
    return appUserRepository.findByEmail(email);
  }

  public Long countByEmail(String email){
    return appUserRepository.countByEmail(email);
  }

  public AppUser findByUsername(String username){
    return appUserRepository.findByUsername(username);
  }

  public void saveUser(AppUser appUser){
    appUser.setAppRoles(Arrays.asList(appRoleRepository.findByRole("USER")));
    appUser.setEnabled(true);
    appUserRepository.save(appUser);
  }

  public void saveAdmin(AppUser appUser) {
    appUser.setAppRoles(Arrays.asList(appRoleRepository.findByRole("ADMIN")));
    appUser.setEnabled(true);
    appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
    appUserRepository.save(appUser);
  }

}
