/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.penzasoft.uldbscryptolib;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import javax.crypto.BadPaddingException;


import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
/**
 *
 * @author ktepin
 * RSA algorith is based on several mathematical formulas, including:
 * Euler's totient function: phi(n) = (p - 1) * (q - 1)
 * Modular exponentiation: a^b mod n
 * Modular inverse: a^-1 mod n
 * These formulas are used to generate the public and private keys, encrypt and decrypt messages, and perform other operations in the RSA algorithm.
 */
public class CustomRSA {

    private PublicKey publicKey;
    private PrivateKey privateKey;

    public CustomRSA(int bitLength) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException {
        SecureRandom random = new SecureRandom(); //TODO custom random

        // Generate two large prime numbers, p and q
        BigInteger p = BigInteger.probablePrime(bitLength / 2, random);
        BigInteger q = BigInteger.probablePrime(bitLength / 2, random);

        // Calculate modulus n = p * q
        BigInteger modulus = p.multiply(q);

        // Calculate Euler's totient function phi(n) = (p - 1) * (q - 1)
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

        // Choose public key e such that 1 < e < phi(n) and gcd(e, phi(n)) = 1
        BigInteger publicKeyExponent = BigInteger.probablePrime(bitLength / 4, random);
        while (phi.gcd(publicKeyExponent).compareTo(BigInteger.ONE) > 0 && publicKeyExponent.compareTo(phi) < 0) {
            publicKeyExponent = publicKeyExponent.add(BigInteger.ONE);
        }

        // Calculate private key d such that d * e ≡ 1 (mod phi(n))
        BigInteger privateKeyExponent = publicKeyExponent.modInverse(phi);

        // Generate PublicKey and PrivateKey objects using the modulus and exponents
        RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(modulus, publicKeyExponent);
        KeyFactory publicKeyFactory = KeyFactory.getInstance("RSA");
        publicKey = publicKeyFactory.generatePublic(publicKeySpec);

        RSAPrivateKeySpec privateKeySpec = new RSAPrivateKeySpec(modulus, privateKeyExponent);
        KeyFactory privateKeyFactory = KeyFactory.getInstance("RSA");
        privateKey = privateKeyFactory.generatePrivate(privateKeySpec);
    }

    public byte[] encrypt(byte[] message) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException {
        // Encrypt the message using the public key
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(message);
    }

    public byte[] decrypt(byte[] ciphertext) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException {
        // Decrypt the ciphertext using the private key
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(ciphertext);
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }
}