package com.example.demo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Transactional
@Service
public class SSUserDetailsService implements UserDetailsService {

  private AppUserRepository appUserRepository;

  public SSUserDetailsService(AppUserRepository userRepository){
    this.appUserRepository=userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String appUsername) throws UsernameNotFoundException{
    try {
      AppUser appUser = appUserRepository.findByUsername(appUsername);
      if (appUser == null) {
        System.out.println("User not found with the provided username" + appUser.toString());
        return null;
      }

      System.out.println(" User from username " + appUser.toString());

      return new org.springframework.security.core.userdetails.User(
              appUser.getUsername(),
              appUser.getPassword(),
              getAuthorities(appUser));
    } catch (Exception e){
      throw new UsernameNotFoundException("User not found");
    }
  } // End loadUserByUsername

  private Set<GrantedAuthority> getAuthorities(AppUser appUser) {
    Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
    for (AppRole role : appUser.getAppRoles()) {
      GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRole());
      authorities.add(grantedAuthority);
    }
    System.out.println("User authorities are" + authorities.toString());
    return authorities;
  }
}
