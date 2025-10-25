package com.collins.ploutos.ploutos.util;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    // Get the signing key from the secret
    private SecretKey getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /// Creates a JWT token
    /// the claims are the additional information you want to add to the token
    /// the subject is the user id
    ///
    public String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignKey())
                .compact();
    }

    // Parse and extract all claims from token
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // Extract any claim from token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // extracts only the username from the token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // extracts only the expiration date from the token
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /// checks if the token has expires.
    /// The expiry date has to be before the current date.
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /// checks if the token is valid
    /// The username from the extracted token must be the same with the username
    /// existing in our state.
    /// The token must not have expired.
    public boolean validateToken(String token, UserDetails userDetails) {
        /// extract the username from the token
        final String username = extractUsername(token);
        /// check if the username from the token is the same as the username from the
        /// userDetails
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // Generate token for a user
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

}