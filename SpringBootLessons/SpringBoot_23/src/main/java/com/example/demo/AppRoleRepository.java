package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import javax.management.relation.Role;

public interface AppRoleRepository extends CrudRepository<AppRole, Long> {

  AppRole findByRole(String role);
}
