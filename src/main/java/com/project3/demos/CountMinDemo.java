package com.project3.demos;

import com.project3.counters.CountMin;
import com.project3.flows.Flow;

import java.io.IOException;
import java.net.URISyntaxException;

import static com.project3.demos.util.SketchDemoUtility.testSketch;

/**
 * Performs a demo run on Count Min.
 */
public class CountMinDemo {
    public static void main(String[] args) throws IOException, URISyntaxException {
        CountMin<Flow> sketch = new CountMin<>(3, 3000);
        testSketch(sketch);
    }
}
