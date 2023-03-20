/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.penzasoft.uldbscryptolib;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;

/**
 *
 * @author ktepin
 */
public class Main {

    private static int KEY_LENGTH = 2048; //1024 or 2048 or 4096 

    public static void main(final String[] args){
        try {
            
            CustomRSA rsa = new CustomRSA(KEY_LENGTH);
            String payload = "RSA Test text abc123456";
            byte[] cipher = rsa.encrypt(payload.getBytes(StandardCharsets.UTF_8));

            System.out.println("\nTesting RSA:");
            System.out.println("Example:");
            System.out.println(payload);
            System.out.println("Cipher:");
            System.out.println(cipher);

            byte[] ex = rsa.decrypt(cipher);
            System.out.println("Decrypt:");
            System.out.println(new String(ex, StandardCharsets.UTF_8));

//            System.out.println("\nTesting JWT:");
//            String key = "abcd12345";
//            CustomJWT jwt = new CustomJWT(key, 36000);
//            String token = jwt.generateToken("{login:Bob}");
//            System.out.println("Got a JWT token:");
//            System.out.println(token);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
