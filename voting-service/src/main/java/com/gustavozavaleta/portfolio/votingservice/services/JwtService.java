package com.gustavozavaleta.portfolio.votingservice.services;

import com.gustavozavaleta.portfolio.votingservice.model.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private final String secretKey;

    public JwtService() {
        secretKey = generateSecretKey();
    }

    public boolean isValidJwtFormat(String token) {
        if (token == null) {
            return false;
        }
        String[] parts = token.split("\\.");
        return parts.length == 3;
    }

    public String generateSecretKey() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            SecretKey secretKey = keyGen.generateKey();
            System.out.println("Secret Key: " + secretKey.toString());
            return Base64.getEncoder().encodeToString(secretKey.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String generateToken(@NotNull @Digits(integer = 8, fraction = 0) Integer documentId) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder().claims(claims).subject(documentId.toString()
                ).issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 100000*60*3))
                .signWith(getKey()).compact();
    }

    private SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractDocumentId(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token).getPayload();
    }

    public boolean validateToken(String token, Users user) {
        final String documentId = extractDocumentId(token);
        return Integer.valueOf(documentId).equals(user.getNationalId());
    }
}
