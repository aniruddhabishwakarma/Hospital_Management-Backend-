package com.example.HospitalManagement.System.jwt;

import com.example.HospitalManagement.System.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtTokenUtil {

    @Value("${jwt.secret]")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    //Method to generate token
    public String generateToken(UserEntity userEntity){

        return Jwts.builder().setSubject(userEntity.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+expiration))
                .setId(String.valueOf(userEntity.getId()))
                .signWith(secretKey,SignatureAlgorithm.HS256)
                .compact();
    }

    //Method to validate token
    private boolean isTokenExpired(String token) throws SignatureException, MalformedJwtException{
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    //Method to extract username
    public String extractUsername(String token) throws SignatureException, MalformedJwtException{
        return extractAllClaims(token).getSubject();
    }

    //Method to extract all claims
    private Claims extractAllClaims(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Long getExpiration(){
        return expiration;
    }

    public String generateRefreshToken(UserEntity userEntity){
        return Jwts.builder().setSubject(userEntity.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+expiration))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token, UserDetails userDetails) throws SignatureException{
        return userDetails.getUsername().equals(extractUsername(token)) && !isTokenExpired(token);
    }
}
