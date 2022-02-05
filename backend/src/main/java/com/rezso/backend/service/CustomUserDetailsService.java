//package com.rezso.backend.service;
//
//import com.rezso.backend.model.CustomUserDetails;
//import com.rezso.backend.model.User;
//import com.rezso.backend.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> userByUsername = userRepository.findUserByUsername(username);
//        userByUsername.orElseThrow(()-> new UsernameNotFoundException("Not found: " + username));
//        return new CustomUserDetails(userByUsername.get());
//    }
//}
