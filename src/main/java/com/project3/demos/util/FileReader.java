package com.project3.demos.util;

import com.project3.flows.Flow;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Helps to read the file required to run the demo.
 */
public class FileReader {

    public static List<Flow> getAllFlows() throws IOException, URISyntaxException {
        return getAllFlows("project3input.txt");
    }

    /**
     * Reads file and returns all the Flow details in it.
     *
     * @param fileName the name of the file which is to be read.
     * @return the details of all the flows.
     * @throws IOException        if unable to read the file.
     * @throws URISyntaxException if unable to find the file.
     */
    public static List<Flow> getAllFlows(final String fileName) throws IOException, URISyntaxException {
        Path path = Paths.get(Objects.requireNonNull(FileReader.class.getClassLoader()
                .getResource(fileName)).toURI());
        List<Flow> flows;
        Stream<String> lines = Files.lines(path);
        flows = lines.map(line -> line.split("\\s+")).filter(values -> values.length == 2)
                .map(values -> new Flow(values[0], Integer.parseInt(values[1]))).collect(Collectors.toList());
        return flows;
    }
}
