package com.project3.demos;

import com.project3.counters.CounterSketch;
import com.project3.demos.util.SketchDemoUtility;
import com.project3.flows.Flow;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Performs a demo run on Counter Sketch.
 */
public class CounterSketchDemo {
    public static void main(String[] args) throws IOException, URISyntaxException {
        CounterSketch<Flow> sketch = new CounterSketch<>(3, 3000);
        SketchDemoUtility.testSketch(sketch);
    }
}
