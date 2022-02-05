//package com.rezso.backend.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@Configuration
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    UserDetailsService userDetailsService;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//        auth.userDetailsService(userDetailsService);
//    }
//
//    protected void configure(HttpSecurity http) throws Exception{
//        http.csrf().disable();
//        http.authorizeHttpRequests()
//                .antMatchers("/no-auth/**")
//                .permitAll()
//                .antMatchers("/user/**", "/admin/**", "/executive/**").authenticated()
//                .anyRequest().authenticated()
//                .and().httpBasic();
////                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
//    }
//
//    @Bean
//    public static NoOpPasswordEncoder passwordEncoder(){
//        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
//    }
//
//
//}
