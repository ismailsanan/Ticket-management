package com.example.TicketManagement.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
            final String requestTokenHeader = request.getHeader("Authorization");
            String username = null;
            String jwtToken = null;

            if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer")){
                jwtToken = requestTokenHeader.substring(7);
                try {
                    username = jwtService.getUsername(jwtToken);
                }
                catch (IllegalArgumentException e){
                    System.out.println("Unable to get JWT Token");
                }
            }

            else{
                logger.warn("JWT Token doesnt begin with bearer String");
            }

            if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){

                UserDetails userDetails =  this.userDetailsService.loadUserByUsername(username);
                if(jwtService.validateToken(jwtToken , userDetails)){

                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }


            }



        filterChain.doFilter(request, response);
    }
}













//        final String authHeader = request.getHeader("Authorization");
//        final String jwt;
//        final String userName;
//
//
//        if(authHeader == null  || !authHeader.startsWith("Bearer")){
//            filterChain.doFilter(request , response);
//            return;
//        }
//
//        jwt = authHeader.substring(7);
//        userName = jwtService.extractUsername(jwt);
//        if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null){
//            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
//            if(jwtService.isTokenValid(jwt , userDetails)){
//                UsernamePasswordAuthenticationToken authenticationToken  = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), passwordEncoder.encode(userDetails.getPassword()), userDetails.getAuthorities());
//
//                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//
//                System.out.println( "hellow im hereee  " + authenticationToken.getName()  + " "  +  authenticationToken.getPrincipal() +  " "  + authenticationToken.getAuthorities());
//            }
//        }
//        filterChain.doFilter(request,response);
//


