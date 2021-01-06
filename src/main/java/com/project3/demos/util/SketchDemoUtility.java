package com.project3.demos.util;

import com.project3.counters.CountMin;
import com.project3.flows.Flow;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

/**
 * Helps to run demos on sketches.
 */
public class SketchDemoUtility {
    /**
     * Runs demo on {@code sketch}
     *
     * @param sketch the sketch on which the demo is to bee run
     * @throws IOException        if unable to read input file.
     * @throws URISyntaxException if unable to find input file.
     */
    public static void testSketch(CountMin<Flow> sketch) throws IOException, URISyntaxException {
        final List<Flow> flows = FileReader.getAllFlows();
        Map<String, Integer> frequencies = new HashMap<>();
        flows.forEach(flow -> {
            int frequency = frequencies.getOrDefault(flow.getID(), 0);
            frequencies.put(flow.getID(), frequency + flow.getNumberOfPackets());
        });
        flows.forEach(flow -> sketch.increment(flow, flow.getNumberOfPackets()));
        long totalError = flows.stream().mapToInt(flow ->
                Math.abs(frequencies.get(flow.getID()) - sketch.getFlowSize(flow))).sum();
        System.out.println("The average error among all flows: " + totalError / flows.size());
        flows.sort(Comparator.comparingInt(sketch::getFlowSize).reversed());
        System.out.format("%18s%11s%9s\n", "Flow ID", "Estimated", "Actual");
        flows.stream().limit(100).forEach(flow -> System.out.format("%18s%11d%9d\n", flow.getID(), sketch.getFlowSize(flow),
                flow.getNumberOfPackets()));
    }
}
