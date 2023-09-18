package com.example.TicketManagement.security;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.BeanDefinitionDsl;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {
    private final JwtAuthFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        authorize -> authorize
                                .requestMatchers("/user/signup").permitAll()
                                .requestMatchers("/user/login").permitAll()
                                .requestMatchers("/user/{id}").permitAll()
                                .requestMatchers(HttpMethod.POST ,"/user/createuser").hasAuthority("MANAGER")  //WORKS !!!!
                                //.requestMatchers("/ticket/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/ticket/{id}/assign/{id2}/{id3}").hasAuthority("MANAGER")
                                .requestMatchers(HttpMethod.POST, "/ticket/createticket/{id}").hasAuthority("CUSTOMER")
                                .requestMatchers(HttpMethod.PUT, "/ticket/process/{id}/stoprocess").hasAuthority("EXPERT")
                                .requestMatchers(HttpMethod.PUT, "/ticket/process/{id}/closeissue").hasAuthority("EXPERT")
                                .requestMatchers(HttpMethod.PUT, "/ticket/process/{id}/resolveticket").hasAuthority("EXPERT")
                                .requestMatchers(HttpMethod.PUT, "/ticket/process/{id}/startprogress").hasAuthority("EXPERT")
                                .requestMatchers(HttpMethod.PUT, "/ticket/process/{id}/reopenissue").hasAuthority("EXPERT")
                                .requestMatchers(HttpMethod.GET, "/ticket/geticket/{id}").permitAll()
                                .requestMatchers(HttpMethod.DELETE, "/ticket/process/removeticket/{id}").hasAnyAuthority(("EXPERT") , ("CUSTOMER"))
                                .requestMatchers("/messages/**").permitAll()
                                .requestMatchers("/chat/createchat").hasAuthority("CUSTOMER")
                                .requestMatchers("/chat/**").permitAll()
                                .requestMatchers("/attachment/**").permitAll()

                )

                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter , UsernamePasswordAuthenticationFilter.class)
                .build();
//                .logout((logout) -> logout.logoutUrl("/user/logout"))

    }
}
//
//.requestMatchers("/user/signup").permitAll()
//        .requestMatchers("/user/createuser").permitAll()//.hasRole("MANAGER")
//        .requestMatchers("/ticket/createticket/*").permitAll()//.hasRole("CUSTOMER")
//        .requestMatchers("/ticket/process/**").permitAll()//.hasRole("EXPERT")
//        .requestMatchers("/ticket/removeticket").permitAll()
//        .requestMatchers("/user/login").permitAll()
//        .requestMatchers("/user/*").permitAll()
//        .anyRequest().authenticated()

