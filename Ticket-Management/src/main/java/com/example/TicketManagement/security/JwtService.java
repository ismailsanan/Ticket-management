package com.example.TicketManagement.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;


@Service
public class JwtService {


    @Value("${jwtSecret}")
    private String SECRET_KEY;

    //@Value(${jwtExpiration})
    //add this to applciation.properties
    private int Expiration = 10000000;


    private SecretKey getKey(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Expiration))
                .signWith(getKey(), SignatureAlgorithm.HS512)
                .compact();
    }


    public Claims getClaims(String token){
        return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody();

    }

    public String getUsername(String token){
        return getClaims(token).getSubject();
    }

    public boolean validateToken(String token , UserDetails userDetails ){
        final String username = getUsername(token);

        return (username.equals(userDetails.getUsername()) && !getClaims(token).getExpiration().before(new Date()));

    }




}
