package com.example.project.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.project.Model.ApplicationUser;
import com.example.project.repository.ApplicationUserRepository;

@Service
public class UserAuthService implements UserDetailsService {
    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ApplicationUser> user = applicationUserRepository.findByUser_name(username);
        
        if (user.isPresent()) {
            ApplicationUser applicationUser = user.get();
          
            List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
            return new org.springframework.security.core.userdetails.User(
                applicationUser.getUser_name(),
                applicationUser.getPassword(),
                authorities
            );
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
