package com.project3.demos;

import com.project3.counters.ActiveCounter;

/**
 * Performs a demo run for Active Counter.
 */
public class ActiveCounterDemo {
    public static void main(String[] args) {
        ActiveCounter counter = new ActiveCounter(32);
        //increments the counter 1000000 times.
        for (int i = 0; i < 1000000; i++) {
            counter.activeIncrease();
        }
        System.out.println(counter.getValue());
    }
}
