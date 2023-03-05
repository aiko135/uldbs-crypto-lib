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

/**
 *
 * @author ktepin
 */
public class CustomJWT {
    private RSAPrivateKey privateKey;
    private RSAPublicKey publicKey;

    public CustomJWT(String privateKey, String publicKey) {
        this.privateKey = Keys.hmacShaKeyFor(privateKey.getBytes());
        this.publicKey = Keys.hmacShaKeyFor(publicKey.getBytes());
    }

    public String signPayload(Claims claims, SignatureAlgorithm algorithm) {
        return Jwts.builder()
                .setClaims(claims)
                .signWith(privateKey, algorithm)
                .compact();
    }

    public boolean verifySignature(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(publicKey).build().parse(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
