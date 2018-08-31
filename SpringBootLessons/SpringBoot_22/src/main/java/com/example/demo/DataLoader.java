package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

  @Autowired
  AppUserRepository appUserRepository;

  @Autowired
  AppRoleRepository appRoleRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public void run(String... strings) throws Exception{
    System.out.println("Loading data...");

    appRoleRepository.save(new AppRole("USER"));
    appRoleRepository.save(new AppRole("ADMIN"));

    AppRole adminRole = appRoleRepository.findByRole("ADMIN");
    AppRole userRole = appRoleRepository.findByRole("USER");

    AppUser user = new
            AppUser("bob@bob.com", "password", "Bob",
            "Bobberson", true, "bob");
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setAppRoles(Arrays.asList(userRole));
    appUserRepository.save(user);

    user = new
            AppUser("jim@jim.com", "password", "Jim",
            "Jimmerson", true, "jim");
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setAppRoles(Arrays.asList(userRole));
    appUserRepository.save(user);

    user = new
            AppUser("admin@admin.com", "password",
            "Admin", "User", true, "admin");
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setAppRoles(Arrays.asList(adminRole));
    appUserRepository.save(user);

    user = new
            AppUser("sam@everyman.com","password",
            "Sam","Everyman",true,"sam");
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setAppRoles(Arrays.asList(userRole, adminRole));
    appUserRepository.save(user);
  }
}
