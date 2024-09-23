package com.aryansingh.instaforms.utils.security;

import com.aryansingh.instaforms.utils.AppConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Slf4j
public class JWTUtils {

    public static String extractUsername(String token) {
        String subject = extractClaim(token, Claims::getSubject);
       log.info("Extracted subject: " + subject);
        return subject;
    }

    public static String extractEntityType(String token){
        final Claims claims=extractAllClaims(token);
        return (String) claims.get(AppConstants.ENTITY_TYPE);
    }

    public static Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public static <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public static Claims extractAllClaims(String token) {

        Claims parsedClaims = Jwts.parser().setSigningKey(AppConstants.SECRET_KEY).build().parseSignedClaims(token)
                .getPayload();
        log.info("Parsed Claims: " + parsedClaims.getSubject());
        return parsedClaims;
    }

    private static Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public static String generateToken(String username, String entityType,String token) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(AppConstants.ENTITY_TYPE,entityType);
        claims.put(AppConstants.UNIQUE_TOKEN,token);
        return createToken(claims, username);
    }

    private static String createToken(Map<String, Object> claims, String subject) {
        Date issueDate = new Date(System.currentTimeMillis());
        log.info("issueDate: " + issueDate + " time: " + issueDate.getTime() + " issueDate formatted: "
                + issueDate);
        Date expirationDate = new Date(System.currentTimeMillis() + AppConstants.ACCESS_TOKEN_EXPIRATION_TIME
        );
        log.info("Expiration date: " + expirationDate + " formatted: " + expirationDate);
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(issueDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, AppConstants.SECRET_KEY).compact()
                ;
    }


    public static Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        boolean isUsernameValid = username.equals(userDetails.getUsername());
        boolean isJwtTtokenExpired = isTokenExpired(token);
        log.info("Is token expired: " + isJwtTtokenExpired + " is username valid: " + isUsernameValid);
        if (!isUsernameValid) {
            log.info("Username in the token is invalid");
        }
        if (isJwtTtokenExpired) {
            log.info("Token is expired!");
        }
        return (isUsernameValid && !isJwtTtokenExpired);
    }

    public static String[] decodedBase64(String token) {

        byte[] decodedBytes = Base64.getDecoder().decode(token);
        String pairedCredentials = new String(decodedBytes);

        return pairedCredentials.split(":", 2);

    }

    public static String extract(String key) {
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        Claims claims = (Claims) authentication.getDetails();
        return (String)claims.get(key);
    }

}
