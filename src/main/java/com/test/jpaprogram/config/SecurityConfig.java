package com.test.jpaprogram.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    };


    @Bean
    public UserDetailsService userDetailsService(){

        UserDetails normalUser = User
                .withUsername("tester")
                .password(passwordEncoder().encode("tester"))
                 .roles("USER")
                .build();

        UserDetails adminUser = User
                .withUsername("raoof")
                .password(passwordEncoder().encode("raoof"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(normalUser,adminUser);
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf().disable()
                .authorizeRequests()
                .antMatchers("/home/normalUser").hasRole("USER")
                .antMatchers("/home/adminUser").hasRole("ADMIN")
                .antMatchers("/home").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin();

        return  httpSecurity.build();
    }
}
