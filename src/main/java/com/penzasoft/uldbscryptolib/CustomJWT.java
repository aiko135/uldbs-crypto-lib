/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.penzasoft.uldbscryptolib;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ktepin
 */
public class CustomJWT {
    private static final String TOKEN_ISSUER = "admin"; 
    
    private String secretKey = "default_key54321"; 
    private byte[] secretKeyBytes = secretKey.getBytes();
    private int tokenExpTime = 36000; // in seconds
    
    public CustomJWT(){
    }
    
    public CustomJWT(String secretKey, int tokExpTime){
        this.secretKey = secretKey;
        this.secretKeyBytes = this.secretKey.getBytes();
        this.tokenExpTime = tokExpTime;
    }   
    
//    public String generateToken(String subject) {
//        Date now = new Date();
//        Date expiration = new Date(now.getTime() + tokenExpTime * 1000);
//
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("sub", subject);
//
//        return Jwts.builder()
//                .setClaims(claims)
//                .setIssuer(TOKEN_ISSUER)
//                .setIssuedAt(now)
//                .setExpiration(expiration)
//                .signWith(Keys.hmacShaKeyFor(secretKeyBytes), SignatureAlgorithm.HS256)
//                .compact();
//    }

//    public boolean verifyToken(String token) {
//        try {
//            Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secretKeyBytes)).build().parseClaimsJws(token);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }

//    public String getSubjectFromToken(String token) {
//        Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secretKeyBytes)).build().parseClaimsJws(token);
//        return claims.getBody().getSubject();
//    }
}
