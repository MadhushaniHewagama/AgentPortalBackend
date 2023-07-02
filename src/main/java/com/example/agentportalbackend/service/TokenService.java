package com.example.agentportalbackend.service;

import com.example.agentportalbackend.dto.Token;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
    @Service
    @Slf4j
    public class TokenService {

        @Value("${jwt.secret}")
        private String SECRET_KEY;

        public String generateToken(Token token) {
            // Set the expiration time for the token
            Date expirationDate = new Date(System.currentTimeMillis() + 86400000); // 1 day
            // Generate the token using the user's ID, username, and expiration date
            String JwtToken = Jwts.builder()
                    .setSubject(token.getId().toString())
                    .claim("id", token.getId().toString())
                    .claim("userType", token.getUserType())
                    .setExpiration(expirationDate)
                    .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                    .compact();

            return JwtToken;
        }

    }
