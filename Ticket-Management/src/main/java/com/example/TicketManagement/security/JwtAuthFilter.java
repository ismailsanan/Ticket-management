package com.example.TicketManagement.security;

import io.jsonwebtoken.Claims;
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

            if(SecurityContextHolder.getContext().getAuthentication() == null){
                final String authorization = request.getHeader("Authorization");
                if(authorization != null && authorization.startsWith("Bearer")){

                    final String token = authorization.substring(7);

                    final Claims claims = jwtService.getClaims(token);

                    if(jwtService.validateToken(token)){
                        final String username = claims.getSubject();
                        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                        final UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails , null , userDetails.getAuthorities());
                        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                }


            }

            filterChain.doFilter(request , response);



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


