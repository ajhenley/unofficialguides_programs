package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {

  AppUser findByUsername(String username);
  AppUser findByEmail(String email);
  Long countByEmail(String email);
  Long countByUsername(String username);
}
