/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.penzasoft.uldbscryptolib;
import java.util.Random;

public class CustomRandom extends Random {
    private static final long serialVersionUID = 1L;
    private static final int SEED_MULTIPLIER = 1103515245;
    private static final int SEED_INCREMENT = 12345;
    private static final int MODULUS = 2147483647;
    private static final int OFFSET = 123456789;

    private int seed;

    public CustomRandom(int seed) {
        this.seed = seed;
    }

    @Override
    protected int next(int bits) {
        seed = (SEED_MULTIPLIER * seed + SEED_INCREMENT) % MODULUS;
        return seed >>> (32 - bits);
    }

    @Override
    public synchronized void setSeed(long seed) {
        this.seed = (int) (seed ^ OFFSET);
    }
}