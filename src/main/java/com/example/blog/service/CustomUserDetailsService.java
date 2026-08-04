package com.example.blog.service;


import com.example.blog.model.User;
import com.example.blog.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//class 3 jwt token
//implements UDS returns a user in ud with username password authprity
@Service
public class CustomUserDetailsService implements UserDetailsService {
 private final UserRepository userRepository;
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User not found"));
        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities("USER").build();}
}
