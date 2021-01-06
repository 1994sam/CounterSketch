package com.project3.counters;

import java.util.Random;

/**
 * Implementation of Count Min data structure.
 *
 * @param <T> the type of object for which the data structure is to be used.
 */
public class CountMin<T> {
    protected final int[][] table;
    private final long[] hashes;
    protected final int k;
    protected final int width;
    public static final long PRIME_MODULUS = (1L << 31) - 1;

    public CountMin(int k, int width) {
        this.k = k;
        table = new int[k][width];
        hashes = new long[k];
        initHashes();
        this.width = width;
    }

    /**
     * Increment the counters.
     *
     * @param element for which the counters are to be incremented.
     * @param count   the value by which the counters are to be incremented.
     */
    public void increment(T element, final int count) {
        for (int i = 1; i <= k; i++) {
            table[i - 1][getIndex(element, i)] += count;
        }
    }

    /**
     * Calculates the estimated flow size.
     *
     * @param element whose estimate is to be found out.
     * @return the estimate.
     */
    public int getFlowSize(final T element) {
        int result = Integer.MAX_VALUE;
        for (int i = 1; i <= k; i++) {
            result = Math.min(table[i - 1][getIndex(element, i)], result);
        }
        return result;
    }

    /**
     * Initializes the {@code hashes} array for calculating the hash values of elements.
     */
    private void initHashes() {
        Random random = new Random();
        for (int i = 0; i < k; ++i) {
            hashes[i] = random.nextInt(Integer.MAX_VALUE);
        }
    }

    /**
     * The function which calculates the index to add the element to the hash table. This function creates a
     * hash value which lies from 0 to capacity of the hash table.
     *
     * @param element        the element to be inserted in the hash table.
     * @param functionNumber the hash function which is to be used.
     * @return the hash value for the element.
     */
    public int getIndex(final T element, int functionNumber) {
        long hash = getHash(element, functionNumber);
        return ((int) hash) % (width - 1);
    }

    /**
     * Calculates the ith hash value of an {@code element}
     *
     * @param element        whose ith hash value is to be calculated.
     * @param functionNumber number of hash value to be calculated.
     * @return the ith hash value of the {@code element}
     */
    protected long getHash(final T element, int functionNumber) {
        long hash = hashes[functionNumber - 1] * element.hashCode();
        hash += hash >> 32;
        hash &= PRIME_MODULUS;
        return hash;
    }
}
