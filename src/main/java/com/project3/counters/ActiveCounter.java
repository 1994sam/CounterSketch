package com.project3.counters;

import java.util.Random;

/**
 * Active counter implementation.
 */
public class ActiveCounter {
    public int n = 0;
    public int e = 0;
    private final int overflow;
    private final Random random;

    /**
     * Used to initialize the number of bits to be used to used by the counter.
     *
     * @param numberOfBits the number bits of the counter.
     */
    public ActiveCounter(int numberOfBits) {
        random = new Random();
        overflow = (int) Math.pow(2, numberOfBits >> 1) - 1;
    }

    /**
     * Increases the counter by one using probability.
     */
    public void activeIncrease() {
        double val = Math.pow(2, e);
        double probability = 1 / val;
        double randomValue = random.nextDouble();
        if (randomValue < probability) {
            n++;
            if (n > overflow) {
                n = n >>> 1;
                e++;
            }
        }
    }

    /**
     * @return the value stored in the counter.
     */
    public int getValue() {
        return (int) (n * Math.pow(2, e));
    }
}
