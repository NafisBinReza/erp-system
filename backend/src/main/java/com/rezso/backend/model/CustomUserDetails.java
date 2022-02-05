//package com.rezso.backend.model;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class CustomUserDetails implements UserDetails {
//    private String username;
//    private String password;
//    private List<GrantedAuthority> grantedAuthorities;
//
//    public CustomUserDetails(User user) {
//        this.username = user.getUsername();
//        this.password = user.getPassword();
//        this.grantedAuthorities = Arrays.stream(user.getRoles().split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return this.grantedAuthorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return this.password;
//    }
//
//    @Override
//    public String getUsername() {
//        return this.username;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
