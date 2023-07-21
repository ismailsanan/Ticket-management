package com.example.TicketManagement.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
@AllArgsConstructor
@Configuration
public class SecurityConfig {

    private final PasswordEncoder passwordEncoder;
    public static final String CUSTOMER = "CUSTOMER";
    public static final String MANAGER = "MANAGER";
    public static final String EXPERT = "EXPERT";




    @Bean
     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
            http.authorizeHttpRequests((auth) -> auth

                    .requestMatchers(HttpMethod.GET , "/ticket/*").permitAll()
                    .requestMatchers(HttpMethod.PUT , "/ticket/*").hasRole(EXPERT)
                    .requestMatchers(HttpMethod.POST , "/ticket/*").hasRole(CUSTOMER)
                    .anyRequest().authenticated());



            return http.build();

    }



    @Bean
    public UserDetailsService users(){

        UserDetails mishlen = User.builder()
                                    .username("anasmith")
                                    .password(passwordEncoder.encode("password"))
                                    .roles("CUSTOMER")
                                    .build();

        return new InMemoryUserDetailsManager(
                mishlen
        );

    }

}