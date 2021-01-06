package com.project3.counters;

import com.project3.flows.Flow;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CounterSketchTest {

    private CounterSketch<Flow> sketch;
    private final int k = 3;
    private final int w = 20;

    @BeforeEach
    void beforeEach() {
        sketch = new CounterSketch<>(k, w);
    }

    @Test
    void increment() {
        Flow flow = new Flow("192.168.0.1", 2);
        sketch.increment(flow, flow.getNumberOfPackets());
        flow = new Flow("192.168.0.1", 3);
        sketch.increment(flow, flow.getNumberOfPackets());
        Assertions.assertEquals(5, sketch.getFlowSize(flow));
    }
}