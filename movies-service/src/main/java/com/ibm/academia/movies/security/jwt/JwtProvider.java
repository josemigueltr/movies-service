package com.ibm.academia.movies.security.jwt;


import com.ibm.academia.movies.security.UserDetailsImp;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {

    private static final String SECRET_KEY="secret";

    private static final int EXPIRATION_TOKEN=43200;

    public String generateToken(Authentication authentication){
        UserDetailsImp UserDetailsImp = (UserDetailsImp) authentication.getPrincipal();
        return Jwts.builder().setSubject(UserDetailsImp.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + EXPIRATION_TOKEN * 1000))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public String getUsernameToken(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }

    public Boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        }catch (Exception e){
            return false;
        }

    }
}