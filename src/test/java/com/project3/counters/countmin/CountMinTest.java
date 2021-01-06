package com.project3.counters.countmin;

import com.project3.counters.CountMin;
import com.project3.flows.Flow;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CountMinTest {

    private CountMin<Flow> sketch;
    private final int k = 3;
    private final int w = 10;

    @BeforeEach
    void beforeEach() {
        sketch = new CountMin<>(k, w);
    }

    @Test
    void increment() {
        Flow flow = new Flow("192.168.0.1", 2);
        sketch.increment(flow, flow.getNumberOfPackets());
        flow = new Flow("192.168.0.1", 3);
        sketch.increment(flow, flow.getNumberOfPackets());
        Assertions.assertEquals(5, sketch.getFlowSize(flow));
    }

    @Test
    void getFlowSize() {
        Flow flow = new Flow("192.168.0.1", 2);
        sketch.increment(flow, flow.getNumberOfPackets());
        Assertions.assertEquals(2, sketch.getFlowSize(flow));
    }
}