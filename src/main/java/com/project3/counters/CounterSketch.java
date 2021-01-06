package com.project3.counters;

import java.util.Arrays;

/**
 * Counter Sketch implementation.
 *
 * @param <T> the type of the object which is to be counter.
 */
public class CounterSketch<T> extends CountMin<T> {

    public CounterSketch(int k, int width) {
        super(k, width);
    }

    /**
     * Increments the counter.
     *
     * @param element for which the counter is to be incremented.
     * @param count   the value by which the counter is to be incremented.
     */
    @Override
    public void increment(final T element, int count) {
        for (int i = 1; i <= k; i++) {
            int index = getIndex(element, i);
            if (isBitSet(element.hashCode(), 63)) {
                table[i - 1][index] -= count;
            } else {
                table[i - 1][index] += count;
            }
        }
    }

    /**
     * @param number   the number which is to be checked.
     * @param position the bit position to be checked.
     * @return true if the bit at {@code position} is set.
     */
    private boolean isBitSet(final long number, final int position) {
        return ((number >> (position)) & 1) > 0;
    }

    /**
     * Returns the estimated value of the element.
     *
     * @param element the element whose estimate is to be found out.
     * @return the estimate of the element.
     */
    public int getFlowSize(T element) {
        int[] estimates = new int[k];
        for (int i = 1; i <= k; i++) {
            long hash = getHash(element, i);
            int index = (int) hash % (width - 1);
            if (isBitSet(element.hashCode(), 63)) {
                estimates[i - 1] = -table[i - 1][index];
            } else {
                estimates[i - 1] = table[i - 1][index];
            }
        }
        return getMedian(estimates);
    }

    /**
     * Calculate the median of an array.
     *
     * @param estimates the array whose median is to be found out.
     * @return the median value.
     */
    private int getMedian(int[] estimates) {
        Arrays.sort(estimates);
        double median;
        if (estimates.length % 2 == 0) {
            median = ((double) estimates[estimates.length / 2] + (double) estimates[estimates.length / 2 - 1]) / 2;
        } else {
            median = estimates[estimates.length / 2];
        }
        return (int) median;
    }

}
